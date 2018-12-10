package com.luffy.tencentimlib.model;

import java.io.Serializable;

/**
 * Created by lvlufei on 2018/11/23
 *
 * @desc 群组成员信息
 */
public class ImGroupMemberBean implements Serializable {

    private String userId;//用户ID
    private String userName;//用户昵称
    private String userUrl;//用户头像

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
