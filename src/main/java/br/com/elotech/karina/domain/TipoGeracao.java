package br.com.elotech.karina.domain;

public enum TipoGeracao {

    MANUAL("M"),
    AUTOMATICA("A");

    private final String sigla;

    private TipoGeracao(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return this.sigla;
    }
}
