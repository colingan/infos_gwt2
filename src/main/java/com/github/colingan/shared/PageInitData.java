
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

  // ��¼��Ϣ����Token����
  private long uid; // �û�id��infosϵͳuid
  private long loginTime; // ��¼ʱ�䣬����
  private String xsrfToken; // Xsrf��֤token
  private PageStatus pageStatus; // ��¼״̬

  // �û���Ϣ����PageRPC����
  private String userName; // �û���
  private String department; // ������

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

