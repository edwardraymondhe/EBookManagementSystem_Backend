package com.dawn.ebms.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserColl {

    public UserColl(Integer userId, String userIcon, String userIntro)
    {
        this.userId = userId;
        this.userIcon = userIcon;
        this.userIntro = userIntro;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    private ObjectId id;

    private Integer userId;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    private String userIcon;

    private String userIntro;
}

