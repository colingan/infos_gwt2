
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.component;

import java.util.Set;

import com.github.colingan.client.common.presenter.HeaderPresenter;
import com.github.colingan.client.constants.AppConstants;
import com.soso.tg.cmvp.client.component.SimpleComponent;
import com.soso.tg.cmvp.client.presenter.Presenter;
import com.soso.tg.cmvp.client.util.CMVP;


 /**
 * @title HeaderComponent
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class HeaderComponent extends SimpleComponent {

  public HeaderComponent() {
    super();
  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getName()
   */

  @Override
  public String getName() {

    return AppConstants.Component.Header;

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getStates()
   */

  @Override
  public Set<String> getStates() {

    return CMVP.asSet("*");

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.SimpleComponent#initPresenter()
   */

  @Override
  protected Presenter initPresenter() {

    return new HeaderPresenter();

  }

}

