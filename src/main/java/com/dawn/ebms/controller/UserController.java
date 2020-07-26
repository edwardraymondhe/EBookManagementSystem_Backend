package com.dawn.ebms.controller;

import com.dawn.ebms.entity.User;
import com.dawn.ebms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser")
    public User findUserByUserId(@RequestParam("userId") Integer id)
    {
        System.out.println("Searching User:" + id);
        User user =  userService.findUserById(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/getUsers")
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @RequestMapping(value = "/updateUser")
    public Integer updateBook(@RequestParam("setType") Integer type, @RequestParam("userId") Integer id, @RequestParam("value") String value)
    {
        System.out.println(type);
        System.out.println(id);
        System.out.println(value);
        return userService.updateUser(type, id, value);
    }
}
