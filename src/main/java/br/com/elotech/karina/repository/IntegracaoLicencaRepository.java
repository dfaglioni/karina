package br.com.elotech.karina.repository;

import java.util.List;

import br.com.elotech.karina.domain.IntegracaoLicenca;

public interface IntegracaoLicencaRepository {

    List<IntegracaoLicenca> findNaoProcessados();

    void marcaTodosProcessados();
}
