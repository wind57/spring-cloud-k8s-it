package zero.x.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zero.x.spring.cloud.dto.NonSpringProperties;
import zero.x.spring.cloud.dto.PlainProperties;
import zero.x.spring.cloud.dto.YamlProperties;

@EnableConfigurationProperties({
        YamlProperties.class,
        PlainProperties.class,
        NonSpringProperties.class
})
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
