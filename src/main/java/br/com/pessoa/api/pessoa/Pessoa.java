package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString(of = {"id", "nome", "cpf"})
@Entity
@Table(name = "PESSOA")
public class Pessoa extends BaseEntity {

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataDeNascimento;

    @Column(name = "EMAIL", length = 400, nullable = false)
    private String email;

    @Column(name = "TEM_IMAGEM")
    private Boolean temImagem;

    private transient String image;
}
