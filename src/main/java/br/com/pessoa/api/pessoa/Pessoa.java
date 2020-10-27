package br.com.pessoa.api.pessoa;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataDeNascimento;

    @Column(name = "EMAIL", length = 400, nullable = false)
    private String email;

}
