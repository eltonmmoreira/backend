package br.com.pessoa.api.pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PessoaDto {
    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String email;
}
