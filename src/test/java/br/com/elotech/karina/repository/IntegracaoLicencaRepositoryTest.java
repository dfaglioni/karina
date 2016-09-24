package br.com.elotech.karina.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.elotech.karina.domain.IntegracaoLicenca;

@RunWith(SpringRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@DataJpaTest
public class IntegracaoLicencaRepositoryTest {

    @Autowired
    private IntegracaoLicencaRepository repository;

    @Test
    @DatabaseSetup(value = "/dbunit/IntegracaoLicenca.xml")
    public void testFindNaoProcessados() {

        final List<IntegracaoLicenca> results = repository.findAll();

        assertThat(results).isNotEmpty();
    }
}
