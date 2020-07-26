package com.dawn.ebms.dao;

import com.dawn.ebms.entity.User;

import java.util.List;

public interface UserDao {
    User findUserById(int id);
    User userCheck(String username);
    User userRegister(String username, String password, String email);
    Integer setUserPermissionByUserId(int id);
    List<User> getUsers();
    Integer updateUser(Integer type,Integer id, String value);

    Integer setUserNameByUserId(Integer id, String username);

    Integer setUserNickNameByUserId(Integer id, String nickname);

    Integer setUserPasswordByUserId(Integer id, String password);

    Integer setUserTelByUserId(Integer id, String tel);

    Integer setUserAddressByUserId(Integer id, String address);

    Integer setUserEmailByUserId(Integer id, String email);
}
