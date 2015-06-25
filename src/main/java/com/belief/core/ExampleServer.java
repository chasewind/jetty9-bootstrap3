package com.belief.core;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Create a Server instance.
 * 
 * Add/Configure Connectors.
 * 
 * Add/Configure Handlers and/or Contexts and/or Servlets.
 * 
 * Start the Server.
 * 
 * Wait on the server or do something else with your thread.
 * 
 * @author ThinkPad
 *
 */
public class ExampleServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		// context.addServlet(HelloServlet.class, "/hello");
		// context.addServlet(AsyncEchoServlet.class, "/echo/*");
		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();
		server.join();
	}
}
