package zero.x.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zero.x.spring.cloud.services.DummyBootstrapService;
import zero.x.spring.cloud.services.DummyContributor;

import java.util.Map;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
       Map<String, DummyContributor> map =  context.getBeansOfType(DummyContributor.class);
       System.out.println(map.size());

       Map<String, DummyBootstrapService> diffMap = context.getBeansOfType(DummyBootstrapService.class);
       System.out.println(diffMap.values().iterator().next());


    }
}
