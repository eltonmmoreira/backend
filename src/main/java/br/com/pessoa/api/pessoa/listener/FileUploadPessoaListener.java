package br.com.pessoa.api.pessoa.listener;

import br.com.pessoa.api.file.event.PostFileUploadEvent;
import br.com.pessoa.api.pessoa.PessoaData;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FileUploadPessoaListener {

    private PessoaData pessoaData;

    public FileUploadPessoaListener(PessoaData pessoaData) {
        this.pessoaData = pessoaData;
    }

    @EventListener
    public Integer updateImagemCadastrada(PostFileUploadEvent event) {
        if (event.getFile() != null) {
            return pessoaData.updateTemImagem(
                    event.getFile().getIdPessoa(),
                    true
            );
        }
        return 0;
    }
}
