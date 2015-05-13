
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.login;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.colingan.client.common.rpc.exception.IllegalTokenException;
import com.github.colingan.constants.Constants;
import com.github.colingan.shared.PageInitData;
import com.github.colingan.shared.domain.type.RoleGroup;
import com.github.colingan.shared.domain.vo.CookieUser;
import com.github.colingan.util.AESUtil;
import com.github.colingan.util.JacksonUtil;
import com.github.colingan.util.MD5Util;
import com.google.common.base.Strings;



 /**
 * @title InfosToken
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public class InfosToken {

  private static final Logger LOGGER = LoggerFactory.getLogger(InfosToken.class);

  public static long getUserId(HttpServletRequest request) {
    PageInitData initData = getInitData(request);
    return initData == null ? -1 : initData.getUser().getUid();
  }

  public static String getUserName(HttpServletRequest request) {
    PageInitData data = getInitData(request);
    return data.getUser().getN();
  }

  public static RoleGroup getRoleGroup(HttpServletRequest request) {
    return getInitData(request).getRole();
  }

  public static PageInitData getInitData(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    Object data = session.getAttribute(Constants.Login.USER_ATTR);
    if (data != null) {
      return (PageInitData) data;
    }
    return null;
  }

  public static boolean isUserLogin(HttpServletRequest request) {
    PageInitData data = getInitData(request);
    return data != null;
  }

  public static PageInitData login(HttpServletRequest request) {
    // 先从cookie读
    CookieUser user = getAndCheckCookieUser(request);

    // 再从request params读
    if (user == null) {
      LOGGER.debug("no user info find in cookie, try retrieve from url");
      String id = request.getParameter("id");
      String n = request.getParameter("n");
      String d = request.getParameter("d");
      if (id != null && n != null && d != null) {
        user = new CookieUser();
        user.setId(id);
        user.setN(n);
        user.setD(d);
      }
    }
    if (user != null) {
      LOGGER.debug("user info find: " + user);
    } else {
      LOGGER.debug("no user info find.");
      // set nobody user
      user = CookieUser.NOBODY;
    }

    return toPageInitData(user);
  }

  public static void login(HttpServletRequest request, HttpServletResponse response,
      PageInitData data) {
    long loginTime = System.currentTimeMillis();
    data.setLoginTime(loginTime);
    data.setXsrfToken(getToken(data.getUser().getN(), loginTime));
    request.getSession().setAttribute(Constants.Login.USER_ATTR, data);
    refreshCookie(response, data.getUser());
  }

  public static String getToken(String uName, long loginTime) {
    String sign = MD5Util.encode(uName + Constants.Pipe.Parameter.INNER_PRIVATE_KEY + loginTime);
    return Base64.encodeBase64String((uName + "," + loginTime + "," + sign).getBytes());
  }

  /**
   * 校验加密串，默认永久有效
   * 
   * @return
   */
  public static void checkToken(String uName, String token) throws IllegalTokenException {
    checkToken(uName, token, 0);
  }

  /**
   * @param uid
   * @param token
   * @param timeout 超时（单位毫秒），0表示永久有效
   * @throws IllegalTokenException
   */
  public static void checkToken(String uName, String token, long timeout)
      throws IllegalTokenException {

    if (token == null || token.isEmpty()) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "token is empty, (%s,%s)", uName, token));
    }

    String m[] = new String(Base64.decodeBase64(token)).split(",");

    if (m.length != 3 || !uName.equals(m[0]) || Strings.isNullOrEmpty(m[2])) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "token is wrong, (%s,%s)", uName, token));
    }

    String signature = MD5Util.encode(m[0] + Constants.Pipe.Parameter.INNER_PRIVATE_KEY + m[1]);
    if (!signature.equalsIgnoreCase(m[2])) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "sign is wrong, (%s,%s)", uName, token));
    }

    if (timeout > 0) {
      long currentTimeMillis = System.currentTimeMillis();

      if (Math.abs(NumberUtils.toLong(m[1], 0) - currentTimeMillis) > timeout) {
        // 验证超时
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTimeMillis);
        throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
            "Wrong request time, (%s,%s,%d) current server time is %s", uName, token, timeout,
            dateString));
      }
    }

  }

  private static PageInitData toPageInitData(CookieUser user) {
    Validate.notNull(user);
    PageInitData data = new PageInitData();
    data.setUser(user);

    return data;
  }

  private static CookieUser getAndCheckCookieUser(HttpServletRequest request) {
    String cuser = getCookieValue(request, Constants.Login.C_UKEY);
    if (cuser == null) {
      return null;
    }

    try {
      // return JacksonUtil.str2Obj(DESUtil.decrypt(cuser, Constants.DES_UKEY), CookieUser.class);
      return JacksonUtil.str2Obj(
          new String(AESUtil.decrypt(Hex.decodeHex(cuser.toCharArray()), Constants.AES_UKEY),
              "UTF-8"), CookieUser.class);
    } catch (Exception e) {
      LOGGER.error("failed to decrypt cookie user infos.", e);
      return null;
    }
  }

  private static String getCookieValue(HttpServletRequest request, String cookieName) {
    if (request != null && cookieName != null && !cookieName.isEmpty()) {
      String allCookieStr = request.getHeader("Cookie");
      if (allCookieStr != null) {
        String[] cookies = allCookieStr.split(";");

        if (cookies != null) {
          for (String cookie : cookies) {
            if (cookie != null && cookie.indexOf("=") > 0) {
              String[] kv = cookie.split("=");
              if (cookieName.equals(kv[0].trim())) {
                return kv.length > 1 ? kv[1] : "";
              }
            }
          }
        }
      }
    }
    return null;
  }

  private static void refreshCookie(HttpServletResponse response, CookieUser user) {
    Validate.notNull(response);
    Validate.notNull(user);

    Cookie cuser;
    try {
      cuser =
          new Cookie(Constants.Login.C_UKEY, Hex.encodeHexString(AESUtil.encrypt(JacksonUtil
              .obj2Str(user).getBytes("UTF-8"), Constants.AES_UKEY)));
    } catch (Exception e) {
      LOGGER.error("failed to refresh cookie", e);
      throw new RuntimeException(e);
    }
    cuser.setMaxAge(-1);
    response.addCookie(cuser);
  }
}

