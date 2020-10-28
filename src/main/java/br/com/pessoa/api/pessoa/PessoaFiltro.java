package br.com.pessoa.api.pessoa;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaFiltro {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataDeNascimento;
}
