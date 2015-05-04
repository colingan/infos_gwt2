package com.github.colingan.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link implements Serializable {

	private static final long serialVersionUID = -5797164967476243302L;

	@Id
	private long id_;
	private String name_;
	private String link_;
	private Date add_time_;
	private Date update_time_;
	public long getId_() {
		return id_;
	}
	public void setId_(long id_) {
		this.id_ = id_;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getLink_() {
		return link_;
	}
	public void setLink_(String link_) {
		this.link_ = link_;
	}
	public Date getAdd_time_() {
		return add_time_;
	}
	public void setAdd_time_(Date add_time_) {
		this.add_time_ = add_time_;
	}
	public Date getUpdate_time_() {
		return update_time_;
	}
	public void setUpdate_time_(Date update_time_) {
		this.update_time_ = update_time_;
	}


}
