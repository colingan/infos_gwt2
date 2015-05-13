
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.shared;

import java.io.Serializable;

import com.github.colingan.shared.domain.type.RoleGroup;
import com.github.colingan.shared.domain.vo.CookieUser;


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

  private long loginTime; // ��¼ʱ�䣬����
  private String xsrfToken; // Xsrf��֤token
  private PageStatus pageStatus; // ��¼״̬

  // �û���Ϣ����PageRPC����
  private CookieUser user;
  private RoleGroup role;

  public PageInitData() {

  }

  public PageInitData(CookieUser user) {
    this.user = user;
    this.loginTime = System.currentTimeMillis();
    this.pageStatus = PageStatus.OK;
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

  public CookieUser getUser() {

    return user;
  }

  public void setUser(CookieUser user) {
  
    this.user = user;
  }

  public RoleGroup getRole() {

    return role;
  }

  public void setRole(RoleGroup role) {

    this.role = role;
  }

}

