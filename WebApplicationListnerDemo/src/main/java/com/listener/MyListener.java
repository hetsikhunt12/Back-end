package com.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 
    	sce.getServletContext().getAttribute("counter");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	sce.getServletContext().getAttribute("counter");
    }
	
}
