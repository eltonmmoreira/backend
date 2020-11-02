package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.BaseFiltro;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaFiltro extends BaseFiltro {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataDeNascimento;
}
