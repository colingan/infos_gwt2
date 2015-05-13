
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common;

import java.util.ArrayList;
import java.util.List;

import com.github.colingan.client.constants.AppConstants;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.soso.tg.cmvp.client.async.Promise;
import com.soso.tg.cmvp.client.async.Promises;
import com.soso.tg.cmvp.client.presenter.AbstractPresenter;


 /**
 * @title InfosPresenter
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public abstract class InfosPresenter extends AbstractPresenter {

  private List<HandlerRegistration> regs = new ArrayList<HandlerRegistration>();

  public final void gotoIndexIndexPage() {
    getParent().getPage().nextPage(AppConstants.Page.IndexPage, AppConstants.IndexPage.State.Index,
        null);
  }

  public final void firePageEvent(GwtEvent<?> event) {
    getParent().getPage().getPageEventBus().fireEvent(event);
  }

  public final <H extends EventHandler> void addPageHandler(GwtEvent.Type<H> type, final H handler) {
    HandlerRegistration reg = getParent().getPage().getPageEventBus().addHandler(type, handler);
    regs.add(reg);
  }

  @Override
  protected Promise<String> onStop() {
    for (HandlerRegistration reg : regs) {
      reg.removeHandler();
    }
    return Promises.done();
  }

}

