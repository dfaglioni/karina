package br.com.elotech.karina.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usu_t160itg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntegracaoLicenca {

    @EmbeddedId
    private IntegracaoLicencaPk id;

    /**
     * Campos não utilizados
     */
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
}
