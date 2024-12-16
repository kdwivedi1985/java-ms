package org.test.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PhoenixApplicationStarter {

    public static void main(String[] args) {
        //SpringApplication.run(DemoApplication.class, args);

        new SpringApplicationBuilder().profiles(args[0]).sources(PhoenixApplicationStarter.class).run(args);
    }

    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
