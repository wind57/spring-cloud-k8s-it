package zero.x.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/dummy")
    public String dummy() {
        return "dummy";
    }

    @GetMapping("/discovery")
    public void discovery() {
        System.out.println(client.getServices());
    }

}
