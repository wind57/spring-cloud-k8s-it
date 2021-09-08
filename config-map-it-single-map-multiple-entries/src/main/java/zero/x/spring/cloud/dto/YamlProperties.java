package zero.x.spring.cloud.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("from.yaml")
public class YamlProperties {

    private String key1;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }
}
