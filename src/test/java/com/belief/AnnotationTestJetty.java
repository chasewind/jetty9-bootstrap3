package com.belief;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.WebApplicationInitializer;

import com.live.cconfig.WebAppInitializer;

/**
 * 基于注解，完全脱离<b>web.xml</b><br>
 * 
 * @author 于东伟
 *
 */
public class AnnotationTestJetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.setErrorHandler(null);
        // http://www.blogjava.net/DLevin/archive/2014/05/06/413129.html
        webAppContext.setConfigurations(new Configuration[] {new AnnotationConfiguration() {
            @Override
            public void preConfigure(final WebAppContext context) throws Exception {

                /************ new version *********/
                ConcurrentHashSet<String> configSet = new ConcurrentHashSet<String>();
                configSet.add(WebAppInitializer.class.getName());
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
