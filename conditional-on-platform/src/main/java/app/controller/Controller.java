package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.service.MyService;

//curl localhost:30008/test
@RestController
public class Controller {

    @Autowired
    private MyService service;

    @GetMapping("/test")
    public void yaml() {
        service.isAutoDetected();
    }

    @GetMapping("/key")
    public String key() {
        return service.key();
    }

}
