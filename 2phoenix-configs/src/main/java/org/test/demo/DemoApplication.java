package org.test.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Value("${profile.name}")
    String activeprofilename;

    @Value("${spring.config.import}")
    String configStoreLoc;

    @Value("${keystore.repo.name}")
    String configStoreName;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //new SpringApplicationBuilder().profiles(args[0]).sources().run(args);
    }

    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/getprofilename")
    public String getActiveProfileName(){
        return activeprofilename;
    }

    @RequestMapping("/getconfigrepolocation")
    public String getConfigRepoLoc(){
        return configStoreLoc;
    }

    @RequestMapping("/getconfigstorename")
    public String getconfigStoreName(){
        return configStoreName;
    }

}
