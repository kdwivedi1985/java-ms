package org.test.demo.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataBase {

    ArrayList<User> users;

    UserDataBase(){

        users = new ArrayList<User>();

        User user1 = new User();
        user1.setUserid(5001);
        user1.setUsername("Kamal");

        Address address1 = new Address();
        address1.setAddressType("Home");
        address1.setAddress1("15510 Ranch Rd");
        address1.setAddress2("Apt 50234");
        address1.setCity("Dallas");
        address1.setState("TX");
        address1.setZip(12131);

        Address address2 = new Address();
        address2.setAddressType("Office");
        address2.setAddress1("3456 West Parmer Ln");
        address2.setCity("San Francisco");
        address2.setState("CA");
        address2.setZip(53453);

        ArrayList<Address> addresses = new ArrayList<Address>();
        addresses.add(address1);
        addresses.add(address2);

        user1.setAddresses(addresses);

        users.add(user1);

    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUser(User users) {
        this.users.add(users);
    }
}
