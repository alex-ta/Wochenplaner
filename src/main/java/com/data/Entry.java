package com.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "entry")
@Proxy(lazy = false)
public class Entry {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date id;
	private User vertretung_morgens;
	private User vertretung_mittags;
	private User vertretung_abends;
	private User verantwortlicher_morgens;
	private User verantwortlicher_mittags;
	private User verantwortlicher_abends;
			
	@Id
	@Temporal(TemporalType.DATE)
	public Date getId() {
		return id;
	}

	public void setId(Date id) {
		this.id = id;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vertretung_morgens", referencedColumnName = "id")
	public User getVertretung_morgens() {
		return vertretung_morgens;
	}

	public void setVertretung_morgens(User vertretung_morgens) {
		this.vertretung_morgens = vertretung_morgens;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vertretung_mittags", referencedColumnName = "id")
	public User getVertretung_mittags() {
		return vertretung_mittags;
	}

	public void setVertretung_mittags(User vertretung_mittags) {
		this.vertretung_mittags = vertretung_mittags;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vertretung_abends", referencedColumnName = "id")
	public User getVertretung_abends() {
		return vertretung_abends;
	}

	public void setVertretung_abends(User vertretung_abends) {
		this.vertretung_abends = vertretung_abends;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "verantwortlicher_morgens", referencedColumnName = "id")
	public User getVerantwortlicher_morgens() {
		return verantwortlicher_morgens;
	}

	public void setVerantwortlicher_morgens(User verantwortlicher_morgens) {
		this.verantwortlicher_morgens = verantwortlicher_morgens;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "verantwortlicher_mittags", referencedColumnName = "id")
	public User getVerantwortlicher_mittags() {
		return verantwortlicher_mittags;
	}

	public void setVerantwortlicher_mittags(User verantwortlicher_mittags) {
		this.verantwortlicher_mittags = verantwortlicher_mittags;
	}

	@Fetch(FetchMode.SELECT)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "verantwortlicher_abends", referencedColumnName = "id")
	public User getVerantwortlicher_abends() {
		return verantwortlicher_abends;
	}

	public void setVerantwortlicher_abends(User verantwortlicher_abends) {
		this.verantwortlicher_abends = verantwortlicher_abends;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Entry) {
			return DATE_FORMAT.format(((Entry)obj).getId()).equals(DATE_FORMAT.format(this.getId()));
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return DATE_FORMAT.format(this.getId()).hashCode();
	}
}
