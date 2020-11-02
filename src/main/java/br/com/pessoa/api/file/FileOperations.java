package br.com.pessoa.api.file;

import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@Log4j2
public class FileOperations {

    private final Path root;

    @Deprecated
    private FileOperations(Path root) {
        this.root = root;
    }

    public static FileOperations of(@NotNull Path root) {
        return new FileOperations(root);
    }

    public static FileOperations of() {
        return new FileOperations(null);
    }

    public byte[] getFileContent(File file) throws IOException {
        var filename = file.getNome() + "." + file.getExtensao();
        return Files.readAllBytes(
                Paths.get(root + root.getFileSystem().getSeparator() + filename)
        );
    }

    public Optional<String> getFileBase64(File file, byte[] fileContent) {
        var data = String.format("data:image/%s;base64,", file.getExtensao());
        var filename = file.getNome() + "." + file.getExtensao();
        log.info(String.format("Arquivo %s carregado com sucesso!", filename));
        return Optional.of(data + Base64.getEncoder().encodeToString(fileContent));
    }

    public Optional<String> getExtensionByFilename(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
