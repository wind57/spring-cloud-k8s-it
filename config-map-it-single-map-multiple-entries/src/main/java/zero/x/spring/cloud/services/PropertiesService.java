package zero.x.spring.cloud.services;

import org.springframework.stereotype.Service;
import zero.x.spring.cloud.dto.PlainProperties;

@Service
public class PropertiesService {

    private final PlainProperties properties;

    public PropertiesService(PlainProperties properties) {
        this.properties = properties;
    }

    public String getProperty() {
        return properties.getKey2();
    }
}
