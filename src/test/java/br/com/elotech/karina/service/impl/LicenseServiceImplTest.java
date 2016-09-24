package br.com.elotech.karina.service.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.service.GeradorSenha;
import br.com.elotech.karina.service.LicenseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LicenseServiceImplTest {

    @Autowired
    private LicenseService licenseService;

    @MockBean
    private GeradorSenha geradorSenha;

    @Test
    public void deveChamarGeradorSenha() {
        given(geradorSenha.generate(Matchers.any(License.class))).willReturn("SENHA");

        IntegracaoLicenca integracaoLicenca = new IntegracaoLicenca();

        List<License> licenses = licenseService.processar(Lists.newArrayList(integracaoLicenca));

        assertThat(licenses, notNullValue());
        assertThat(licenses, hasSize(1));

        Mockito.verify(geradorSenha).generate(Matchers.any(License.class));

    }

}
