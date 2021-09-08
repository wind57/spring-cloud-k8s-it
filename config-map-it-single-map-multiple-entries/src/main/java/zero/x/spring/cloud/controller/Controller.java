package zero.x.spring.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zero.x.spring.cloud.services.NonSpringService;
import zero.x.spring.cloud.services.PropertiesService;
import zero.x.spring.cloud.services.YamlService;

@RestController
public class Controller {

    private final YamlService yamlService;
    private final PropertiesService propertiesService;
    private final NonSpringService nonSpringService;

    public Controller(YamlService yamlService, PropertiesService propertiesService, NonSpringService nonSpringService) {
        this.yamlService = yamlService;
        this.propertiesService = propertiesService;
        this.nonSpringService = nonSpringService;
    }

    @GetMapping("/yaml")
    public String yaml() {
        return yamlService.getYamlProperty();
    }

    @GetMapping("/props")
    public String properties() {
        return propertiesService.getProperty();
    }

    @GetMapping("non-spring")
    public String nonSpring() {
        return nonSpringService.getProperty();
    }
}
