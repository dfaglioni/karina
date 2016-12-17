package br.com.elotech.karina.service.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.domain.Module;

public class GeradorSenhaMD5Test {

    private GeradorSenhaElotech senhaMD5 = new GeradorSenhaElotech();

    @Test
    public void maringaTributos() {

        String senha = senhaMD5.generate(
                License.builder().module(Module.AISE_TRIBUTOS).code("306137001").date(LocalDate.of(2016, 12, 15))
                        .name("PREFEITURA DO MUNICIPIO DE MARINGA").build());

        assertThat(senha, equalTo("486877644D6F744D"));

    }

    /*
     * KeySource '15/12/2016PREFEITURA DO MUNICIPIO DE MARINGA306137001' DELPHI
     * '15/12/2016PREFEITURA DO MUNICIPIO DE MARINGA306137001' JAVA
     * 
     * MD5 404229D7D58EF5C9DAF4613EC604BE27 404229D7D58EF5C9DAF4613EC604BE27
     * 
     * SHA1 5B153A2BCDDC7294290729F06AD9A7C71D2D92A3
     * 5B153A2BCDDC7294290729F06AD9A7C71D2D92A3
     * 
     * IDEA
     * D4lwHO+ih0xJebcjfhXyiis3/EW24ssjVDIdxvzFAePqmuUt+aOXq/2DUwuZxJ4p24iLJ5s=
     * 
     * HEX
     * 44346C77484F2B696830784A6562636A66685879696973332F4557323473736A5644496478767A46416550716D7555742B614F58712F32445577755A784A34703234694C4A35733D
     * 
     * 
     */

}
