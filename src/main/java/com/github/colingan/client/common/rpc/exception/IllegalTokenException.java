
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.rpc.exception;


 /**
 * @title IllegalTokenException
 * @description TODO 
 * @author colingan
 * @date 2015-5-6
 * @version 1.0
 */

public class IllegalTokenException extends RuntimeException {


  private static final long serialVersionUID = -304562864187688876L;

  private int code;
  private String message;

  /**
   * @param code
   * @param message
   */

  public IllegalTokenException(int code, String message) {
    super();
    this.code = code;
    this.message = message;
  }

}

