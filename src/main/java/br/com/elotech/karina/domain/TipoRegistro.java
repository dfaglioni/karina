package br.com.elotech.karina.domain;

public enum TipoRegistro {

    BLOQUEIO(1),
    LIBERA(2),
    ANALISE(3);

    private final Integer valor;

    private TipoRegistro(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return this.valor;
    }
}
