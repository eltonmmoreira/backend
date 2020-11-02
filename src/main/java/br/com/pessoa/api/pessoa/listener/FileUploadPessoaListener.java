package br.com.pessoa.api.pessoa.listener;

import br.com.pessoa.api.file.event.PostFileUploadEvent;
import br.com.pessoa.api.pessoa.PessoaData;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FileUploadPessoaListener {

    private PessoaData pessoaData;

    public FileUploadPessoaListener(PessoaData pessoaData) {
        this.pessoaData = pessoaData;
    }

    @EventListener
    public Integer updateImagemCadastrada(PostFileUploadEvent event) {
        if (event.getFile() != null) {
            log.info(String.format("Atualizado imagem da pessoa %s", event.getFile().getIdPessoa()));
            return pessoaData.updateTemImagem(
                    event.getFile().getIdPessoa(),
                    true
            );
        }
        return 0;
    }
}

