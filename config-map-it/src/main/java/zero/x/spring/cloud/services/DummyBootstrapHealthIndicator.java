package zero.x.spring.cloud.services;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class DummyBootstrapHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return null;
    }
}
