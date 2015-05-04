
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.view;

import java.util.List;
import java.util.Map;

import com.github.colingan.client.common.presenter.HeaderPresenter.View;
import com.github.colingan.shared.domain.Category;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;


 /**
 * @title HeaderView
 * @description TODO 
 * @author colingan
 * @date 2015-5-4
 * @version 1.0
 */

public class HeaderView extends Composite implements View {

  private static HeaderViewUiBinder uiBinder = GWT.create(HeaderViewUiBinder.class);

  @UiTemplate("ui/HeaderView.ui.xml")
  interface HeaderViewUiBinder extends UiBinder<Widget, HeaderView> {

  }

  @UiField
  MenuBar menus;

  public HeaderView() {
    initWidget(uiBinder.createAndBindUi(this));
  }


  @Override
  public void setMenuDatas(Map<Category, List<Category>> datas) {

    // TODO Auto-generated method stub
    this.mockDatas();
  }


  private void mockDatas() {
    Command cmd = new Command() {

      @Override
      public void execute() {
        Window.alert("You selected a menu item");
      }

    };

    // create submenus
    MenuBar fooMenu = new MenuBar(true);
    fooMenu.addItem("the", cmd);
    fooMenu.addItem("foo", cmd);
    fooMenu.addItem("menu", cmd);

    MenuBar barMenu = new MenuBar(true);
    barMenu.addItem("the", cmd);
    barMenu.addItem("bar", cmd);
    barMenu.addItem("menu", cmd);

    menus.addItem("foo", fooMenu);
    menus.addItem("bar", barMenu);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

}

