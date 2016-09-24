package br.com.elotech.karina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.IntegracaoLicencaPk;

public interface IntegracaoLicencaRepository extends JpaRepository<IntegracaoLicenca, IntegracaoLicencaPk> {
}
