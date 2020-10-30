package br.com.pessoa.api.file;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_PESSOA", nullable = false)
    private Integer idPessoa;

    @Column(name = "EXTENSAO", nullable = false, length = 5)
    private String extensao;

    @Column(name = "NOME_ORIGINAL", nullable = false, length = 150)
    private String nomeOriginal;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
}
