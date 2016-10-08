package br.com.elotech.karina.service.impl;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import br.com.elotech.karina.domain.*;
import br.com.elotech.karina.service.*;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static final long VENCIMENTO_CARENCIA = 5;

    private final GeradorSenha geradorSenha;

    public LicenseServiceImpl(GeradorSenha geradorSenha) {
        this.geradorSenha = geradorSenha;
    }

    @Override
    public List<License> processar(List<IntegracaoLicenca> integracaoLicenca) {
        return integracaoLicenca.stream()
                .map(this::getLicenseFrom)
                .collect(Collectors.toList());
    }

    @Override
    public License getLicenseFrom(IntegracaoLicenca integracaoLicenca) {

        if (integracaoLicenca.isProcessado()) {

            throw new IllegalStateException("A integração não deveria estar processada.");
        }

        if (!integracaoLicenca.getTipoRegistro().equals(TipoRegistro.LIBERA)) {

            throw new IllegalStateException("A integração deveria estar liberada.");
        }

        License license = License.builder()
                .cliente(integracaoLicenca.getCodigoClienteContrato())
                .code(String.format("%s%d", integracaoLicenca.getCodigoServico(),
                        integracaoLicenca.getCodigoClienteContrato()))
                .date(getExpirationDate(integracaoLicenca))
                .module(Module.fromCode(integracaoLicenca.getCodigoServico()))
                .name(integracaoLicenca.getNomeCliente())
                .build();

        license.setPassword(geradorSenha.generate(license));

        return license;
    }

    private LocalDate getExpirationDate(IntegracaoLicenca integracaoLicenca) {

        if (integracaoLicenca.getQuitacaoTitulo() == null) {

            if (integracaoLicenca.getVencimentoTitulo() == null) {
                return LocalDate.now();
            }

            return integracaoLicenca.getVencimentoTitulo().plusDays(VENCIMENTO_CARENCIA);

        } else {

            return integracaoLicenca.getQuitacaoTitulo().plusMonths(1);
        }

    }

}
