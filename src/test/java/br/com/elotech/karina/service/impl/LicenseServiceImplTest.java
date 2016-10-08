package br.com.elotech.karina.service.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.*;
import java.util.*;

import org.junit.*;
import org.mockito.*;

import com.google.common.collect.*;

import br.com.elotech.karina.domain.*;
import br.com.elotech.karina.service.*;

public class LicenseServiceImplTest {

    private GeradorSenha geradorSenha = mock(GeradorSenha.class);

    private LicenseService licenseService = new LicenseServiceImpl(geradorSenha);

    @Before
    public void setUp() {

        when(geradorSenha.generate(Matchers.any(License.class))).thenReturn("fake");
    }

    @Test
    public void deveChamarGeradorSenha() {

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca().withTipoRegistro(TipoRegistro.LIBERA);

        List<License> licenses = licenseService.processar(Lists.newArrayList(integracaoLicenca));

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
