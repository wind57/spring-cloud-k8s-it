package zero.x.spring.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zero.x.spring.cloud.services.MyService;

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping(value = "/prop")
    public String property() {
       return myService.property();
    }

}
