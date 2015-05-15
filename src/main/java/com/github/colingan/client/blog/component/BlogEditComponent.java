
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
 * @title BlogEditComponent
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogEditComponent extends InfosComponent {

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getName()
   */

  @Override
  public String getName() {

    return AppConstants.Component.BlogEditComponent;

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getStates()
   */

  @Override
  public Set<String> getStates() {

    return CMVP.asSet(AppConstants.BlogPage.State.Edit);

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.SimpleComponent#initPresenter()
   */

  @Override
  protected Presenter initPresenter() {

    return null;

  }

}

