package br.com.elotech.karina.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;
import br.com.elotech.karina.service.GeradorSenha;
import br.com.elotech.karina.service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final GeradorSenha geradorSenha;

    public LicenseServiceImpl(GeradorSenha geradorSenha) {
        this.geradorSenha = geradorSenha;
    }

    @Override
    public List<License> processar(List<IntegracaoLicenca> integracaoLicenca) {
        return integracaoLicenca.stream()
                .map(this::gerarLicensa)
                .collect(Collectors.toList());
    }

    private License gerarLicensa(IntegracaoLicenca integracaoLicenca) {
        License license = License.builder()
                .cliente(integracaoLicenca.getCodigoClienteContrato())
                .code(integracaoLicenca.getCodigoServico())
                .date(integracaoLicenca.getVencimentoTitulo())
                .module(Module.fromCode(integracaoLicenca.getCodigoServico()))
                .name(integracaoLicenca.getNomeCliente())
                .build();

        license.setPassword(geradorSenha.generate(license));

        return license;
    }

}
