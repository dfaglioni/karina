package br.com.elotech.karina.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public final class ElotechApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ElotechApplication.class);

    private ElotechApplication() {

    }

    /**
     * Verifica conflitos entre os profiles ativos.
     * 
     */
    public static void checkProfiles(Environment env) {
        LOG.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));

        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        if (activeProfiles.contains(Profiles.DEV) && activeProfiles.contains(Profiles.PRODUCTION)) {
            LOG.error("You have misconfigured your application! It should not run "
                    + "with both the 'dev' and 'prod' profiles at the same time.");
        }

        if (activeProfiles.contains(Profiles.DEV)) {
            LOG.error("You have misconfigured your application! It should not"
                    + "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Method used to run the application.
     * 
     * @param applicationClass The Spring Boot Application Class
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved
     *             into an address
     */
    public static void run(Class<?> applicationClass, String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(applicationClass);

        DefaultProfileUtil.addDefaultProfile(app);

        Environment env = app.run(args).getEnvironment();

        ElotechApplication.checkProfiles(env);

        LOG.info("\n----------------------------------------------------------\n\t"
                + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
                + "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));

    }
}
