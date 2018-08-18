package com.data;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name = "user")
@Proxy(lazy = false)
public class User {

	private String id;
	private String password;
	private List<Roles> roles;
	
	public User() {
		this.roles = new LinkedList<Roles>();
	}

	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@ElementCollection(targetClass = Roles.class)
	@CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	public List<Roles> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	public void setRoles(Roles...roles) {
		for(Roles r : roles) {
			this.roles.add(r);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
