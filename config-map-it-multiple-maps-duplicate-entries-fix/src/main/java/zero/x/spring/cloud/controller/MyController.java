package zero.x.spring.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zero.x.spring.cloud.service.MyService;

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/prop")
    public String prop() {
        return myService.property();
    }
}
