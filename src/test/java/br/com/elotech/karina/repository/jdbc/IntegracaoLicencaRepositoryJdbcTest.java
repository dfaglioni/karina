package br.com.elotech.karina.repository.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

import br.com.elotech.karina.KarinaApplication;
import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.repository.IntegracaoLicencaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarinaApplication.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class IntegracaoLicencaRepositoryJdbcTest {

    @Autowired
    private IntegracaoLicencaRepository repository;

    @Test
    public void testFindNaoProcessados() {

        final List<IntegracaoLicenca> results = repository.findNaoProcessados();

        assertThat(results.isEmpty(), is(false));
    }
}
