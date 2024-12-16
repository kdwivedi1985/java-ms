package org.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PriceServiceStarter {

    public static void main(String[] args) {
        SpringApplication.run(PriceServiceStarter.class, args);
    }

    @RequestMapping("/getprice")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        System.out.println("Hi This is get price service");
        return String.format("Hi This is get price service");
    }
}
