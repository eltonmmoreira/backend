package br.com.pessoa.api.validator;

import br.com.pessoa.api.core.Status;
import br.com.pessoa.api.exception.CpfDuplicadoException;
import br.com.pessoa.api.pessoa.Pessoa;
import br.com.pessoa.api.pessoa.PessoaData;
import br.com.pessoa.api.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PessoaValidator {
    private final List<Consumer<Pessoa>> validators = new ArrayList<>();
    private final Pessoa pessoa;
    private final PessoaData pessoaData;

    private PessoaValidator(Pessoa pessoa, PessoaData pessoaData) {
        this.pessoa = pessoa;
        this.pessoaData = pessoaData;
        validators.add(validateEmail());
        validators.add(validateCpf());
    }

    public static PessoaValidator of(Pessoa pessoa, PessoaData pessoaData) {
        return new PessoaValidator(pessoa, pessoaData);
    }

    public void validate() {
        for (Consumer<Pessoa> validator : validators) {
            validator.accept(pessoa);
        }
    }

    private Consumer<Pessoa> validateEmail() {
        return (entity) -> {
            var emails = entity.getEmail();
            if (StringUtils.isNotBlank(emails)) {
                var validator = org.apache.commons.validator.routines.EmailValidator.getInstance();

                var emailList = emails.split(";");
                for (String email : emailList) {
                    if (!validator.isValid(email)) {
                        throw new RuntimeException(MessageUtil.get("email.invalido") + ": " + email);
                    }
                }
            }
        };
    }

    private Consumer<Pessoa> validateCpf() {
        return (entity) -> {
            if (StringUtils.isNotBlank(entity.getCpf())) {
                pessoaData.findByCpfAndStatus(entity.getCpf(), Status.ATIVO).ifPresent(pessoa -> {
                    if (!pessoa.getId().equals(entity.getId())) {
                        throw new CpfDuplicadoException(MessageUtil.get("cpf.duplicado"));
                    }
                });
            }
        };
    }
}
