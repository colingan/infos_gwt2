
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.constants;

import com.github.colingan.shared.PageInitData;
import com.soso.tg.cmvp.client.util.CMVP;


 /**
 * @title AppUtil
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class AppUtil {

  public final static PageInitData getPageInitData() {
    return CMVP.getSession().getAttribute(AppConstants.PAGE_INIT_DATA);
  }

  public final static void setPageInitData(PageInitData data) {
    CMVP.getSession().setAttribute(AppConstants.PAGE_INIT_DATA, data);
  }

  public static final String getXsrfToken() {
    PageInitData data = AppUtil.getPageInitData();
    if (data != null) {
      return data.getXsrfToken();
    }
    return null;
  }

  public static final long getUin() {
    return getPageInitData().getUser().getUid();
  }

  public static final String getUserName() {
    return getPageInitData().getUser().getN();
  }
}

