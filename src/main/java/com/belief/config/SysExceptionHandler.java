package com.belief.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice(basePackages = "com.belief")
public class SysExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(HttpServletRequest request) {
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String statusCode = (String) request.getAttribute("javax.servlet.error.status_code");
		String exceptionType = (String) request.getAttribute("javax.servlet.error.exception_type");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		logger.error(requestUri);
		logger.error(servletName);
		logger.error(throwable.getLocalizedMessage());
		logger.error(statusCode);
		logger.error(exceptionType);
		logger.error(message);
		ModelAndView mav = new ModelAndView();
		mav.addObject("requestUri", requestUri);
		mav.addObject("servletName", servletName);
		mav.addObject("throwable", throwable);
		mav.addObject("statusCode", statusCode);
		mav.addObject("exceptionType", exceptionType);
		mav.addObject("message", message);
		mav.addObject("timestamp",
				new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		mav.setViewName("exception");
		return mav;
	}

}