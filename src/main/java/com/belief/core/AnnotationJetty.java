package com.belief.core;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.WebApplicationInitializer;

import com.belief.config.WebAppInitializer;

/**
 * 基于注解，完全脱离<b>web.xml</b><br>
 * 参考 https://github.com/steveliles/jetty-embedded-spring-mvc-noxml<br>
 * http://stackoverflow.com/questions/13222071/spring-3-1-
 * webapplicationinitializer-embedded-jetty-8-annotationconfiguration
 * 
 * @author 于东伟
 *
 */
public class AnnotationJetty {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setContextPath("/");
		webAppContext
				.setConfigurations(new Configuration[] { new AnnotationConfiguration() {
					@Override
					public void preConfigure(final WebAppContext context)
							throws Exception {
						MultiMap<String> map = new MultiMap<String>();
						map.add(WebApplicationInitializer.class.getName(),
								WebAppInitializer.class.getName());
						context.setAttribute(CLASS_INHERITANCE_MAP, map);
						_classInheritanceHandler = new ClassInheritanceHandler(
								map);
					}
				} });
		webAppContext.setParentLoaderPriority(true);

		server.setHandler(webAppContext);
		server.start();
		server.join();
	}
}