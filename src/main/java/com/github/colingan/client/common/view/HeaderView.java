
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.colingan.client.common.InfosPresenter;
import com.github.colingan.client.common.events.CategoryEnterEvent;
import com.github.colingan.client.common.presenter.HeaderPresenter.View;
import com.github.colingan.shared.domain.Category;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Command;
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

  private InfosPresenter presenter;

  public HeaderView(InfosPresenter presenter) {
    initWidget(uiBinder.createAndBindUi(this));
    this.presenter = presenter;
  }


  @Override
  public void setMenuDatas(Map<Category, Set<Category>> datas) {

    if (datas != null && datas.size() > 0) {
      for (Entry<Category, Set<Category>> entry : datas.entrySet()) {
        Category parent = entry.getKey();
        MenuBar parentBar = new MenuBar(true);
        for (Category child : entry.getValue()) {
          parentBar.addItem(child.getName_(),
              new CategoryEnterCommand(child.getId_(), parent.getId_()));
        }
        this.menus.addItem(parent.getName_(), parentBar);
      }
    }
  }

  private class CategoryEnterCommand implements Command {

    private long categoryId;
    private long parentCategoryId;

    public CategoryEnterCommand(long categoryId, long parentCategoryId) {
      this.categoryId = categoryId;
      this.parentCategoryId = parentCategoryId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.Command#execute()
     */

    @Override
    public void execute() {
      presenter.firePageEvent(new CategoryEnterEvent(this.parentCategoryId, this.categoryId));
    }

  }

  @Override
  public Widget asWidget() {
    return this;
  }

}

