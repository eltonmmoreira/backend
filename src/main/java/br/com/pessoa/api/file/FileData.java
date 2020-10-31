package br.com.pessoa.api.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileData extends JpaRepository<File, Integer> {

    Optional<File> findByIdPessoa(Integer id);

}
