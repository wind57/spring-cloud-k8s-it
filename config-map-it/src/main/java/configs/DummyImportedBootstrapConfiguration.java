package configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zero.x.spring.cloud.services.DummyImportedBootstrapHealthIndicator;

@Configuration
public class DummyImportedBootstrapConfiguration {

    @Configuration
    protected static class InnerConfiguration {

        @Autowired
        private ApplicationContext context;

        @Bean
        public DummyImportedBootstrapHealthIndicator importedBootstrapHealthIndicator() {
            return new DummyImportedBootstrapHealthIndicator();
        }
    }

}
