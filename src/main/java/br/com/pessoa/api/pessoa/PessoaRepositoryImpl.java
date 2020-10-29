package br.com.pessoa.api.pessoa;

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

        Optional.ofNullable(filtro).ifPresent(pessoaFiltro -> {
            var where = new BooleanBuilder();
            if (StringUtils.isNotBlank(pessoaFiltro.getNome())) {
                where.and(pessoa.nome.like("%" + pessoaFiltro.getNome() + "%"));
            }

            if (StringUtils.isNotBlank(pessoaFiltro.getCpf())) {
                where.and(pessoa.cpf.likeIgnoreCase("%" + pessoaFiltro.getCpf().replaceAll("\\D+", "") + "%"));
            }

            if (StringUtils.isNotBlank(pessoaFiltro.getEmail())) {
                where.and(pessoa.email.likeIgnoreCase("%" + pessoaFiltro.getEmail() + "%"));
            }

            if (pessoaFiltro.getDataDeNascimento() != null) {
                where.and(pessoa.dataDeNascimento.eq(pessoaFiltro.getDataDeNascimento()));
            }

            query.where(where);
        });

        query.orderBy(pessoa.id.asc());

        var jpaQueryResults = query.fetchResults();
        return new PageImpl<>(jpaQueryResults.getResults(), pageable, jpaQueryResults.getTotal());
    }
}
