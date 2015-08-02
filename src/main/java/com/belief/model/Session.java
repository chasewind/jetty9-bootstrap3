package com.belief.model;

import java.util.Date;

import com.belief.module.model.BaseDomain;

public class Session extends BaseDomain {

    private static final long serialVersionUID = -5738626994741244454L;

    private String sessionId;
    private boolean isLogin = false;
    private String userName;
    private Date accessTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }



}
