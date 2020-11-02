package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.BaseCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PessoaData extends BaseCrudRepository<Pessoa, Integer> {

    @Transactional
    @Modifying
    @Query("update Pessoa p set p.temImagem = :temImagem where p.id = :id")
    Integer updateTemImagem(Integer id,  boolean temImagem);
}
