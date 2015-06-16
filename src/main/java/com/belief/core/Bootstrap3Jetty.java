package com.belief.core;

import java.util.Arrays;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 
 * @author 于东伟
 *
 */
public class Bootstrap3Jetty {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		WebAppContext webapp = new WebAppContext();
		webapp.setResourceBase("src/main/webapp");
		webapp.setContextPath("/");
		webapp.setOverrideDescriptors(Arrays
				.asList(new String[] { Bootstrap3Jetty.class.getResource("/")
						+ "web_override_development.xml" }));
		server.setHandler(webapp);
		server.start();
		server.join();
	}
}
