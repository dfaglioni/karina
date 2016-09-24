package br.com.elotech.karina.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class IntegracaoLicenca {

    /**
     * Campos não utilizados
     */
    private Integer codigoEmpresa;
    private Integer codigoFilial;

    private Integer horaGeracaoRepositorio;

    private Integer numeroInternoContrato;
    private Integer sequenciaIntegracao;

    private Integer sequenciaItemServico;
    private Integer codigoClienteContrato;

    private String nomeCliente;
    private String codigoServico;

    private Situacao situacaoContrato;
    private Situacao situacaoServico;

    private Long codigoRepresentante;
    private String numeroTituloMovimentado;
    private String tipoTitulo;

    private Integer sequenciaMovimentoTitulo;

    private LocalDate emissaoTitulo;
    private LocalDate vencimentoTitulo;
    private LocalDate quitacaoTitulo;
    private LocalDate competenciaServicoContrato;
    private LocalDate dataGeracaoRepositorio;

    private Integer usuarioGeracaoRepositorio;

    private TipoGeracao tipoGeracao;
    private TipoRegistro tipoRegistro;

    private Integer motivoServicoContrato;
    private String observacao;
    private TipoAcao tipoAcao;

    // Nossa coluna para dizer se foi processado ou não o registro
    private Boolean processado;
}
