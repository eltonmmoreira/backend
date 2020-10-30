package br.com.pessoa.api.file.event;

import br.com.pessoa.api.file.File;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PostFileUploadEvent extends ApplicationEvent {

    @Getter
    private File file;

    public PostFileUploadEvent(Object source, File file) {
        super(source);
        this.file = file;
    }
}
