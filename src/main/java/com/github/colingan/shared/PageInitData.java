
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.shared;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


 /**
 * @title PageInitData
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class PageInitData implements Serializable {


  private static final long serialVersionUID = 6167647189057542901L;

  public static enum PageStatus {
    OK, NotLogin;
  }

  // 登录信息，由Token设置
  private long uid; // 用户id，infos系统uid
  private long loginTime; // 登录时间，毫秒
  private String xsrfToken; // Xsrf验证token
  private PageStatus pageStatus; // 登录状态

  // 用户信息，由PageRPC设置
  private String userName; // 用户名
  private String department; // 部门名

  public PageInitData() {

  }

  public PageInitData(long userId) {
    this.uid = userId;
    this.loginTime = System.currentTimeMillis();
    this.pageStatus = PageStatus.OK;
  }

  public long getUid() {

    return uid;
  }

  public void setUid(long uid) {

    this.uid = uid;
  }

  public long getLoginTime() {

    return loginTime;
  }

  public void setLoginTime(long loginTime) {

    this.loginTime = loginTime;
  }

  public String getXsrfToken() {

    return xsrfToken;
  }

  public void setXsrfToken(String xsrfToken) {

    this.xsrfToken = xsrfToken;
  }

  public PageStatus getPageStatus() {

    return pageStatus;
  }

  public void setPageStatus(PageStatus pageStatus) {

    this.pageStatus = pageStatus;
  }

  public String getUserName() {

    return userName;
  }

  public void setUserName(String userName) {

    this.userName = userName;
  }

  public String getDepartment() {

    return department;
  }

  public void setDepartment(String department) {

    this.department = department;
  }
  
  @Override
  public String toString() {
    
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

