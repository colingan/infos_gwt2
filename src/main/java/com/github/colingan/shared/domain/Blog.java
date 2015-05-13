package com.github.colingan.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog implements Serializable {

	private static final long serialVersionUID = 2064717122400298350L;

	@Id
	private long id_;
	private String user_name_;
	private long category_1_;
	private long category_2_;
	private String title_;
	private String content_;
	private Date add_time_;
	private Date update_time_;
	private boolean is_del_;
	
	public long getId_() {
		return id_;
	}
	public void setId_(long id_) {
		this.id_ = id_;
	}
	public String getUser_name_() {
		return user_name_;
	}
	public void setUser_name_(String user_name_) {
		this.user_name_ = user_name_;
	}
	public long getCategory_1_() {
		return category_1_;
	}
	public void setCategory_1_(long category_1_) {
		this.category_1_ = category_1_;
	}
	public long getCategory_2_() {
		return category_2_;
	}
	public void setCategory_2_(long category_2_) {
		this.category_2_ = category_2_;
	}
	public String getTitle_() {
		return title_;
	}
	public void setTitle_(String title_) {
		this.title_ = title_;
	}
	public String getContent_() {
		return content_;
	}
	public void setContent_(String content_) {
		this.content_ = content_;
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
	public boolean isIs_del_() {
		return is_del_;
	}
	public void setIs_del_(boolean is_del_) {
		this.is_del_ = is_del_;
	}

}
