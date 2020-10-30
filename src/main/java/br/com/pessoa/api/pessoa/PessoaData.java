package br.com.pessoa.api.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PessoaData extends JpaRepository<Pessoa, Integer> {

    @Transactional
    @Modifying
    @Query("update Pessoa p set p.temImagem = :temImagem where p.id = :id")
    Integer updateTemImagem(Integer id,  boolean temImagem);
}
