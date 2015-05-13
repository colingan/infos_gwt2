
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client;

import com.github.colingan.client.constants.AppUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.RpcTokenExceptionHandler;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;


 /**
 * @title RpcUtils
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class RpcUtils {
  public static <T> T createTimeoutAndFix(Object rawRpc) {
    return createTimeoutRPC(fix(rawRpc), TG_RPC_TIMEOUT);
  }

  public final static int TG_RPC_TIMEOUT = 180000;

  public static class LoginCheckExceptionHandler implements RpcTokenExceptionHandler {

    @Override
    public void onRpcTokenException(RpcTokenException e) {
      GWT.log("onError " + e);
      Window.alert("页面过期，将重新登录。");
      Window.Location.reload();
    }

  }

  public static final LoginCheckExceptionHandler handler = new LoginCheckExceptionHandler();

  public static class TgRpcRequestBuilder extends RpcRequestBuilder {
    private final int timeoutMillis;

    public TgRpcRequestBuilder(int timeoutMillis) {
      this.timeoutMillis = timeoutMillis;
    }

    @Override
    protected RequestBuilder doCreate(String serviceEntryPoint) {
      RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, serviceEntryPoint);
      builder.setTimeoutMillis(timeoutMillis);
      return builder;
    }

  }

  @SuppressWarnings("unchecked")
  private static <T> T createTimeoutRPC(Object rpc, int timeoutMillis) {
    ((ServiceDefTarget) rpc).setRpcRequestBuilder(new TgRpcRequestBuilder(timeoutMillis));
    return (T) rpc;
  }

  public static <T> T createTimeoutRPC(Object rpc) {
    return createTimeoutRPC(fix(rpc), TG_RPC_TIMEOUT);
  }

  public static <T> void addRpcToken(T r) {
    if (r instanceof HasRpcToken) {
      String token = AppUtil.getXsrfToken();
      if (token != null) {
        ((HasRpcToken) r).setRpcToken(new XsrfToken(AppUtil.getXsrfToken()));
        ((HasRpcToken) r).setRpcTokenExceptionHandler(handler);
      }
    }
  }

  public static <T> T fix(T r) {
    ServiceDefTarget sdt = (ServiceDefTarget) r;
    String oldUrl = sdt.getServiceEntryPoint();
    String newServiceEntryPoint =
        oldUrl.startsWith(GWT.getModuleBaseURL()) ? GWT.getHostPageBaseURL() + GWT.getModuleName()
            + "/" + oldUrl.substring(GWT.getModuleBaseURL().length()) : oldUrl;
    sdt.setServiceEntryPoint(newServiceEntryPoint);
    addRpcToken(sdt);
    return r;
  }
}

