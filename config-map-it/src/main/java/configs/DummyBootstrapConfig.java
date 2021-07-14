package configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import zero.x.spring.cloud.services.DummyBootstrapHealthIndicator;

@Import(value = DummyImportedBootstrapConfiguration.class)
@Configuration
public class DummyBootstrapConfig {

    @Autowired
    private Environment env;

    @ConditionalOnEnabledHealthIndicator("minebootstrap")
    @Bean
    public DummyBootstrapHealthIndicator service() {
        return new DummyBootstrapHealthIndicator();
    }

}
