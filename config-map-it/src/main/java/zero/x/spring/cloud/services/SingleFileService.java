package zero.x.spring.cloud.services;

import org.springframework.stereotype.Component;
import zero.x.spring.cloud.dto.SingleFileProperties;

@Component
public class SingleFileService {

    private final SingleFileProperties properties;

    public SingleFileService(SingleFileProperties properties) {
        this.properties = properties;
    }

    public String getKey1() {
        return properties.getKey1();
    }

    public String getKey2() {
        return properties.getKey2();
    }
}
