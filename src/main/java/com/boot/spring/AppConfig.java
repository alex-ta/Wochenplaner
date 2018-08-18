package com.boot.spring;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.dao.EntryDao;
import com.dao.UserDao;
import com.security.UserSecurityService;

@Configuration
@ComponentScan({ "com.security", "com.boot.spring", "com.boot", "com.webservices", "com.service.exceptions" })
@Import({ SecurityConfig.class })
public class AppConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		threadPoolTaskScheduler();
		return viewResolver;
	}

	
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.initialize();
		threadPoolTaskScheduler.setPoolSize(1);
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		Runnable task = new Runnable() {
			@Override
			public void run() {
				Date next = entryDao().updateEntries();
				System.out.println("Updating Table with Scheduler");
				System.out.println("Current Size: "+entryDao().getAll().size());
			}
		};
		threadPoolTaskScheduler.scheduleAtFixedRate(task, 864000000);
		return threadPoolTaskScheduler;
	}
		
	@Bean
	public EntryDao entryDao() {
		return new EntryDao();
	}

	@Bean
	public UserDao userDao() {
		return new UserDao();
	}

	@Bean
	public UserSecurityService userSecurityService() {
		return new UserSecurityService();
	}

}
