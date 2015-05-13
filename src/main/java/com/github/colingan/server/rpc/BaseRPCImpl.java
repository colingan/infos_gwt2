
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.rpc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.colingan.client.common.rpc.exception.NotLoginException;
import com.github.colingan.client.login.InfosToken;
import com.github.colingan.server.member.MemberService;
import com.github.colingan.shared.PageInitData;
import com.github.colingan.shared.domain.Member;
import com.github.colingan.shared.domain.type.RoleGroup;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.inject.Inject;


 /**
 * @title BaseRPCImpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public class BaseRPCImpl extends InfosRemoteServiceServlet {

  @Inject
  protected MemberService memberService;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.user.server.rpc.AbstractXsrfProtectedServiceServlet#validateXsrfToken(com.google
   * .gwt.user.client.rpc.RpcToken, java.lang.reflect.Method)
   */

  @Override
  protected void onBeforeMethodInvoke(Method method, Object[] args, int flags) {

    // 根据cookie自动检测登录状态
    autoCheckAndLogin();
    if (isRequiredCheckLogin(method, args, flags)) {
      checkUserLogin();
    }
  }

  private void autoCheckAndLogin() {
    HttpServletRequest request = getThreadLocalRequest();
    PageInitData initData = InfosToken.getInitData(request);
    if (initData == null) {
      initData = InfosToken.login(request);
      if (initData != null) {
        HttpServletResponse response = getThreadLocalResponse();
        Member member = memberService.queryMemberByUserName(initData.getUser().getN());
        if (member != null) {
          RoleGroup role = RoleGroup.valueOf(member.getRole_group_());
          initData.setRole(role);

          InfosToken.login(request, response, initData);
        }
      }
    }
  }

  protected boolean isRequiredCheckLogin(Method method, Object[] args, int flags) {
    boolean require = false;
    for (Class<?> c : method.getExceptionTypes()) {
      if (c.equals(NotLoginException.class)) {
        return true;
      }
    }

    return require;
  }

  protected final void checkUserLogin() throws NotLoginException {
    if (!isUserLogin()) {
      throw new NotLoginException();
    }
  }

  protected final boolean isUserLogin() {
    return InfosToken.isUserLogin(getThreadLocalRequest());
  }

  protected final String getUserName() {
    return InfosToken.getUserName(getThreadLocalRequest());
  }

  protected final RoleGroup getRoleGroup() {
    return InfosToken.getRoleGroup(getThreadLocalRequest());
  }

  @Override
  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {

  }

  protected static String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

}

