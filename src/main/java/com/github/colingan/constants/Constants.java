package com.github.colingan.constants;

public class Constants {

  public static class Login {

    public static final String USER_ATTR = "pageInitData";

    public static class Parameter {
      public static String COOKIE_UIN = "uin";
      public static String COOKIE_SKEY = "skey";
      public static String COOKIE_UID = "uid";
            }
        }

  public static class URL {
    // TODO
    public static String HOME = "/404.html"; // 未登录的静态首页
  }

  public static class Pipe {
    public static class Parameter {
      public static String INNER_CLIENT_NAME = "image";
      public static String INNER_PRIVATE_KEY = "ponyma@tencent.com";
      public static int PARAM_ERROR = 4003;
    }
  }

}
