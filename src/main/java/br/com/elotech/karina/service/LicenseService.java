package br.com.elotech.karina.service;

import java.util.*;

import br.com.elotech.karina.domain.*;

public interface LicenseService {

    List<License> processar(List<IntegracaoLicenca> integracaoLicenca);

    License getLicenseFrom(IntegracaoLicenca integracaoLicenca);

}
