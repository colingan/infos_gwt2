package com.github.colingan.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * blog category for ones123
 * 
 * @title Category
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2015年1月19日
 * @version 1.0
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 2193179169570223853L;

	@Id
	private long id_;
	private String name_;
	private int level_;
	private long parent_category_;
	private boolean is_del_;
	private Date add_time_;
	private Date update_time_;
	private String update_user_;

	public Category() {
	}

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

	public int getLevel_() {
		return level_;
	}

	public void setLevel_(int level_) {
		this.level_ = level_;
	}

	public long getParent_category_() {
		return parent_category_;
	}

	public void setParent_category_(long parent_category_) {
		this.parent_category_ = parent_category_;
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

	public String getUpdate_user_() {
		return update_user_;
	}

	public void setUpdate_user_(String update_user_) {
		this.update_user_ = update_user_;
	}

  @Override
  public int hashCode() {
    return Long.valueOf(id_).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Category)) {
      return false;
    }
    Category other = (Category) obj;
    return id_ == other.id_;
  }

}
