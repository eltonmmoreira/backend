package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.BaseCrudRepository;
import br.com.pessoa.api.core.JpaCrudServiceImpl;
import br.com.pessoa.api.file.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.Optional;

@Validated
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
    protected BaseCrudRepository<Pessoa, Integer> getData() {
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

        if (StringUtils.isNotBlank(entity.getCpf())) {
            entity.setCpf(entity.getCpf().replaceAll("\\D+", ""));
        }

    }

    @Override
    public Pessoa save(Pessoa entity) {
        try {
            return super.save(entity);
        } catch (Exception e) {
            if (e.getMessage().contains("uk_cpf")) {
                throw new RuntimeException("CPF já cadastrado!");
            }
            throw new RuntimeException(e);
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
        Optional.ofNullable(entity).ifPresent(pessoa -> {
            if (Boolean.TRUE.equals(pessoa.getTemImagem())) {
                try {
                    fileService.findFile(pessoa.getId()).ifPresent(pessoa::setImage);
                } catch (IOException e) {
                    getLog().error(this.getClass(), e);
                }
            }
        });
    }
}
