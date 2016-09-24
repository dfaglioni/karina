package br.com.elotech.karina.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

/**
 * Utility class to load a Spring profile to be used as default when there is no
 * <code>spring.profiles.active</code> set in the environment or as command line
 * argument. If the value is not available in <code>application.yml</code> then
 * <code>dev</code> profile will be used as default.
 */
public final class DefaultProfileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultProfileUtil.class);

    private static final String SPRING_PROFILE_ACTIVE = "spring.profiles.active";

    private static final Optional<Properties> BUILD_PROPERTIES = readProperties();

    private DefaultProfileUtil() {

    }

    /**
     * Get a default profile from <code>application.yml</code>.
     */
    public static String getDefaultActiveProfiles() {
        if (BUILD_PROPERTIES.isPresent()) {
            String activeProfile = BUILD_PROPERTIES.get().getProperty(SPRING_PROFILE_ACTIVE);
            if (activeProfile != null && !activeProfile.isEmpty()
                    && (activeProfile.contains(Profiles.DEV) || activeProfile.contains(Profiles.PRODUCTION))) {
                return activeProfile;
            }
        }

        LOG.warn("No Spring profile configured, running with default profile: {}", Profiles.DEV);

        return Profiles.DEV;
    }

    /**
     * Set a default to use when no profile is configured.
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        /*
         * The default profile to use when no other profiles are defined This
         * cannot be set in the <code>application.yml</code> file. See
         * https://github.com/spring-projects/spring-boot/issues/1219
         */
        defProperties.put(SPRING_PROFILE_ACTIVE, getDefaultActiveProfiles());

        app.setDefaultProperties(defProperties);
    }

    /**
     * Load application.yml from classpath.
     */
    private static Optional<Properties> readProperties() {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(new ClassPathResource("/application.yml"));
            return Optional.ofNullable(factory.getObject());

            // factory.getObject() pode retornar raw Exception em caso de erro.
        } catch (Exception e) { // NOPMD
            LOG.error("Failed to read application.yml to get default profile");
        }
        return Optional.empty();
    }

}
