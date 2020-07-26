package com.dawn.ebms.serviceimpl;

import com.dawn.ebms.dao.UserDao;

import com.dawn.ebms.entity.*;
import com.dawn.ebms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    public Integer userCheck(String username, String password)
    {
        User fetchUser = userDao.userCheck(username);

        // No such user
        if(fetchUser == null) {
            System.out.println("No such user");
            return -1;
        }

        // Have such user
        String fetchPassword = fetchUser.getPassword();
        if(fetchPassword.equals(password))
        {
            // Password correct
            System.out.println("Password correct.");
            if(fetchUser.getPermission() == 0) {
                // User not locked
                System.out.println("User not locked.");
                return fetchUser.getUserId();
            }else{
                // User locked
                System.out.println("User locked.");
                return -2;
            }
        }
        else {
            // Password incorrect
            System.out.println("Password incorrect.");
            return -3;
        }

    }

    public User userRegister(String username, String password, String email) {

        Integer fetchUser = userCheck(username, password);
        if(fetchUser != -1) {
            System.out.println("User exists.");
            return null;
        }
        else
        {
            System.out.println("User doesn't exist.");
            return userDao.userRegister(username, password, email);
        }
    }

    public Integer updateUser(Integer type,Integer id, String value){
        return userDao.updateUser(type,id, value);
    }

    public List<User> getUsers()
    {
        return userDao.getUsers();
    }
}
