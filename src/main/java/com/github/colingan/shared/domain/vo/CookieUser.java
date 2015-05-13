package com.github.colingan.shared.domain.vo;

import java.io.Serializable;

public class CookieUser implements Serializable {

  private static final long serialVersionUID = -6741790652257280418L;
  // id in url: 210|240|214|218
  private String id;
  // user name in url
  private String n;
  // department in url
  private String d;

  // inner member user id
  private long uid;

  public static final CookieUser NOBODY = new CookieUser("NULL", "匿名", "NULL", -1);

  public CookieUser() {
    super();
  }

  public CookieUser(String id, String n, String d, long uid) {
    super();
    this.id = id;
    this.n = n;
    this.d = d;
    this.uid = uid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getD() {
    return d;
  }

  public void setD(String d) {
    this.d = d;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

}
