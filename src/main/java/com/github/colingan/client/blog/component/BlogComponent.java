
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.blog.component;

import com.github.colingan.client.constants.AppConstants;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.soso.tg.cmvp.client.component.ExclusiveCompositeComponent;
import com.soso.tg.cmvp.client.display.Display;
import com.soso.tg.cmvp.client.display.ExclusiveDisplayContainer;


 /**
 * @title BlogComponent
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogComponent extends ExclusiveCompositeComponent {

  public interface Template extends Display {
    HasWidgets getContent();
  }

  private Template content = new Template() {

    private FlowPanel content = new FlowPanel();

    @Override
    public Widget asWidget() {
      return content;
    }

    @Override
    public HasWidgets getContent() {
      return content;
    }

  };

  public BlogComponent() {
    BlogViewComponent view = new BlogViewComponent();
    initComponent(view);
    view.setContainer(new ExclusiveDisplayContainer(content.getContent()));
    BlogEditComponent edit = new BlogEditComponent();
    initComponent(edit);
    edit.setContainer(new ExclusiveDisplayContainer(content.getContent()));
  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getName()
   */

  @Override
  public String getName() {

    return AppConstants.Component.BlogComponent;

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.presenter.Presenter#getDisplay()
   */

  @Override
  public Display getDisplay() {

    return content;

  }

}

