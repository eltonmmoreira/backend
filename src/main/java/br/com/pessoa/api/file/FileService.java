package br.com.pessoa.api.file;

import br.com.pessoa.api.core.CrudService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileService extends CrudService<File, Integer> {

    void upload(MultipartFile multipartFile, Integer idPessoa);

    Optional<String> findFile(Integer id);
}
