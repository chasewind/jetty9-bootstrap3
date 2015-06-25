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
	public ModelAndView handleException(HttpServletRequest req,
			Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp",
				new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		mav.setViewName("exception");
		return mav;
	}

}