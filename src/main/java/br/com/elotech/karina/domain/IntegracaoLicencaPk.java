package br.com.elotech.karina.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

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

    private Integer codigoEmpresa;
    private Integer codigoFilial;
    private Integer sequenciaIntegracao;
}
