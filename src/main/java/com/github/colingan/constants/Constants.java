package com.github.colingan.constants;

public class Constants {

  public static final byte[] AES_UKEY = new byte[] {-109, 55, 24, 38, 109, -26, 10, 1, 101, -81,
      86, -102, -112, 104, 58, -84};

  public static class Login {

    public static final String USER_ATTR = "pageInitData";

    public static final String C_UKEY = "_c_key_";

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
