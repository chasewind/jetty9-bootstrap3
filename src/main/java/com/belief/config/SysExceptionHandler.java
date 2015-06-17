package com.belief.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SysExceptionHandler extends ResponseEntityExceptionHandler {
	// @ResponseBody
	// @ExceptionHandler(value = Exception.class)
	// public ModelAndView handleError(HttpServletRequest req, Exception
	// exception) {
	// logger.error("Request: " + req.getRequestURL() + " raised " + exception);
	// ModelAndView mav = new ModelAndView();
	// mav.addObject("exception", exception);
	// mav.addObject("url", req.getRequestURL());
	// mav.addObject("timestamp",
	// new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
	// mav.setViewName("exception");
	// return mav;
	// }
	// @ExceptionHandler(value = { IllegalArgumentException.class,
	// IllegalStateException.class })
	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex,
			WebRequest request) {
		logger.error("Request:-------- ");
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
	}

	// @ResponseBody
	// @ExceptionHandler(value = Exception.class)
	// public Map<String, Object> exception(HttpServletRequest req, Exception e)
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	// return map;
	// }
}