package br.com.elotech.karina.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntegracaoLicencaPk implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3301626592011538493L;

    @Column(name = "usu_codemp")
    @NotNull
    private Integer codigoEmpresa;

    @Column(name = "usu_codfil")
    @NotNull
    private Integer codigoFilial;

    @Column(name = "usu_seqitg")
    @NotNull
    private Integer sequenciaIntegracao;
}
