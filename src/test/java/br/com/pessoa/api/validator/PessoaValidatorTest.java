package br.com.pessoa.api.validator;

import br.com.pessoa.api.core.Status;
import br.com.pessoa.api.exception.CpfDuplicadoException;
import br.com.pessoa.api.pessoa.Pessoa;
import br.com.pessoa.api.pessoa.PessoaData;
import br.com.pessoa.api.validator.PessoaValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaValidatorTest {

    @Mock
    private PessoaData pessoaData;

    @Test(expected = RuntimeException.class)
    public void validateEmailTest() {
        var pessoa = new Pessoa();
        pessoa.setEmail("eltonmmoreira.com");
        var validate = PessoaValidator.of(pessoa, null).validateEmail();
        validate.accept(pessoa);
    }

    @Test(expected = CpfDuplicadoException.class)
    public void validateCpfTest() {
        var cpf = "11111111111";
        var p = new Pessoa();
        p.setId(1);
        when(pessoaData.findByCpfAndStatus(cpf, Status.ATIVO)).thenReturn(
                Optional.of(p)
        );

        var pessoa = new Pessoa();
        pessoa.setCpf(cpf);
        var validate = PessoaValidator.of(pessoa, pessoaData).validateCpf();
        validate.accept(pessoa);
    }

}
