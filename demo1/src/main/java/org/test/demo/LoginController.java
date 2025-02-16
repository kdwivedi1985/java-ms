package org.test.demo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.demo.data.Customer;
import org.test.demo.data.CustomerDataRepository;

import java.util.List;

@Tag(name = "Login", description = "Login API")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        log.info("Ping Invoked!!!");
        return ResponseEntity.status(HttpStatus.OK).body("Service is UP!!");
    }
}
