package com.belief.component;

import java.util.Date;

import com.belief.model.Session;


public class SessionDaoImpl implements SessionDao {

    @Override
    public Session findSessionBySessionId(String sessionId) {
        return null;
    }

    @Override
    public void deleteSessionBySessionId(String sessionId) {

    }

    @Override
    public void updateSessionAccessTimeBySessionId(String sessionId, Date time, String requestURI,
            String ipAddr) {}

}
