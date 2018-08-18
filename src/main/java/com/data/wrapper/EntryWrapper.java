package com.data.wrapper;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.dao.UserDao;
import com.data.Entry;
import com.data.User;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class EntryWrapper {

	public EntryWrapper() {
	}

	public EntryWrapper(Entry entry) {
		this();
		if (entry != null) {
			this.date = entry.getId();

			this.verantwortlicher_morgens = null;
			this.vertretung_morgens = null;
			this.verantwortlicher_mittags = null;
			this.vertretung_mittags = null;
			this.verantwortlicher_abends = null;
			this.vertretung_abends = null;

			if (entry.getVerantwortlicher_morgens() != null) {
				this.verantwortlicher_morgens = entry.getVerantwortlicher_morgens().getId();
			}
			if (entry.getVertretung_morgens() != null) {
				this.vertretung_morgens = entry.getVertretung_morgens().getId();
			}
			if (entry.getVerantwortlicher_mittags() != null) {
				this.verantwortlicher_mittags = entry.getVerantwortlicher_mittags().getId();
			}
			if (entry.getVertretung_mittags() != null) {
				this.vertretung_mittags = entry.getVertretung_mittags().getId();
			}
			if (entry.getVerantwortlicher_abends() != null) {
				this.verantwortlicher_abends = entry.getVerantwortlicher_abends().getId();
			}
			if (entry.getVertretung_abends() != null) {
				this.vertretung_abends = entry.getVertretung_abends().getId();
			}
		}
	}

	public Entry join(Entry entry, UserDao dao) {

		if (this.date != null) {
			entry.setId(date);
		}
		if (this.verantwortlicher_morgens != null) {
			User u = dao.getById(this.verantwortlicher_morgens);
			entry.setVerantwortlicher_morgens(u);
		}
		if (this.vertretung_morgens != null) {
			User u = dao.getById(this.vertretung_morgens);
			entry.setVertretung_morgens(u);
		}
		if (this.verantwortlicher_mittags != null) {
			User u = dao.getById(this.verantwortlicher_mittags);
			entry.setVerantwortlicher_mittags(u);
		}
		if (this.vertretung_mittags != null) {
			User u = dao.getById(this.vertretung_mittags);
			entry.setVertretung_mittags(u);
		}
		if (this.verantwortlicher_abends != null) {
			User u = dao.getById(this.verantwortlicher_abends);
			entry.setVerantwortlicher_abends(u);
		}
		if (this.vertretung_abends != null) {
			User u = dao.getById(this.vertretung_abends);
			entry.setVertretung_abends(u);
		}
		return entry;
	}

	// using custom toString

	public String toString() {
		return "{\"date\":\"" + this.date + "\",\"Verantwortlich morgens\":\"" + this.verantwortlicher_morgens
				+ "\",\"Vertretung morgens\":\"" + this.vertretung_morgens + "\",\"Verantwortlich mittags\":\""
				+ this.verantwortlicher_mittags + "\",\"Vertretung mittags\":\"" + this.vertretung_mittags
				+ "\",\"Verantwortlich abends\":\"" + this.verantwortlicher_abends + "\",\"vertretung abends\":"
				+ this.vertretung_abends + "\"}\n";
	}

	@NotNull
	@JsonProperty(required = true)
	private Date date;
	@JsonProperty(required = true)
	private String verantwortlicher_morgens;
	@JsonProperty(required = true)
	private String vertretung_morgens;
	@JsonProperty(required = true)
	private String verantwortlicher_mittags;
	@JsonProperty(required = true)
	private String vertretung_mittags;
	@JsonProperty(required = true)
	private String verantwortlicher_abends;
	@JsonProperty(required = true)
	private String vertretung_abends;
}
