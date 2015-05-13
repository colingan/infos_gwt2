
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common;

import com.github.colingan.client.constants.AppConstants;
import com.github.colingan.client.index.page.IndexPage;
import com.google.gwt.core.client.GWT;
import com.soso.tg.cmvp.client.async.Promise;
import com.soso.tg.cmvp.client.async.Promises;
import com.soso.tg.cmvp.client.factory.PageFactory;
import com.soso.tg.cmvp.client.page.Page;


 /**
 * @title InfosPageFactoryAsync
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class InfosPageFactoryAsync implements PageFactory {

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.factory.PageFactory#createPage(java.lang.String)
   */

  @Override
  public Promise<Page> createPage(final String pageName) {

    Page page = null;
    if (pageName == null || pageName.isEmpty()
        || pageName.equalsIgnoreCase(AppConstants.Page.IndexPage)) {
      page = new IndexPage();
    } else {
      GWT.log("not exist page:" + pageName);
    }
    return Promises.fulfill(page);
  }

}

