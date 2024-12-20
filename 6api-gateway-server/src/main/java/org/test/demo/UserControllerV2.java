package org.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.test.demo.data.User;
import org.test.demo.data.UserDataBase;

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    @Autowired
    UserDataBase userDataBase;

    @GetMapping("json/{userid}")
    public ResponseEntity<Object> getUser2(@PathVariable Integer userid) {

        for (User user : userDataBase.getUsers()){
            if (user.getUserid().equals(userid)){
                return ResponseEntity.ok(user);
            }
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        userDataBase.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Record Created!");
    }
}
