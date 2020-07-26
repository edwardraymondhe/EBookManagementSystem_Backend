package com.dawn.ebms.service;

import com.dawn.ebms.entity.User;

import java.util.List;


public interface UserService {
    User findUserById(int id);
    Integer userCheck(String username, String password);
    User userRegister(String username, String password, String email);
    List<User> getUsers();
    Integer updateUser(Integer type,Integer id, String value);

}
