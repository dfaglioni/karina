package br.com.elotech.karina.domain;

import java.util.HashMap;
import java.util.Map;

public enum Module {

    FOLHA("001"),
    ORCAMENTO("002"),
    CONTABIL("003"),
    PROTOCOLO("004"),
    TRIBUTOS("005"),
    SAUDE("006"),
    PATRIMONIO("007"),
    FROTAS_VEICULOS("008"),
    AGUA_ESGOTO("009"),
    COMPRAS_LICITACOES("010"),
    INFORMACOES_GERENCIAIS("011"),
    CONTROLE_INTERNO("012"),
    ACAO_SOCIAL("013"),
    SAUDE_WEB("014"),
    FINANCEIRO("015"),
    BIBLIOTECA("016"),
    OUVIDORIA("017"),
    LEGISLACAO("018"),
    ALMOXARIFADO("019"),
    LRF("020"),
    EDUCACAO("021"),
    INFRACOES_DE_TRANSITO("022"),
    OBRAS("025"),
    MAIS_ISSQN("026"),
    GERADOR_DE_RELATORIO("027"),
    AGENDADOR("028"),
    ESOCIAL("030"),
    PORTAL_TRANSPARENCIA("034"),
    TRIBUTOS_WEB("035"),
    WEB_ORDEM_SERIVO("037"),
    CEMITERIO("038"),
    PROCESSO_JUDICIAL("039"),
    ALVARA("040"),
    RECEITAS_NAO_TRIBUTARIAS("041"),
    HOLERITE_WEB("042"),
    WEB_FISCALIZACAO("043"),
    CONFIGURACAO("090"),
    APICE("091"),
    CONTROLE_DE_VERSAO("099"),
    AISE_FOLHA("101"),
    APICE_CONTABIL("103"),
    AISE_TRIBUTOS("306");

    private final String code;

    private static final Map<String, Module> map = new HashMap<String, Module>();

    static {
        for (Module module : values()) {
            map.put(module.getCode(), module);
        }
    }

    private Module(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Module fromCode(final String code) {
        return map.get(code);
    }

}
