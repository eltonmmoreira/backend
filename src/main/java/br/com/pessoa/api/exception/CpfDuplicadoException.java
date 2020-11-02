package br.com.pessoa.api.exception;

public class CpfDuplicadoException extends RuntimeException {

    public CpfDuplicadoException(String message) {
        super(message);
    }
}
