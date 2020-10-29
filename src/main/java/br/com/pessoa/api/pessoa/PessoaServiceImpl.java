package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.JpaCrudServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaServiceImpl extends JpaCrudServiceImpl<Pessoa, Long> implements PessoaService {
    
    private final PessoaData pessoaData;
    private final PessoaRepository pessoaRepository;
    
    public PessoaServiceImpl(PessoaData pessoaData, PessoaRepository pessoaRepository) {
        this.pessoaData = pessoaData;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    protected JpaRepository<Pessoa, Long> getData() {
        return pessoaData;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable) {
        return pessoaRepository.findAll(filtro, pageable);
    }
}
