package com.data.wrapper;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.data.Roles;
import com.data.User;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class UserWrapper {

	public UserWrapper() {
	}

	public UserWrapper(User user) {
		this();
		if (user != null) {
			this.username = user.getId();
			this.roles = user.getRoles();
		}
	}

	public User join(User user) {
		if (username != null)
			user.setId(username);
		if (roles != null)
			user.setRoles(roles);
		return user;
	}

	@JsonProperty(required = true)
	@NotNull
	@Size(max = 200)
	private String username;
	@JsonProperty(required = true)
	private long id;
	@JsonProperty(required = true)
	private List<Roles> roles;
	
}
