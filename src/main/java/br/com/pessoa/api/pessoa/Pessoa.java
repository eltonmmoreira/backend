package br.com.pessoa.api.pessoa;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.pessoa.api.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString(of = {"nome", "cpf"})
@Entity
@Table(name = "PESSOA",
    uniqueConstraints = @UniqueConstraint(
            columnNames = {"CPF", "STATUS"})
)
public class Pessoa extends BaseEntity {

    @NotNull(message = "{nome.notnull}")
    @Size(message = "{nome.size}", max = 150)
    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @CPF(message = "{cpf.invalido}")
    @NotNull(message = "{cpf.notnull}")
    @NotEmpty(message = "{cpf.notnull}")
    @Column(name = "CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull(message = "{dataDeNascimento.notnull}")
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataDeNascimento;

    @NotEmpty(message = "{cpf.notnull}")
    @NotNull(message = "{email.notnull}")
    @Size(message = "{email.size}", max = 400)
    @Column(name = "EMAIL", length = 400, nullable = false)
    private String email;

    @Column(name = "TEM_IMAGEM")
    private Boolean temImagem;

    private transient String image;
}
