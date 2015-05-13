package com.github.colingan.server.rpc;

import java.lang.reflect.Method;

import com.github.colingan.client.common.rpc.exception.IllegalTokenException;
import com.github.colingan.client.login.InfosToken;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;

/**
 * 登录后的权限认证，防止Xsrf攻击
 * 
 * @title AuthRPCImpl
 * @description TODO
 * @author akeungsu
 * @date 2014-10-16
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AuthRPCImpl extends BaseRPCImpl {

    @Override
  protected void validateXsrfToken(RpcToken rpcToken, Method method) throws RpcTokenException {
    XsrfToken xsrfToken = (XsrfToken) rpcToken;
    String token = xsrfToken.getToken();
    try {
      InfosToken.checkToken(getUserName(), token);
    } catch (IllegalTokenException e) {
      throw new RpcTokenException(e.getMessage());
    }
  }

}
