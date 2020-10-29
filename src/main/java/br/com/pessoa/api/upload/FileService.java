package br.com.pessoa.api.upload;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void upload(MultipartFile multipartFile, Long pessoaId);

    String findFile(Long id);
}
