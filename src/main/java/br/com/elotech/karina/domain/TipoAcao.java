package br.com.elotech.karina.domain;

public enum TipoAcao {

    INATIVACAO_CONTRATO(1),
    ATIVACAO_CONTRATO(2),
    ENTRADA_TITULO_FATURAMENTO(3),
    BAIXA_TITULO(4),
    TITULO_ABERTO_VENCIDO(5),
    PRORROGACAO_VENCIMENTO(6),
    ANTECIPACAO_VENCIMENTO(7);

    private final Integer codigo;

    private TipoAcao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
