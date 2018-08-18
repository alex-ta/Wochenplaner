package com.dao;

import com.data.Roles;
import com.data.User;

public class UserDao extends Dao<User>{

	@Override
	Class<User> getDaoClass() {		
		return User.class;
	}
	
	public void initAdmin(String username, String password){
		User o = new User();
		o.setId(username);
		o.setPassword(password);
		o.setRoles(Roles.ROLE_ADMIN);
		this.save(o);
	}
	
}
