package com.belief.component;

import java.util.Date;

import com.belief.model.Session;


public interface SessionDao {

    Session findSessionBySessionId(String sessionId);

    void deleteSessionBySessionId(String sessionId);

    void updateSessionAccessTimeBySessionId(String sessionId, Date time, String requestURI,
            String ipAddr);

}
