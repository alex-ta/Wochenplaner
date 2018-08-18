package com.webservices;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.UserDao;
import com.data.User;
import com.service.exceptions.E;
import com.service.exceptions.E500;

@Controller
@RequestMapping("/v1/users")
public class UserService {
	
	public static String baseUrl = "/v1/users";
	private UserDao dao;
	
	@Autowired
	public UserService(UserDao d) {
		this.dao = d;
	}
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<List<String>> getAll() {
		try {
			List<String> list = new LinkedList<String>();
			for(User u : dao.getAll()) {
				list.add(u.getId());
			}
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (E e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new E500("Ups something went wrong");
		}
	}

}
