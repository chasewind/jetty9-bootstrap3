package com.belief.test;

import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void test() {
		Properties statusCodes = new Properties();
		// 500
		statusCodes.setProperty("errors/exception", "500");
		// 404
		statusCodes.setProperty("errors/error", "404");
		for (Enumeration<?> enumeration = statusCodes.propertyNames(); enumeration.hasMoreElements();) {
			String viewName = (String) enumeration.nextElement();
			System.out.println(viewName);
			Integer statusCode = new Integer(statusCodes.getProperty(viewName));
			statusCodes.put(viewName, statusCode);
		}
	}
}
