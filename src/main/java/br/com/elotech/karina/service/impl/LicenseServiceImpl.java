package br.com.elotech.karina.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;
import br.com.elotech.karina.domain.TipoRegistro;
import br.com.elotech.karina.repository.IntegracaoLicencaRepository;
import br.com.elotech.karina.service.GeradorSenha;
import br.com.elotech.karina.service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static final long VENCIMENTO_CARENCIA = 5;

    private final GeradorSenha geradorSenha;
    private final IntegracaoLicencaRepository integracaoRepository;

    public LicenseServiceImpl(GeradorSenha geradorSenha, IntegracaoLicencaRepository integracaoRepository) {
        this.geradorSenha = geradorSenha;
        this.integracaoRepository = integracaoRepository;
    }

    @Override
    public List<License> processar() {

        return this.integracaoRepository.findAll()
                .stream()
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
