
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.rpc;

import java.lang.reflect.Method;

import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;


 /**
 * @title BaseRPCImpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public class BaseRPCImpl extends InfosRemoteServiceServlet {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.user.server.rpc.AbstractXsrfProtectedServiceServlet#validateXsrfToken(com.google
   * .gwt.user.client.rpc.RpcToken, java.lang.reflect.Method)
   */

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {

    // TODO Auto-generated method stub

  }

}

