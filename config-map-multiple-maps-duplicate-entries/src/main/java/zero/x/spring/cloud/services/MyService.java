package zero.x.spring.cloud.services;

import org.springframework.stereotype.Service;
import zero.x.spring.cloud.dto.MyProperties;

@Service
public class MyService {

    private final MyProperties myProperties;

    public MyService(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    public String property() {
        return myProperties.getMykey1();
    }
}
