package br.com.elotech.karina;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.elotech.karina.config.ElotechApplication;

@SpringBootApplication
@EnableScheduling
public class KarinaApplication {

    public static void main(String[] args) throws UnknownHostException {
        ElotechApplication.run(KarinaApplication.class, args);
    }
}
