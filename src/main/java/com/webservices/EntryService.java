package com.webservices;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.EntryDao;
import com.dao.UserDao;
import com.data.Entry;
import com.data.wrapper.EntryWrapper;
import com.service.exceptions.E;
import com.service.exceptions.E400;
import com.service.exceptions.E500;

@Controller
@RequestMapping("/v1/entries")
public class EntryService {
	
	public static String baseUrl = "/v1/entries";
	@Autowired
	private EntryDao dao;
	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	public EntryService(EntryDao d) {
//		this.dao = d;
//	}
	
//	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = {
//			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	public @ResponseBody ResponseEntity<List<String>> getAll() {
//		try {
//			return new ResponseEntity<List<String>>(getWrapperList(dao.getAll()), HttpStatus.OK);
//		} catch (E e) {
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new E500("Ups something went wrong");
//		}
//	}

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<List<EntryWrapper>> getAll() {
		try {
			List<EntryWrapper> list = new LinkedList<EntryWrapper>();
			for(Entry u : dao.getAll()) {
				list.add(new EntryWrapper(u));
			}
			return new ResponseEntity<List<EntryWrapper>>(list, HttpStatus.OK);
		} catch (E e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new E500("Ups something went wrong");
		}
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<EntryWrapper> post(@Valid @RequestBody EntryWrapper w, BindingResult r) {
		System.out.println(w);
		System.out.println(r);
		
		if (r.hasErrors()) {
			throw new E400(this.getError(r));
		}

		try {
				dao.save(w.join(new Entry(),userDao));
				return new ResponseEntity<EntryWrapper>(w, HttpStatus.CREATED);
		} catch (E e) {
			throw e;
		} catch (javax.persistence.PersistenceException e) {
			throw new E400("Object relation not found ");
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
			throw new E500("Ups something went wrong");
		}
	}
	
	private String getError(BindingResult r) {
		StringBuffer buff = new StringBuffer();
		for (ObjectError x : r.getAllErrors()) {
			buff.append(x.toString().replaceAll(":", " ").replaceAll(";", ":"));
		}
		return buff.toString();
	}

}
