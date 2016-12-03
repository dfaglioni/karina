package br.com.elotech.karina.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;
import br.com.elotech.karina.service.LicenseService;

@RunWith(MockitoJUnitRunner.class)
public class PushServiceBlueMixTest {

    private static final String SERVER_URL = "http://license.elotech.com.br/";

    private PushServiceBlueMix service;

    @Mock
    private LicenseService licenseServiceMock;

    @Mock
    private RestTemplate restTemplateMock;

    @Before
    public void setup() {

        this.service = new PushServiceBlueMix(licenseServiceMock, restTemplateMock, SERVER_URL);
    }

    @Test
    public void testPushFromEmptyCollection() {

        when(this.licenseServiceMock.processar()).thenReturn(Lists.newArrayList());

        this.service.push();

        verify(this.licenseServiceMock).processar();
    }

    @Test
    public void testPushFromNullCollection() {

        when(this.licenseServiceMock.processar()).thenReturn(null);

        this.service.push();

        verify(this.licenseServiceMock).processar();
        Mockito.verifyZeroInteractions(this.restTemplateMock);
    }

    @Test
    public void testPushFromPopulatedCollection() {

        final List<License> licenses = createLicenseList();

        when(this.licenseServiceMock.processar()).thenReturn(licenses);

        this.service.push();

        verify(this.licenseServiceMock).processar();
        verify(this.restTemplateMock).postForObject(SERVER_URL + "/licenses", licenses, List.class);

    }

    private List<License> createLicenseList() {

        License license1 = License.builder()
                .cliente(1)
                .code("licenseCode1")
                .date(LocalDate.now())
                .module(Module.ACAO_SOCIAL)
                .name("licenseName1")
                .build();

        License license2 = License.builder()
                .cliente(1)
                .code("licenseCode2")
                .date(LocalDate.now())
                .module(Module.AGUA_ESGOTO)
                .name("licenseName2")
                .build();

        return Arrays.asList(license1, license2);
    }
}
