package br.com.elotech.karina.domain;

import java.time.*;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name = "usu_t160itg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntegracaoLicenca {

    @EmbeddedId
    private IntegracaoLicencaPk id;

    @Column(name = "usu_horger")
    private Integer horaGeracaoRepositorio;

    @Column(name = "usu_numctr")
    private Integer numeroInternoContrato;

    @Column(name = "usu_seqcvs")
    private Integer sequenciaItemServico;

    @Column(name = "usu_codcli")
    private Integer codigoClienteContrato;

    @Column(name = "usu_nomcli")
    private String nomeCliente;

    @Column(name = "usu_codser")
    private String codigoServico;

    @Column(name = "usu_sitctr")
    private Situacao situacaoContrato;

    @Column(name = "usu_sitcvs")
    private Situacao situacaoServico;

    @Column(name = "usu_codrep")
    private Long codigoRepresentante;

    @Column(name = "usu_numtit")
    private String numeroTituloMovimentado;

    @Column(name = "usu_codtpt")
    private String tipoTitulo;

    @Column(name = "usu_seqmov")
    private Integer sequenciaMovimentoTitulo;

    @Column(name = "usu_emitit")
    private LocalDate emissaoTitulo;

    @Column(name = "usu_vcttit")
    private LocalDate vencimentoTitulo;

    @Column(name = "usu_baitit")
    private LocalDate quitacaoTitulo;

    @Column(name = "usu_cptctr")
    private LocalDate competenciaServicoContrato;

    @Column(name = "usu_datger")
    private LocalDate dataGeracaoRepositorio;

    @Column(name = "usu_usuger")
    private Integer usuarioGeracaoRepositorio;

    @Column(name = "usu_tipger")
    private TipoGeracao tipoGeracao;

    @Column(name = "usu_blolib")
    private TipoRegistro tipoRegistro;

    @Column(name = "usu_codmot")
    private Integer motivoServicoContrato;

    @Column(name = "usu_obsitg")
    private String observacao;

    @Column(name = "usu_tipaca")
    private TipoAcao tipoAcao;

    @Column(name = "usu_processado")
    private boolean processado = false;

    public IntegracaoLicenca withCodigoClienteContrato(Integer codigoClienteContrato) {
        this.codigoClienteContrato = codigoClienteContrato;
        return this;
    }

    public IntegracaoLicenca withNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        return this;
    }

    public IntegracaoLicenca withCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
        return this;
    }

    public IntegracaoLicenca withEmissaoTitulo(LocalDate emissaoTitulo) {
        this.emissaoTitulo = emissaoTitulo;
        return this;
    }

    public IntegracaoLicenca withVencimentoTitulo(LocalDate vencimentoTitulo) {
        this.vencimentoTitulo = vencimentoTitulo;
        return this;
    }

    public IntegracaoLicenca withQuitacaoTitulo(LocalDate quitacaoTitulo) {
        this.quitacaoTitulo = quitacaoTitulo;
        return this;
    }

    public IntegracaoLicenca withTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
        return this;
    }

    public IntegracaoLicenca withProcessado(boolean processado) {
        this.processado = processado;
        return this;
    }

}
