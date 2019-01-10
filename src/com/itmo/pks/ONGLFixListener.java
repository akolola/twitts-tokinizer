package com.itmo.pks;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ognl.OgnlRuntime;

public class ONGLFixListener implements ServletContextListener {

	public ONGLFixListener() {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		OgnlRuntime.setSecurityManager(null);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
