
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.presenter;

import java.util.Map;
import java.util.Set;

import com.github.colingan.client.common.InfosPresenter;
import com.github.colingan.client.common.view.HeaderView;
import com.github.colingan.client.constants.rpcs.RpcHolder;
import com.github.colingan.shared.domain.Category;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

    void setMenuDatas(Map<Category, Set<Category>> datas);
  }

  protected final View view;

  public HeaderPresenter() {
    view = new HeaderView(this);
  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.presenter.Presenter#getDisplay()
   */

  @Override
  protected Promise<String> onStart(String state, Map<String, Object> params) {

    RpcHolder.getCategoryRPC().getCategoryBrief(new AsyncCallback<Map<Category,Set<Category>>>() {
      
      @Override
      public void onSuccess(Map<Category, Set<Category>> result) {
        
        view.setMenuDatas(result);
      }
      
      @Override
      public void onFailure(Throwable caught) {
        
        Window.alert("页面加载失败");
        GWT.log("分类拉取失败", caught);
      }
    });
    return super.onStart(state, params);

  }

  @Override
  public Display getDisplay() {

    return view;

  }

}

