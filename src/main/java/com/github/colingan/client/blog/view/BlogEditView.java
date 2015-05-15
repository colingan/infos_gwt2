
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.blog.view;

import com.github.colingan.client.blog.presenter.BlogEditPresenter.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


 /**
 * @title BlogEditView
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogEditView extends Composite implements View {

  @UiTemplate("ui/BlogEditView.ui.xml")
  interface Binder extends UiBinder<Widget, BlogEditView> {

  }

  private static Binder uiBinder = GWT.create(Binder.class);

  @UiField
  Hidden id;
  @UiField
  TextBox title;
  @UiField
  TextArea content;
  @UiField
  Button submit;
  @UiField
  Button reset;

  public BlogEditView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}

