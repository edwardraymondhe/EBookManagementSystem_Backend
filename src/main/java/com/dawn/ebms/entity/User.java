package com.dawn.ebms.entity;


import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String name;
    private String nickName;
    private String password;
    private String tel;
    private String address;
    private String email;
    private Integer userType;
    private Integer permission;


    public Integer getUserId() { return userId;}
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission){
        this.permission = permission;
    }

    @Transient
    private UserColl userColl;

    public UserColl getUserColl() {
        return userColl;
    }

    public void setUserColl(UserColl userColl) {
        this.userColl = userColl;
    }
}
