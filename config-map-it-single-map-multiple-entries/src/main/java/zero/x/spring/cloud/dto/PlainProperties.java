package zero.x.spring.cloud.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("from.properties")
public class PlainProperties {

    private String key2;

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }
}
