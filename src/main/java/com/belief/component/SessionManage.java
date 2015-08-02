package com.belief.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManage {
    boolean isLogin(HttpServletRequest request, HttpServletResponse response);
}
