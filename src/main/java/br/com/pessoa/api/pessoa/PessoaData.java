package br.com.pessoa.api.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaData extends JpaRepository<Pessoa, Long> {
}
