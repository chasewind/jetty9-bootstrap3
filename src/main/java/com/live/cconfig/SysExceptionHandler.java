package com.live.cconfig;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@EnableWebMvc
//@ControllerAdvice(basePackages = "com.belief")
public class SysExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception exception) {
		logger.error(exception.getLocalizedMessage());
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String statusCode = (String) request.getAttribute("javax.servlet.error.status_code");
		String exceptionType = (String) request.getAttribute("javax.servlet.error.exception_type");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		logger.error("requestUri:" + requestUri);
		logger.error("servletName:" + servletName);
		logger.error("throwable:" + (throwable == null ? "" : throwable.getLocalizedMessage()));
		logger.error("statusCode:" + statusCode);
		logger.error("exceptionType:" + exceptionType);
		logger.error("message:" + message);
		mav.setViewName("exception");
		return mav;
	}

}