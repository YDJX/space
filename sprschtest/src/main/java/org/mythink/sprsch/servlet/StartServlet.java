package org.mythink.sprsch.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartServlet() {
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
//		config.getServletContext().getAttribute("");
		System.out.println("------------------------------------------------");
		System.out.println("------------------init(config)------------------");
		System.out.println("------------------------------------------------");
	}
}
