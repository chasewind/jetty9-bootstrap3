package com.belief.security;

import javax.servlet.ServletRequest;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class ServletTest {

	@Test
	public void isServlet3() {
		System.out.println(ClassUtils.hasMethod(ServletRequest.class, "startAsync"));
	}
}
