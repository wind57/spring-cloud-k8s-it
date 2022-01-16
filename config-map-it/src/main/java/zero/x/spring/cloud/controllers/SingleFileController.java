package zero.x.spring.cloud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zero.x.spring.cloud.services.SingleFileService;

@RestController
public class SingleFileController {

    private final SingleFileService service;

    public SingleFileController(SingleFileService service) {
        this.service = service;
    }

    @GetMapping("/key1")
    public String key1() {
        return service.getKey1();
    }

    @GetMapping("/key2")
    public String key2() {
        return service.getKey2();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
