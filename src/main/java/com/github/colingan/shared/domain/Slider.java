package com.github.colingan.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slider")
public class Slider implements Serializable {

	private static final long serialVersionUID = -945797619752919794L;

	@Id
	private long id_;
	private int idx_;
	private String origin_name_;
	private String dest_name_;
	private int is_del_;
	private Date add_time_;
	private Date update_time_;
	public long getId_() {
		return id_;
	}
	public void setId_(long id_) {
		this.id_ = id_;
	}
	public int getIdx_() {
		return idx_;
	}
	public void setIdx_(int idx_) {
		this.idx_ = idx_;
	}
	public String getOrigin_name_() {
		return origin_name_;
	}
	public void setOrigin_name_(String origin_name_) {
		this.origin_name_ = origin_name_;
	}
	public String getDest_name_() {
		return dest_name_;
	}
	public void setDest_name_(String dest_name_) {
		this.dest_name_ = dest_name_;
	}
	public int getIs_del_() {
		return is_del_;
	}
	public void setIs_del_(int is_del_) {
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


}
