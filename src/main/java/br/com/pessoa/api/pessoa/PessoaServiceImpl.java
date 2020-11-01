package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.JpaCrudServiceImpl;
import br.com.pessoa.api.file.FileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class PessoaServiceImpl extends JpaCrudServiceImpl<Pessoa, Integer> implements PessoaService {
    
    private final PessoaData pessoaData;
    private final PessoaRepository pessoaRepository;
    private final FileService fileService;
    
    public PessoaServiceImpl(PessoaData pessoaData, PessoaRepository pessoaRepository, FileService fileService) {
        this.pessoaData = pessoaData;
        this.pessoaRepository = pessoaRepository;
        this.fileService = fileService;
    }

    @Override
    protected JpaRepository<Pessoa, Integer> getData() {
        return pessoaData;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaDto> findAll(PessoaFiltro filtro, Pageable pageable) {
        return pessoaRepository.findAll(filtro, pageable);
    }

    @Override
    protected void preSave(Pessoa entity) {
        if (entity.getId() == null) {
            entity.setTemImagem(false);
        }
    }

    @Override
    protected void postSave(Pessoa entity) {
        carregarImagem(entity);
    }

    @Override
    protected void postFindById(Pessoa entity) {
        carregarImagem(entity);
    }

    private void carregarImagem(Pessoa entity) {
        if (Boolean.TRUE.equals(entity.getTemImagem())) {
            try {
                fileService.findFile(entity.getId()).ifPresent(entity::setImage);
            } catch (IOException e) {
                getLog().error(this.getClass(), e);
            }
        }
    }
}
