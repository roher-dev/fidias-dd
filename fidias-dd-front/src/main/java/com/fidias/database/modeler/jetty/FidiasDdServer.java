package com.fidias.database.modeler.jetty;

import static java.util.EnumSet.of;
import static javax.servlet.DispatcherType.REQUEST;
import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;
import static org.slf4j.LoggerFactory.getLogger;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import com.despegar.library.routing.filter.RoutingFilter;

public class FidiasDdServer {
private static final Logger LOGGER = getLogger(FidiasDdServer.class);
	
	private static final Integer DEFAULT_PORT = 9290;
	private static final String DEFAULT_ENV = "dev";
	private static final String APP_PATH_PREFIX = "/fidias-dd";
	private static final String SERVICE_APP_PATH = APP_PATH_PREFIX + "/*";
	@SuppressWarnings("unused")
	private static final String APP_PATH = APP_PATH_PREFIX + "/*.html";
	private static final String APP_PATH_BETA = APP_PATH_PREFIX + "-beta/*";
	private static final String APP_SERVLET = "classpath:spring-servlet.xml";

	public static void main(String[] args) throws Exception {
		try {
			LOGGER.debug("empezando con el global para ver si funca");
			Integer port = DEFAULT_PORT;
			if (args.length > 0) {
				port = Integer.valueOf(args[0]);
			}
			String environment = DEFAULT_ENV;
			if (args.length == 2) {
				environment = args[1];
			}

			DispatcherServlet dispatcherServlet = new DispatcherServlet();
			dispatcherServlet.setContextConfigLocation(APP_SERVLET);
			ServletContextHandler handler = getServletContextHandler(dispatcherServlet, environment);
			handler.setContextPath("/");
			handler.setResourceBase("src/main/resources/public");
			handler.addFilter(new FilterHolder(new RoutingFilter()), "/*",
					of(REQUEST));
//			DelegatingFilterProxy delegatingFilter = new DelegatingFilterProxy("securityFilter");
//            delegatingFilter.setServletContext(handler.getServletContext());
//            handler.addFilter(new FilterHolder(delegatingFilter), "/*", of(REQUEST));
			
			ResourceHandler resourceHandler = new ResourceHandler();
			resourceHandler.setDirectoriesListed(true);
			resourceHandler.setResourceBase(".");
			
			Server server = new Server(port);
			HandlerList handlerList = new HandlerList();
			handlerList.setHandlers(new Handler[]{resourceHandler, handler});
			server.setHandler(handlerList);
			server.start();
			server.join();

		} catch (Exception e) {
			LOGGER.error("Error al ejecutar el GLOBAL", e);
		}
	}

	private static ServletContextHandler getServletContextHandler(
			DispatcherServlet app, String environment) {
		ServletContextHandler handler = new ServletContextHandler(SESSIONS);
		handler.addEventListener(new ContextLoaderListener());
		handler.setInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
		
		ServletHolder servlet = new ServletHolder(app);
		switch (environment) {
		case DEFAULT_ENV:
			handler.addServlet(servlet, SERVICE_APP_PATH);
			break;
		case "prod":
			handler.addServlet(servlet, SERVICE_APP_PATH);
			break;
		default:
			handler.addServlet(servlet, APP_PATH_BETA);
			break;
		}

		return handler;
	}
}
