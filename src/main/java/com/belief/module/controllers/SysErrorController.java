package com.belief.module.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/errors")
public class SysErrorController {

	private static Logger logger = LoggerFactory.getLogger(SysErrorController.class);

	@RequestMapping("/exception")
	public ModelAndView getExceptions(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
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
		mv.setViewName("exception");
		return mv;
	}

	@RequestMapping("/error")
	public ModelAndView getErrors(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
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
		mv.setViewName("exception");
		return mv;
	}
}
