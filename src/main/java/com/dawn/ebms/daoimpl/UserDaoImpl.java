package com.dawn.ebms.daoimpl;

import com.dawn.ebms.dao.UserDao;
import com.dawn.ebms.entity.*;
import com.dawn.ebms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCollRepository userCollRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;


    public User findUserById(int id) {
        User user =  userRepository.findById(id).get();
        UserColl userColl = userCollRepository.findUserCollByUserIdEquals(id).get();
        user.setUserColl(userColl);
        return user;
    }

    public User userCheck(String username) { return userAuthRepository.findByNameEquals(username); }

    public User userRegister(String username, String password, String email) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserType(1);
        user.setPermission(0);
        User newUser =  userRepository.save(user);
        userCollRepository.save(new UserColl(newUser.getUserId(), "", "Nothing from " + newUser.getName() + " yet."));
        return userRepository.findById(newUser.getUserId()).get();
    }

    public Integer updateUser(Integer type,Integer id, String value){
        switch (type)
        {
            case 1:
                return setUserPermissionByUserId(id);
            case 2:
                return setUserNameByUserId(id, value);
            case 3:
                return setUserNickNameByUserId(id, value);
            case 4:
                return setUserTelByUserId(id, value);
            case 5:
                return setUserAddressByUserId(id, value);
            case 6:
                return setUserEmailByUserId(id, value);
            case 7:
                return setUserPasswordByUserId(id, value);
            default:
                return 0;
        }
    }


    public Integer setUserPermissionByUserId(int id){
        User user = userRepository.findById(id).get();
        user.setPermission(user.getPermission() == 0?1:0);
        return userRepository.save(user).getPermission();
    }

    public Integer setUserNameByUserId(Integer id, String username){
        User user = userRepository.findById(id).get();
        user.setName(username);
        return userRepository.save(user).getName()==username?1:0;
    }

    public Integer setUserNickNameByUserId(Integer id, String nickname){
        User user = userRepository.findById(id).get();
        user.setNickName(nickname);
        return userRepository.save(user).getNickName()==nickname?1:0;
    }

    public Integer setUserPasswordByUserId(Integer id, String password){
        User user = userRepository.findById(id).get();
        user.setPassword(password);
        return userRepository.save(user).getPassword()==password?1:0;
    }

    public Integer setUserTelByUserId(Integer id, String tel){
        User user = userRepository.findById(id).get();
        user.setTel(tel);
        return userRepository.save(user).getTel()==tel?1:0;
    }

    public Integer setUserAddressByUserId(Integer id, String address){
        User user = userRepository.findById(id).get();
        user.setAddress(address);
        return userRepository.save(user).getAddress()==address?1:0;
    }

    public Integer setUserEmailByUserId(Integer id, String email){
        User user = userRepository.findById(id).get();
        user.setEmail(email);
        return userRepository.save(user).getEmail()==email?1:0;
    }

    public List<User> getUsers()
    {
        return userRepository.findUsersByUserTypeEquals(1);
    }
}
