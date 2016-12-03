package br.com.elotech.karina.service.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;

public class GeradorSenhaMD5Test {

    private GeradorSenhaMD5 senhaMD5 = new GeradorSenhaMD5();

    @Test
    public void maringaTributos() {

        String senha = senhaMD5.generate(
                License.builder().module(Module.AISE_TRIBUTOS).code("306137001").date(LocalDate.of(2016, 12, 15))
                        .name("PREFEITURA DO MUNICIPIO DE MARINGA").build());

        assertThat(senha, equalTo("486877644D6F744D"));

    }

}
