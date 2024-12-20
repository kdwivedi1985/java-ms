package org.test.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDataMap {
    private Map users = new HashMap<Integer, String>();

    UserDataMap(){
        users.put(10001,"Kamal");
        users.put(10002,"Nawal");
        users.put(10003,"Soni");
        users.put(10004,"Alok");
        users.put(10005,"Aavya");
        users.put(10006,"Divisha");
    }

    public Map getUsers() {
        return users;
    }

    public String getUser(int userid) {
        if(users.containsKey(userid)) {
            return users.get(userid).toString();
        }
         return null;
    }

    public void setUser(User user) {
        this.users.put(user.getUserid(), user.getUsername());
    }
}
