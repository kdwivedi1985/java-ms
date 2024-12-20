package org.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {

    @Autowired
    UserDataMap userdata;
    @Autowired
    private UserDataMap userDataMap;


    @GetMapping("/map/{userid}")
    public Map<String,String> getUser1(@PathVariable Integer userid) {

        Map<String,String> map = new HashMap<>();

        if (userdata.getUser(userid) != null){
            map.put("userid",userid.toString());
            map.put("username",userdata.getUser(userid));
        }
        else{
            map.put("userid",userid.toString());
            map.put("username","No Found!");
        }
        return map;
    }

    @GetMapping("json/{userid}")
    public ResponseEntity<Object> getUser2(@PathVariable Integer userid) {

        Map<String,String> map = new HashMap<>();

        if (userdata.getUser(userid) != null){
            map.put("userid",userid.toString());
            map.put("username",userdata.getUser(userid));
            return ResponseEntity.ok(map);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        userDataMap.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Record Created!");
    }
}
