package br.com.pessoa.api.file;

import br.com.pessoa.api.core.JpaBaseCrudServiceImpl;
import br.com.pessoa.api.file.event.PostFileUploadEvent;
import br.com.pessoa.api.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl extends JpaBaseCrudServiceImpl<File, Integer> implements FileService {

    private final Path root;

    private final ApplicationEventPublisher publisher;

    private final FileData fileData;

    private static final List<String> extensoesValidas = List.of("png", "jpg", "jpeg", "bmp");

    @Override
    protected JpaRepository<File, Integer> getData() {
        return fileData;
    }

    public FileServiceImpl(ApplicationEventPublisher publisher,
                           FileData fileData,
                           @Value("${uploadPath}") String uploadPath) {
        this.publisher = publisher;
        this.fileData = fileData;
        this.root = Paths.get(StringUtils.isNotBlank(uploadPath) ? uploadPath : "uploads");
    }

    @Override
    public void upload(MultipartFile multipartFile, Integer idPessoa) {
        try {
            extensaoValidate(multipartFile);
            createDirectory();

            var extension = getExtensionByFilename(multipartFile.getOriginalFilename()).orElseThrow();
            var file = save(multipartFile, idPessoa, extension);

            try {
                getLog().info(String.format("Criando arquivo %s", file.getNome() + "." + file.getExtensao()));
                createFile(multipartFile, idPessoa, extension);
            } catch (IOException e) {
                fileData.deleteById(file.getId());
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void createDirectory() throws IOException {
        if (!Files.exists(root)) {
            getLog().info(String.format("Criando diretório %s", root));
            Files.createDirectory(root);
        }
    }

    private void extensaoValidate(MultipartFile file) {
        var extension = getExtensionByFilename(file.getOriginalFilename()).orElseThrow();
        if (!extensoesValidas.contains(extension)) {
            getLog().error("Extensão inválida");
            throw new RuntimeException(MessageUtil.get("file.extensao.invalida"));
        }
    }

    private File save(MultipartFile multipartFile, Integer idPessoa, String extension) throws IOException {
        var file = fileData.findByIdPessoa(idPessoa).orElse(null);

        if (file != null) {
            var path = Paths.get(root + root.getFileSystem().getSeparator()
                    + file.getNome() + "." + file.getExtensao());
            Files.deleteIfExists(path);
        }

        return save(File.builder()
                .id(file != null ? file.getId() : null)
                .nomeOriginal(multipartFile.getOriginalFilename())
                .nome(idPessoa.toString())
                .idPessoa(idPessoa)
                .extensao(extension)
                .build());
    }

    private void createFile(MultipartFile multipartFile, Integer idPessoa, String extension) throws IOException {
        var filename = idPessoa + "." + extension;
        Files.copy(multipartFile.getInputStream(), this.root.resolve(filename));
        getLog().info(String.format("Arquivo %s gravado com sucesso!", filename));
    }

    @Override
    protected void postSave(File entity) {
        publisher.publishEvent(new PostFileUploadEvent(this, entity));
    }

    @Override
    public Optional<String> findFile(Integer id) throws IOException {
        var file = fileData.findByIdPessoa(id);

        if (file.isEmpty()) {
            return Optional.empty();
        }

        var filename = file.get().getNome() + "." + file.get().getExtensao();

        byte[] fileContent = Files.readAllBytes(
                Paths.get(root + root.getFileSystem().getSeparator() + filename)
        );
        var data = String.format("data:image/%s;base64,", file.get().getExtensao());
        getLog().info(String.format("Arquivo %s carregado com sucesso!", filename));
        return Optional.of(data + Base64.getEncoder().encodeToString(fileContent));
    }

    private Optional<String> getExtensionByFilename(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
