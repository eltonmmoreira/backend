package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService extends CrudService<Pessoa, Long> {

    Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable);
}
