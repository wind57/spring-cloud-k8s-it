package zero.x.spring.cloud.services;

import org.springframework.stereotype.Service;
import zero.x.spring.cloud.dto.YamlProperties;

@Service
public class YamlService {

    private final YamlProperties properties;

    public YamlService(YamlProperties properties) {
        this.properties = properties;
    }

    public String getYamlProperty() {
        return properties.getKey1();
    }
}
