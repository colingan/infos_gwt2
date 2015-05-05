
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.login;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.github.colingan.constants.Constants;
import com.github.colingan.shared.PageInitData;
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
  /**
   * 创建加密串
   * 
   * @return
   */
  public static String getToken(long uid) {
    String userId = String.format("%010d", uid);
    long currentTime = System.currentTimeMillis();
    String sign = MD5Util.encode(userId + Constants.Pipe.Parameter.INNER_PRIVATE_KEY + currentTime);
    return Base64.encodeBase64String((userId + "," + currentTime + "," + sign).getBytes());
  }

  /**
   * 校验加密串，默认永久有效
   * 
   * @return
   */
  public static void checkToken(long uid, String token) throws IllegalTokenException {
    checkToken(uid, token, 0);
  }

  /**
   * @param uid
   * @param token
   * @param timeout 超时（单位毫秒），0表示永久有效
   * @throws IllegalTokenException
   */
  public static void checkToken(long uid, String token, long timeout) throws IllegalTokenException {

    if (token == null || token.isEmpty()) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "token is empty, (%d,%s)", uid, token));
    }

    String userId = String.format("%010d", uid);
    String m[] = new String(Base64.decodeBase64(token)).split(",");

    if (m.length != 3 || !userId.equals(m[0]) || Strings.isNullOrEmpty(m[2])) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "token is wrong, (%d,%s)", uid, token));
    }

    String signature = MD5Util.encode(m[0] + Constants.Pipe.Parameter.INNER_PRIVATE_KEY + m[1]);
    if (!signature.equalsIgnoreCase(m[2])) {
      throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
          "sign is wrong, (%d,%s)", uid, token));
    }

    if (timeout > 0) {
      long currentTimeMillis = System.currentTimeMillis();

      if (Math.abs(NumberUtils.toLong(m[1], 0) - currentTimeMillis) > timeout) {
        // 验证超时
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTimeMillis);
        throw new IllegalTokenException(Constants.Pipe.Parameter.PARAM_ERROR, String.format(
            "Wrong request time, (%d,%s,%d) current server time is %s", uid, token, timeout,
            dateString));
      }
    }

  }

  public static String getCookieValue(HttpServletRequest request, String cookieName) {
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

  /**
   * 获取Session登陆信息，默认不创建
   * 
   * @param request
   * @return
   */
  public static PageInitData getInitData(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    Object data = session.getAttribute(Constants.Login.USER_ATTR);
    if (null != data) {
      // session存在，未必登录
      return (PageInitData) data;
    } else {
      return null;
    }
  }

  /**
   * 判断是否登陆，不创建session，QQ登陆并且白名单才算登陆
   * 
   * @param request
   * @return
   */
  public static boolean isUserLogin(HttpServletRequest request) {
    PageInitData initData = getInitData(request);
    if (initData == null) {
      return false;
    }
    return true;
  }

  public static long getUserId(HttpServletRequest request) {
    PageInitData initData = getInitData(request);
    if (initData == null) {
      return 0;
    }
    return initData.getUserId();
  }

  public static long getMemberId(HttpServletRequest request) {
    PageInitData initData = getInitData(request);
    if (initData == null) {
      return 0;
    }
    return initData.getMemberId();
  }

  public static long getAdvertiserId(HttpServletRequest request) {
    PageInitData initData = getInitData(request);
    if (initData == null) {
      return 0;
    }
    return initData.getAdvertiserId();
  }

  // 设置登陆Session
  public static void login(HttpServletRequest request, PageInitData initData) {
    initData.setXsrfToken(getToken(initData.getUserId()));
    request.getSession().setAttribute(Constants.Login.USER_ATTR, initData);
  }

  public static void logout(HttpServletRequest request) {
    request.getSession().removeAttribute(Constants.Login.USER_ATTR);
  }

  /**
   * 根据cookie登陆验证，要么成功返回data，要么抛出异常
   * 
   * @param request
   * @return
   * @throws NotLoginException
   */
  public static PageInitData login(HttpServletRequest request) {
    // 1. 首先获取cookie
    String skeyCookie = getCookieValue(request, "skey");
    String uinCookie = getCookieValue(request, "uin");
    if (Strings.isNullOrEmpty(skeyCookie) || Strings.isNullOrEmpty(uinCookie)) {
      return null;
    }

    // 2. 根据cookie登陆验证
    if (!validateUinFromPtLogin(uinCookie, skeyCookie)) {
      // 验证失败
      return null;
    }

    // 3. 登陆成功，获取信息并设置session
    long uid = parseUin(uinCookie);
    PageInitData initData = new PageInitData(uid);
    return initData;
  }

  /**
   * TODO http请求方式可以优化下
   * 
   * @param skey
   * @param uin
   * @return
   */
  private static boolean validateUinFromPtLogin(String uin, String skey) {
    DefaultHttpClient httpClient = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet("http://e.qq.com/ec/loginfo.php?g_tk=" + time33(skey));
    CookieStore cookieStore = new BasicCookieStore();
    BasicClientCookie cookie = new BasicClientCookie("skey", skey);
    cookie.setDomain("qq.com");
    cookieStore.addCookie(cookie);
    cookie = new BasicClientCookie("uin", uin);
    cookie.setDomain("qq.com");
    cookieStore.addCookie(cookie);
    httpClient.setCookieStore(cookieStore);
    try {
      HttpResponse response = httpClient.execute(httpGet);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String content = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(content);
        int ret = Integer.parseInt(jsonObject.get("ret").toString());
        if (ret == 0) {
          return true;
        }
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  private static long time33(String str) {
    long hash = 5381;
    int len = str.length();
    for (int i = 0; i < len; ++i) {
      hash = (int) ((hash << 5 & 0x7fffffff) + str.charAt(i) + hash);
    }
    return hash & 0x7fffffff;
  }

  private static Long parseUin(String uinCookie) {
    return Long.parseLong(uinCookie.replace("o", ""));
  }
}

