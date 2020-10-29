package br.com.pessoa.api.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileUploadServiceImpl implements FileService {
    private final Path root = Paths.get("uploads");

    @Override
    public void upload(MultipartFile multipartFile, Long pessoaId) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }

            var path = Paths.get(root + root.getFileSystem().getSeparator() + pessoaId.toString()) ;
            Files.deleteIfExists(path);

            var extension = getExtensionByFilename(multipartFile.getOriginalFilename()).orElseThrow();
            var file = pessoaId + "." + extension;

            Files.copy(multipartFile.getInputStream(), this.root.resolve(file));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String findFile(Long id) {
        return null;
    }

    public Optional<String> getExtensionByFilename(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
