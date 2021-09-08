package zero.x.spring.cloud.services;

import org.springframework.stereotype.Service;
import zero.x.spring.cloud.dto.NonSpringProperties;

@Service
public class NonSpringService {

    private final NonSpringProperties properties;

    public NonSpringService(NonSpringProperties properties) {
        this.properties = properties;
    }

    public String getProperty() {
        return properties.getNo_extension();
    }
}
