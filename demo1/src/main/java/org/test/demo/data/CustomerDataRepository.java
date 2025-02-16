package org.test.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.demo.data.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CustomerDataRepository extends JpaRepository<Customer, Integer> {

    //Method name should has name of attribute
    List<Customer> findByNameContainingIgnoreCase(String customerName);

    Optional<Customer> findByEmailAndPassword(String email, String password);


}
