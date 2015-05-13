
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.presenter;

import java.util.List;
import java.util.Map;

import com.github.colingan.client.common.InfosPresenter;
import com.github.colingan.client.common.view.HeaderView;
import com.github.colingan.shared.domain.Category;
import com.soso.tg.cmvp.client.async.Promise;
import com.soso.tg.cmvp.client.display.Display;


 /**
 * @title HeaderPresenter
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class HeaderPresenter extends InfosPresenter {

  public interface View extends Display {

    void setMenuDatas(Map<Category, List<Category>> datas);
  }

  protected final View view;

  public HeaderPresenter() {
    view = new HeaderView();
  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.presenter.Presenter#getDisplay()
   */

  @Override
  protected Promise<String> onStart(String state, Map<String, Object> params) {

    // TODO query from server rpc
    view.setMenuDatas(null);
    return super.onStart(state, params);

  }

  @Override
  public Display getDisplay() {

    return view;

  }

}

