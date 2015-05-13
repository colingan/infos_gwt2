
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common;

import com.github.colingan.client.common.component.HeaderComponent;
import com.github.colingan.client.common.view.BorderTpl;
import com.google.gwt.user.client.ui.HasWidgets;
import com.soso.tg.cmvp.client.component.Component;
import com.soso.tg.cmvp.client.display.Display;
import com.soso.tg.cmvp.client.display.ExclusiveDisplayContainer;


 /**
 * @title BorderPage
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class BorderPage extends InfosPage {

  public interface Template extends Display {
    HasWidgets getHeader();

    HasWidgets getBody();

    HasWidgets getFooter();
  }

  private final Template template;
  private final String name;

  public BorderPage(String name, Component body) {
    this.name = name;
    this.template = new BorderTpl();

    Component header = new HeaderComponent();
    initComponent(header);
    initComponent(body);
    header.setContainer(new ExclusiveDisplayContainer(template.getHeader()));
    body.setContainer(new ExclusiveDisplayContainer(template.getBody()));
  }


  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.component.Component#getName()
   */

  @Override
  public String getName() {

    return name;

  }

  /* (non-Javadoc)
   * @see com.soso.tg.cmvp.client.presenter.Presenter#getDisplay()
   */

  @Override
  public Display getDisplay() {

    return template;

  }

}

