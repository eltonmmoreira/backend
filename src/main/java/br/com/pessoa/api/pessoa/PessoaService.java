package br.com.pessoa.api.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {

    Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable);

}
