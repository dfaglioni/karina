package br.com.elotech.karina.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.service.LicenseService;
import br.com.elotech.karina.service.PushService;

@Service
public class PushServiceBlueMix implements PushService {

    private static final String SERVER_PROP = "${karina.licenseServerUrl:http://license.elotech.com.br/}";

    private static final Logger LOGGER = LoggerFactory.getLogger(PushServiceBlueMix.class);

    private final LicenseService licenseService;
    private final RestTemplate restTemplate;
    private final String licenseUrl;

    public PushServiceBlueMix(LicenseService licenseService, RestTemplate restTemplate,
            @Value(SERVER_PROP) String licenseUrl) {

        this.licenseService = licenseService;
        this.restTemplate = restTemplate;
        this.licenseUrl = licenseUrl;
    }

    @Override
    @Scheduled(fixedDelay = 3000)
    public void push() {

        final List<License> licenses = this.licenseService.processar();

        if (CollectionUtils.isEmpty(licenses)) {
            return;
        }

        LOGGER.debug("Preparing push to license server: {}", licenses);

        final List<License> result = new ArrayList<>();

        licenses.forEach(l -> {
            result.add(this.restTemplate.postForObject(this.licenseUrl + "/licenses", l, License.class));
        });

        LOGGER.info("Result from POST: {}", result);
    }
}
