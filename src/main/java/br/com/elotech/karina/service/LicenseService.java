package br.com.elotech.karina.service;

import java.util.List;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.domain.License;

public interface LicenseService {

    List<License> processar();

    License getLicenseFrom(IntegracaoLicenca integracaoLicenca);

}
