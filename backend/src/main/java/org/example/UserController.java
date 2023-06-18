package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServices userServices;


    @GetMapping("/get-all-user")
    public List<User> getAllShifts() {
        return userServices.getAllUsers();
    }

    @PostMapping("/login")
    public User addShift(@RequestBody User user) {
        return userServices.createUser(user);
    }
}
