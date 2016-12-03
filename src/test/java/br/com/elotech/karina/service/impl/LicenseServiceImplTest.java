package br.com.elotech.karina.service.impl;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;
import br.com.elotech.karina.domain.TipoRegistro;
import br.com.elotech.karina.repository.IntegracaoLicencaRepository;
import br.com.elotech.karina.service.GeradorSenha;
import br.com.elotech.karina.service.LicenseService;

@RunWith(MockitoJUnitRunner.class)
public class LicenseServiceImplTest {

    private GeradorSenha geradorSenha = mock(GeradorSenha.class);

    @Mock
    private IntegracaoLicencaRepository integracaoLicensaRepositoryMock;

    private LicenseService licenseService;

    @Before
    public void setUp() {

        this.licenseService = new LicenseServiceImpl(geradorSenha, integracaoLicensaRepositoryMock);

        when(geradorSenha.generate(Matchers.any(License.class))).thenReturn("fake");
    }

    @Test
    public void deveChamarGeradorSenha() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withTipoRegistro(TipoRegistro.LIBERA);

        when(integracaoLicensaRepositoryMock.findAll()).thenReturn(Lists.newArrayList(integracaoLicenca));

        List<License> licenses = licenseService.processar();

        assertThat(licenses, notNullValue());
        assertThat(licenses, hasSize(1));

        Mockito.verify(geradorSenha).generate(Matchers.any(License.class));

    }

    @Test(expected = IllegalStateException.class)
    public void integracaoLicencaProcessa() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withTipoRegistro(TipoRegistro.LIBERA)
                .withProcessado(true);

        licenseService.getLicenseFrom(integracaoLicenca);

    }

    @Test(expected = IllegalStateException.class)
    public void integracaoLicencaNaoLiberada() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withTipoRegistro(TipoRegistro.BLOQUEIO);

        licenseService.getLicenseFrom(integracaoLicenca);

    }

    @Test
    public void tituloQuitado() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withCodigoClienteContrato(297001)
                .withCodigoServico("003").withEmissaoTitulo(LocalDate.of(2016, 10, 6))
                .withVencimentoTitulo(LocalDate.of(2016, 11, 5)).withNomeCliente("PREFEITURA MUNICIPAL DE UMUARAMA")
                .withTipoRegistro(TipoRegistro.LIBERA).withQuitacaoTitulo(LocalDate.of(2016, 11, 7));

        License licenseExpect = new License(297001, "003297001", Module.CONTABIL, LocalDate.of(2016, 12, 7),
                "PREFEITURA MUNICIPAL DE UMUARAMA", "fake");

        License license = licenseService.getLicenseFrom(integracaoLicenca);

        assertThat(license, samePropertyValuesAs(licenseExpect));

    }

    @Test
    public void tituloNaoVencido() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withCodigoClienteContrato(297001)
                .withCodigoServico("003").withEmissaoTitulo(LocalDate.of(2016, 10, 6))
                .withVencimentoTitulo(LocalDate.of(2016, 11, 5)).withNomeCliente("PREFEITURA MUNICIPAL DE UMUARAMA")
                .withTipoRegistro(TipoRegistro.LIBERA);

        License licenseExpect = new License(297001, "003297001", Module.CONTABIL, LocalDate.of(2016, 11, 10),
                "PREFEITURA MUNICIPAL DE UMUARAMA", "fake");

        License license = licenseService.getLicenseFrom(integracaoLicenca);

        assertThat(license, samePropertyValuesAs(licenseExpect));

    }

}
