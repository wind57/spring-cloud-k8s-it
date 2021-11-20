package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private Environment environ;

    public void isAutoDetected() {

        ConfigurableEnvironment env = (ConfigurableEnvironment) environ;

        PropertySource<?> environmentPropertySource = env.getPropertySources().get("systemEnvironment");
        System.out.println("===== systemEnvironment is present");
        System.out.println("==== " + environmentPropertySource.getProperty("KUBERNETES_SERVICE_HOST"));
        System.out.println(" ==== " + environmentPropertySource.getProperty("KUBERNETES_SERVICE_PORT"));
    }

    public String key() {
        return environ.getProperty("awesome-key");
    }

}
