package com.weten.ecole.controller;

import com.weten.ecole.model.User;
import com.weten.ecole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /*private UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }*/

    @GetMapping("/users/{userName}")
    public User getAUser(@PathVariable("userName") String userName){
        User user = userService.findUserByUserName(userName);
        return user;
    }
}
