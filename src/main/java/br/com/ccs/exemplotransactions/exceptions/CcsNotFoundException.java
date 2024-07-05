package br.com.ccs.exemplotransactions.exceptions;

public class CcsNotFoundException extends RuntimeException {
    public CcsNotFoundException(String msg) {
        super(msg);
    }
}
