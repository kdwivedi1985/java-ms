package org.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.demo.data.Customer;
import org.test.demo.data.CustomerDataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        return ResponseEntity.status(HttpStatus.OK).body("Service is UP!!");
    }


    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody Customer customer) {

        customerDataRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer Record Created!");
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
