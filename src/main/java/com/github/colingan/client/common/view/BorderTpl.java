
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.view;

import com.github.colingan.client.common.BorderPage.Template;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


 /**
 * @title BorderTpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class BorderTpl extends Composite implements Template {

  @UiTemplate("ui/BorderTpl.ui.xml")
  interface Binder extends UiBinder<Widget, BorderTpl> {

  }

  private static Binder binder = GWT.create(Binder.class);

  @UiField
  HTMLPanel header;
  @UiField
  FlowPanel body;
  @UiField
  HTMLPanel footer;

  public BorderTpl() {
    initWidget(binder.createAndBindUi(this));
  }

  /* (non-Javadoc)
   * @see com.github.colingan.client.common.BorderPage.Template#getHeader()
   */

  @Override
  public HasWidgets getHeader() {

    return header;

  }

  /* (non-Javadoc)
   * @see com.github.colingan.client.common.BorderPage.Template#getBody()
   */

  @Override
  public HasWidgets getBody() {

    return body;

  }

  /* (non-Javadoc)
   * @see com.github.colingan.client.common.BorderPage.Template#getFooter()
   */

  @Override
  public HasWidgets getFooter() {

    return footer;

  }

  @Override
  public Widget asWidget() {
    return this;
  }

}

