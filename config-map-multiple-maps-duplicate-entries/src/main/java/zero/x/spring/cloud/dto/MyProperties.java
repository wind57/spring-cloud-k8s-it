package zero.x.spring.cloud.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class MyProperties {

    private String mykey1;

    public String getMykey1() {
        return mykey1;
    }

    public void setMykey1(String mykey1) {
        this.mykey1 = mykey1;
    }
}
