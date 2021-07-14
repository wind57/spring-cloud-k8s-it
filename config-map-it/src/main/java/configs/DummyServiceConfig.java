package configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import zero.x.spring.cloud.services.DummyBootstrapService;

import java.util.Map;

@Configuration
public class DummyServiceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DummyBootstrapService anotherDummyService(Map<String, HealthContributor> map) {
        return new DummyBootstrapService();
    }

}
