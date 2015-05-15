
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.blog.presenter;

import com.github.colingan.client.blog.view.BlogEditView;
import com.github.colingan.client.common.InfosPresenter;
import com.soso.tg.cmvp.client.display.Display;


 /**
 * @title BlogEditPresenter
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogEditPresenter extends InfosPresenter {

  public interface View extends Display {

  }

  private View view;

  public BlogEditPresenter() {
    this.view = new BlogEditView();
  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.presenter.Presenter#getDisplay()
   */

  @Override
  public Display getDisplay() {

    return view;

  }

}

