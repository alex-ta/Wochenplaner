package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dao.UserDao;
import com.data.User;
import com.data.wrapper.SecurityUserWrapper;

public class UserSecurityService implements UserDetailsService{

	@Autowired
	UserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("User: "+username);
		User u = dao.getById(username);
		if(u == null){
			return null;
		}
		System.out.println(u.getId()+" "+u.getPassword()+" "+u.getRoles()+"");
		return new SecurityUserWrapper(u);
	}

}
