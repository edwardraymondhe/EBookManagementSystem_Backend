package com.dawn.ebms.controller;

import com.dawn.ebms.entity.User;
import com.dawn.ebms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class UserAuthController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Integer loginCheck(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        // userId: USER EXISTS, Normal user
        // userId: USER EXISTS, Super user
        // -1: USER EXISTS, PASSWORD WRONG
        // -2: USER EXISTS, BUT LOCKED
        // -3: USER NOT EXISTS
        System.out.println(username);
        System.out.println(password);
        return userService.userCheck(username, password);
    }

    @RequestMapping(value = "/register")
    public User registerCheck(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email)
    {
        // User: USER NOT EXISTS
        // null: USER EXISTS
        System.out.println(username);
        System.out.println(password);
        User user =  userService.userRegister(username, password, email);
        System.out.println(user.getUserId());
        System.out.println(user.getName());
        return user;
    }

}
