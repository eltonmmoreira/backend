package br.com.pessoa.api.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaServiceImpl implements PessoaService {
    
    private final PessoaData pessoaData;
    private final PessoaRepository pessoaRepository;
    
    public PessoaServiceImpl(PessoaData pessoaData, PessoaRepository pessoaRepository) {
        this.pessoaData = pessoaData;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable) {
        return pessoaRepository.findAll(filtro, pageable);
    }
}
