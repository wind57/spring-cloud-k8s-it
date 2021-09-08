package zero.x.spring.cloud.service;

import org.springframework.stereotype.Service;
import zero.x.spring.cloud.dto.MyProperties;

@Service
public class MyService {

    private final MyProperties properties;

    public MyService(MyProperties properties) {
        this.properties = properties;
    }

    public String property() {
        return properties.getKey1();
    }
}
