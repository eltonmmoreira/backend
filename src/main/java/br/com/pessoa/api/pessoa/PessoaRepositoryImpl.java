package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.Status;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    @PersistenceContext EntityManager entityManager;

    @Override
    public Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable) {
        var jpaQuery = new JPAQuery<PessoaDto>(entityManager);

        var pessoa = QPessoa.pessoa;
        var query = jpaQuery.select(
                Projections.constructor(PessoaDto.class,
                pessoa.id,
                pessoa.nome,
                pessoa.cpf,
                pessoa.dataDeNascimento,
                pessoa.email))
                .from(pessoa);

        query.limit(pageable.getPageSize()).offset(pageable.getOffset());

        var where = new BooleanBuilder();
        Optional.ofNullable(filtro).ifPresentOrElse(pessoaFiltro -> {
            if (StringUtils.isNotBlank(pessoaFiltro.getNome())) {
                where.and(pessoa.nome.likeIgnoreCase("%" + pessoaFiltro.getNome() + "%"));
            }

            if (StringUtils.isNotBlank(pessoaFiltro.getCpf())) {
                where.and(pessoa.cpf.like("%" + pessoaFiltro.getCpf().replaceAll("\\D+", "") + "%"));
            }

            if (StringUtils.isNotBlank(pessoaFiltro.getEmail())) {
                where.and(pessoa.email.likeIgnoreCase("%" + pessoaFiltro.getEmail() + "%"));
            }

            if (pessoaFiltro.getDataDeNascimento() != null) {
                where.and(pessoa.dataDeNascimento.eq(pessoaFiltro.getDataDeNascimento()));
            }

            if (pessoaFiltro.getStatus() != null) {
                where.and(pessoa.status.eq(pessoaFiltro.getStatus()));
            } else {
                where.and(pessoa.status.eq(Status.ATIVO));
            }
            }, () -> where.and(pessoa.status.eq(Status.ATIVO))
        );
        query.where(where);
        query.orderBy(pessoa.id.asc());

        var jpaQueryResults = query.fetchResults();
        return new PageImpl<>(jpaQueryResults.getResults(), pageable, jpaQueryResults.getTotal());
    }
}
