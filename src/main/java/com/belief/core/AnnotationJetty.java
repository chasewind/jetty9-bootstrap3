package com.belief.core;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ConcurrentHashSet;
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
        // http://www.blogjava.net/DLevin/archive/2014/05/06/413129.html
        // webAppContext.setErrorHandler(null);
        webAppContext.setConfigurations(new Configuration[] {new AnnotationConfiguration() {
            @Override
            public void preConfigure(final WebAppContext context) throws Exception {
                /*************** version 9.0.6 ****************/
                // MultiMap<String> map = new MultiMap<String>();
                // map.add(WebApplicationInitializer.class.getName(),
                // WebAppInitializer.class.getName());
                // context.setAttribute(CLASS_INHERITANCE_MAP, map);
                // _classInheritanceHandler = new
                // ClassInheritanceHandler(
                // map);
                /************ new version *********/
                ConcurrentHashSet<String> configSet = new ConcurrentHashSet<String>();
                configSet.add(WebAppInitializer.class.getName());
                // 类转换异常，导致一直报503错误
                // ConcurrentHashMap<String, ConcurrentHashSet<String>> configMap =
                // new ConcurrentHashMap<String, ConcurrentHashSet<String>>();
                ClassInheritanceMap configMap = new ClassInheritanceMap();
                configMap.put(WebApplicationInitializer.class.getName(), configSet);
                context.setAttribute(CLASS_INHERITANCE_MAP, configMap);
                _classInheritanceHandler = new ClassInheritanceHandler(configMap);

            }
        }});
        // webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}