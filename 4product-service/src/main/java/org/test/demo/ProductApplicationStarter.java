package org.test.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
@RestController
public class ProductApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplicationStarter.class, args);

        //new SpringApplicationBuilder().profiles(args[0]).sources(PhoenixApplicationStarter.class).run(args);
    }

    @RequestMapping("/serviceproduct")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
