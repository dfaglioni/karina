package br.com.elotech.karina.service.impl;

import org.springframework.stereotype.Service;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.service.GeradorSenha;

@Service
public class GeradorSenhaFake implements GeradorSenha {

    @Override
    public String generate(License license) {

        StringBuilder builder = new StringBuilder()
                .append(license.getModule().getCode()).append("-")
                .append(license.getName()).append("-")
                .append(license.getCode()).append("-")
                .append(license.getDate().toString());

        return builder.toString();
    }

}
