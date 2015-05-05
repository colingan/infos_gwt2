
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.utils;

import gdt.infra.cfg.ConfigEnv;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;


 /**
 * @title EBeanEnvUtils
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public class EBeanEnvUtils {


  static public EbeanServer get() {
    return Ebean.getServer(null);
  }

  static public EbeanServer get(String name) {
    return Ebean.getServer(name);
  }

  static private void init() {
    String db = ConfigEnv.getConfig("db.properties");
    System.setProperty("db.properties", db);
    System.out.println("UseDbCfg: " + db);
  }

  static {
    init();
  }
}

