package com.belief.component;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belief.model.Session;
import com.belief.module.model.User;
import com.belief.utils.web.CookieUtil;

@Component
public class SessionManageImpl implements SessionManage {
    private static Logger logger = LoggerFactory.getLogger(SessionManageImpl.class);
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getParameter(GlobalValue.LOGIN_SESSION_ID);
        if (sessionId == null || "".equals(sessionId.trim())) {
            sessionId = CookieUtil.getSesssionIdFromCookies(request);
        }
        Session session = sessionDao.findSessionBySessionId(sessionId);
        if (session == null || null == session.getSessionId()) {
            return false;
        }
        if (!session.isLogin()) {
            return false;
        }
        if (isTimeOut(session)) {
            sessionDao.deleteSessionBySessionId(sessionId);
            return false;
        }
        sessionDao.updateSessionAccessTimeBySessionId(sessionId, Calendar.getInstance().getTime(),
                request.getRequestURI(), getIpAddr(request));
        ThreadVariable.setSession(session);
        User user = permissionService.getSimpleUserByUserName(session.getUserName());
        ThreadVariable.setUser(user);
        CookieUtil.putSessionIdInCookies(request, response, GlobalValue.LOGIN_SESSION_ID,
                session.getSessionId());
        return true;
    }

    private boolean isTimeOut(Session session) {
        if ((Calendar.getInstance().getTime().getTime() - session.getAccessTime().getTime()) > GlobalValue.SESSION_TIME_OUT) {
            logger.info("用户：{}Session失效，上一次访问时间{}", session.getUserName(), session.getAccessTime());
            return true;
        }
        return false;
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
