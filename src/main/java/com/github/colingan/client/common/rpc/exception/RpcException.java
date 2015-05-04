
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.rpc.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gwt.user.client.rpc.IsSerializable;


 /**
 * @title RpcException
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class RpcException extends RuntimeException implements IsSerializable {


  private static final long serialVersionUID = -4653318388398520789L;

  private int errorCode;
  private String message;

  public RpcException() {
    this(0);
  }

  public RpcException(int errorCode) {
    this(errorCode, "rpc exception");
  }

  public RpcException(int errorCode, String message) {
    this(errorCode, message, null);
  }

  public RpcException(int errorCode, String message, Throwable e) {
    super(message, e);
    this.errorCode = errorCode;
    this.message = message;
  }

  public int getErrorCode() {

    return errorCode;
  }

  public void setErrorCode(int errorCode) {

    this.errorCode = errorCode;
  }

  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

