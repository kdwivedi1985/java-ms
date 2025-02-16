package org.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.demo.data.Customer;
import org.test.demo.data.CustomerDataRepository;
import org.test.demo.data.LoginPojo;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer Service", description = "Customer Service APIs")
@RestController
@RequestMapping("/api/v1")
public class CustomerServiceController {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceController.class);
    @Autowired
    CustomerDataRepository customerDataRepository;


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        log.info("Ping Invoked!!!");
        return ResponseEntity.status(HttpStatus.OK).body("Service is UP!!");
    }


    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody Customer customer) {
        try {
            customerDataRepository.save(customer);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer can't be registered!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer Record Created!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {

        log.info("Inside Login Method!!"+ customer.getEmail() +", "+ customer.getPassword());
        try {
            if (null != customer){
                String email = customer.getEmail();
                String password = customer.getPassword();

                Optional<Customer> user = customerDataRepository.findByEmailAndPassword(email, password);
                log.info("Data Fetched!!"+ user);
                if (user.isPresent()) {
                    return ResponseEntity.status(HttpStatus.OK).body("Authentication Successful!!");
                } else {
                    return ResponseEntity.status(404).body(null);  // Or send an error message
                }
            }
        }catch (Exception e){
            log.info("Exception!!"+ e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer can't be Fetched!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input data is not correct!!");
    }

    @GetMapping("/findbycustomerid/{id}")
    public ResponseEntity<Customer> getUser1(@PathVariable Integer id) {

        log.info("Request received for::"+id);

        Customer customer = customerDataRepository.findById(id).orElse(null);

        if (customer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

        @GetMapping("/findbycustomername/{customername}")
        public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String customername) {
            log.info("Request received for::"+customername);

            List<Customer> customers =  customerDataRepository.findByNameContainingIgnoreCase(customername);

            if(!customers.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(customers);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }


    }
}
