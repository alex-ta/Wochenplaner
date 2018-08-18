package com.boot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.UserDao;

public class BootLoader implements ServletContextListener{
	
	private Database base;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 base.closeDatabase();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		base = Database.getDatabase();
		UserDao d = new UserDao();
		d.initAdmin("Helene", "reimer");
		d.initAdmin("Elvira", "reimer");
		d.initAdmin("Emilia", "reimer");
	}
   
}