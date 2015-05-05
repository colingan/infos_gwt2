
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.rpc;

import gdt.dmp.domain.vo.ErrorCode;
import gdt.dmp.domain.vo.exception.ServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.github.colingan.client.common.rpc.exception.RpcException;
import com.github.colingan.client.login.InfosToken;
import com.github.colingan.util.DevLog;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.AbstractXsrfProtectedServiceServlet;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.google.gwt.user.server.rpc.UnexpectedException;


 /**
 * @title InfosRemoteServiceServlet
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public abstract class InfosRemoteServiceServlet extends AbstractXsrfProtectedServiceServlet {

  public final static DevLog rpcLog = new DevLog("rpc");

  private boolean isOfSimpleType(Object obj) {
    Class<?> clazz = obj.getClass();
    return clazz == Boolean.class || Number.class.isAssignableFrom(clazz) || clazz.isPrimitive()
        || clazz.isEnum()//
        || clazz == String.class || obj instanceof java.util.Date;
  }

  private String paramToString(Object[] param) {
    if (param == null || param.length == 0) {
      return "[]";
    }

    StringBuilder buf = new StringBuilder();
    buf.append("[");
    for (int i = 0; i < param.length; i++) {
      if (i > 0) {
        buf.append(",");
      }
      if (param[i] == null) {
        buf.append("null");
      } else if (isOfSimpleType(param[i])) {
        buf.append(param[i]);
      } else {
        buf.append(param[i].toString());
      }
    }
    buf.append("]");
    return buf.toString();
  }

  /**
   * 在RPC方法调用前执行，可以做登录检查、权限验证等通用工作
   * 
   * @throws NotLoginException
   */
  protected void onBeforeMethodInvoke(Method method, Object[] args, int flags) {
    rpcLog.info("method=" + method.getName() + ", args=" + Arrays.toString(args));
  }

  @Override
  public final String processCall(String payload) throws SerializationException {
    long begin = System.currentTimeMillis();
    try {
      RPCRequest rpcRequest = RPC.decodeRequest(payload, this.getClass(), this);

      String operAccount =
          " account-" + String.valueOf(InfosToken.getUserId(getThreadLocalRequest())) + ":";
      String name =
          operAccount + rpcRequest.getMethod().getDeclaringClass().getName() + ":"
              + rpcRequest.getMethod().getName() + ":"
              + (rpcRequest.getParameters() == null ? 0 : rpcRequest.getParameters().length)
              + ",params:" + paramToString(rpcRequest.getParameters());

      try {

        // 验证登陆权限
        onBeforeMethodInvoke(rpcRequest.getMethod(), rpcRequest.getParameters(),
            rpcRequest.getFlags());
        // 验证Xsrf攻击
        onAfterRequestDeserialized(rpcRequest);

        String result =
            RPC.invokeAndEncodeResponse(this, rpcRequest.getMethod(), rpcRequest.getParameters(),
                rpcRequest.getSerializationPolicy(), rpcRequest.getFlags());
        rpcLog.info(begin, name);
        return result;
      } catch (UnexpectedException e) {
        Throwable cause = e.getCause();
        RpcException re = null;
        // 已知异常：即开发自定义的异常，包括 ServiceException
        // 所有其他异常都改成RpcException
        if (cause instanceof ServiceException) {
          ServiceException se = (ServiceException) cause;
          re = new RpcException(se.getErrorCode().getCode(), se.getMessage(), se);
        } else if (!(cause instanceof RpcException)) {
          re =
              new RpcException(ErrorCode.API_SERVER_ERROR.getCode(),
                  ErrorCode.API_SERVER_ERROR.getDescription(), cause);
        }
        rpcLog.error(begin, name, re);
        return RPC.encodeResponseForFailure(null, re);
      }
    } catch (IncompatibleRemoteServiceException ex) {
      rpcLog.error(begin,
          "An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
      return RPC.encodeResponseForFailure(null, ex);
    }
  }

  @Override
  protected SerializationPolicy doGetSerializationPolicy(HttpServletRequest request,
      String moduleBaseURL, String strongName) {
    SerializationPolicy serializationPolicy =
        super.doGetSerializationPolicy(request, moduleBaseURL, strongName);

    if (serializationPolicy != null) {
      return serializationPolicy;
    }

    // 不是同一个app的情况，约定
    // 1)moduleBaseURL的最后一部分是moduleName，
    // 2)所需资源在 /moduleName/strongName.gwt.rpc
    int loc = -1;
    int len = moduleBaseURL.length();
    loc = len <= 1 ? -1 : moduleBaseURL.lastIndexOf('/', len - 2);
    String moduleName = null;
    if (loc >= 0) {
      moduleName = moduleBaseURL.substring(loc + 1, len - 1);
    }
    if (loc < 0 || moduleName.length() == 0) {
      log("ERROR: GWT default SerializationPolicy load strategy failed, our strategy failed to get moduleName. ");
      return null;
    }
    log("WARN: GWT default SerializationPolicy load strategy failed, try our strategy:moduleBaseURL="
        + moduleBaseURL + ",strongName=" + strongName);
    String contextRelativePath = "/" + moduleName + "/";// modulePath.substring(contextPath.length());
    log("WARN: contextRelativePath=" + contextRelativePath);

    String serializationPolicyFilePath =
        SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath + strongName);
    // Open the RPC resource file and read its contents.
    InputStream is = getServletContext().getResourceAsStream(serializationPolicyFilePath);
    try {
      if (is != null) {
        try {
          serializationPolicy = SerializationPolicyLoader.loadFromStream(is, null);
        } catch (ParseException e) {
          log("ERROR: Failed to parse the policy file '" + serializationPolicyFilePath + "'", e);
        } catch (IOException e) {
          log("ERROR: Could not read the policy file '" + serializationPolicyFilePath + "'", e);
        }
      } else {
        String message =
            "ERROR: The serialization policy file '" + serializationPolicyFilePath
                + "' was not found; did you forget to include it in this deployment?";
        log(message);
      }
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          // Ignore this error
        }
      }
    }

    if (serializationPolicy != null) {
      log("WARN: load SerializationPolicy SUCCESSFULLY:" + strongName);
    } else {
      log("ERROR: load SerializationPolicy by our strategy failed too.");
    }

    return serializationPolicy;
  }

}

