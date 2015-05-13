package com.github.colingan.client;

import com.github.colingan.client.common.InfosPageFactoryAsync;
import com.github.colingan.client.constants.AppConstants;
import com.github.colingan.client.constants.AppUtil;
import com.github.colingan.client.constants.rpcs.RpcHolder;
import com.github.colingan.shared.PageInitData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.soso.tg.cmvp.client.AppController;
import com.soso.tg.cmvp.client.async.Promise;
import com.soso.tg.cmvp.client.async.Promises;
import com.soso.tg.cmvp.client.history.HistoryObserver;
import com.soso.tg.cmvp.client.page.Page;
import com.soso.tg.cmvp.client.squeeze.Token;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author colingan
 * Created: Sun May 03 14:15:55 CST 2015
 */
public class infos_gwt implements EntryPoint {

  @Override
  public void onModuleLoad() {

    RpcHolder.getPageRPC().getInitData(Window.Location.getHref(),
        new AsyncCallback<PageInitData>() {

          @Override
          public void onSuccess(PageInitData result) {
            Element ele = DOM.getElementById("loading");
            if (ele != null) {
              ele.removeFromParent();
            }
            // no login requires
            AppUtil.setPageInitData(result);
            RpcUtils.addRpcToken(RpcHolder.getPageRPC());
            new AppController(new InfosPageFactoryAsync(), new HistoryObserver() {

              @Override
              public Promise<Boolean> onTokenChange(Token token, Page currentPage) {

                return Promises.True();

              }
            }).start(AppConstants.Page.IndexPage + "/" + AppConstants.IndexPage.State.Index);
            }

          @Override
          public void onFailure(Throwable caught) {

            Window.alert("页面加载失败" + caught.getMessage());
            return;
              }
        });
  }
}
