package com.belief.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belief.component.GlobalValue;

public class CookieUtil {
    public static String getSesssionIdFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (GlobalValue.LOGIN_SESSION_ID.equals(cookie.getName())) {
                String sid = cookie.getValue();
                return sid;
            }

        }

        return null;
    }

    public static void putSessionIdInCookies(HttpServletRequest request,
            HttpServletResponse response, String loginSessionKey, String sessionId) {
        Cookie cookie = new Cookie(loginSessionKey, sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
