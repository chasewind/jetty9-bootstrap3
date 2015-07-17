package com.belief.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomiseMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(CustomiseMappingExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView();
		logger.error("request.getRequestURI():" + request.getRequestURI());
		logger.error("Exception:" + ex.getLocalizedMessage());
		mv.addObject("timestamp", new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		mv.setViewName("exception");
		return mv;
	}

	// @Override
	// public void setExceptionMappings(Properties mappings) {
	// mappings.setProperty("java.lang.Exception", "errors/exception");
	// mappings.setProperty("java.lang.Throwable", "errors/error");
	// super.setExceptionMappings(mappings);
	// }
	//
	// @Override
	// public void setStatusCodes(Properties statusCodes) {
	// statusCodes.setProperty("errors/exception", "500");
	// statusCodes.setProperty("errors/error", "404");
	// super.setStatusCodes(statusCodes);
	// }
	//
	// @Override
	// public void setDefaultStatusCode(int defaultStatusCode) {
	// super.setDefaultStatusCode(HttpServletResponse.SC_NOT_FOUND);
	// }

}
