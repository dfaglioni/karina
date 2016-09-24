package br.com.elotech.karina.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.elotech.karina.domain.IntegracaoLicencaPk.IntegracaoLicencaPkBuilder;
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
    private Integer horaGeracaoRepositorio;

    private Integer numeroInternoContrato;

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
