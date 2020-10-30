package br.com.pessoa.api.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileData extends JpaRepository<File, Integer> {

    File findByIdPessoa(Integer id);

}
