package com.henriqueaguiar.libraryapi.exceptions;

public class OperacaoNaoPermitidaExecption extends RuntimeException {
    public OperacaoNaoPermitidaExecption(String message) {
        super(message);
    }
}
