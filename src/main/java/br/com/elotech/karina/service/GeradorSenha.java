package br.com.elotech.karina.service;

import br.com.elotech.karina.domain.License;

public interface GeradorSenha {

    public String generate(License license);

}
