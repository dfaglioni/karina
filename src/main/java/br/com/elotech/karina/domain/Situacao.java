package br.com.elotech.karina.domain;

public enum Situacao {

    ATIVO("A"), INATIVO("I");

    private final String sigla;

    private Situacao(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return this.sigla;
    }
}
