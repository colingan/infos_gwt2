
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.blog.component;

import java.util.Set;

import com.github.colingan.client.common.InfosComponent;
import com.github.colingan.client.constants.AppConstants;
import com.soso.tg.cmvp.client.presenter.Presenter;
import com.soso.tg.cmvp.client.util.CMVP;


 /**
 * @title BlogViewComponent
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogViewComponent extends InfosComponent {

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getName()
   */

  @Override
  public String getName() {

    return AppConstants.Component.BlogViewComponent;

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getStates()
   */

  @Override
  public Set<String> getStates() {

    return CMVP.asSet(AppConstants.BlogPage.State.View);

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.SimpleComponent#initPresenter()
   */

  @Override
  protected Presenter initPresenter() {

    // TODO Auto-generated method stub
    return null;

  }

}

