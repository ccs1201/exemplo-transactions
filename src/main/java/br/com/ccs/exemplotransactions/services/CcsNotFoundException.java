package br.com.ccs.exemplotransactions.services;

public class CcsNotFoundException extends RuntimeException {
    public CcsNotFoundException(String msg) {
        super(msg);
    }
}
