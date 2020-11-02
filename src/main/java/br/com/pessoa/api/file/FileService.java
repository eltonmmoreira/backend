package br.com.pessoa.api.file;

import br.com.pessoa.api.core.SimpleCrudService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileService extends SimpleCrudService<File, Integer> {

    void upload(MultipartFile multipartFile, Integer idPessoa);

    Optional<String> findFile(Integer id) throws IOException;

}
