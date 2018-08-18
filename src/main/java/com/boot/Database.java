package com.boot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
	
	private static Database instance = null;
	private SessionFactory sessionFactory;
	private Configuration config;
	
	private Database(){
		// Load Drivers
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	public static Database getDatabase(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}
	
	public void closeDatabase(){
		sessionFactory.close();
	}
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	
	
} 