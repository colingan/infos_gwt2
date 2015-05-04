package com.github.colingan.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member implements Serializable {

	private static final long serialVersionUID = 5248277785702409001L;

	@Id
	private long id_;
	private String real_name_;
	private String user_name_;
	private int role_group_;
	private boolean is_del_;
	private Date add_time_;
	private Date update_time_;

	private String uid_;

	public long getId_() {
		return id_;
	}

	public void setId_(long id_) {
		this.id_ = id_;
	}

	public String getReal_name_() {
		return real_name_;
	}

	public void setReal_name_(String real_name_) {
		this.real_name_ = real_name_;
	}

	public String getUser_name_() {
		return user_name_;
	}

	public void setUser_name_(String user_name_) {
		this.user_name_ = user_name_;
	}

	public int getRole_group_() {
		return role_group_;
	}

	public void setRole_group_(int role_group_) {
		this.role_group_ = role_group_;
	}

	public boolean getIs_del_() {
		return is_del_;
	}

	public void setIs_del_(boolean is_del_) {
		this.is_del_ = is_del_;
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

	public String getUid_() {
		return uid_;
	}

	public void setUid_(String uid_) {
		this.uid_ = uid_;
	}


}
