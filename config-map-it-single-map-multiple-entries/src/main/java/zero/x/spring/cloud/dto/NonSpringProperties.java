package zero.x.spring.cloud.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("no-name")
public class NonSpringProperties {

    private String no_extension;

    public String getNo_extension() {
        return no_extension;
    }

    public void setNo_extension(String no_extension) {
        this.no_extension = no_extension;
    }
}
