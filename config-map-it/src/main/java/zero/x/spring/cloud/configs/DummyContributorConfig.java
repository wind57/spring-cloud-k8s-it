package zero.x.spring.cloud.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import zero.x.spring.cloud.services.DummyContributor;

@Configuration
public class DummyContributorConfig {

    @Autowired
    private Environment env;

    @ConditionalOnEnabledHealthIndicator("mine")
    @Bean
    public DummyContributor dummyContributor() {
        return new DummyContributor();
    }

}
