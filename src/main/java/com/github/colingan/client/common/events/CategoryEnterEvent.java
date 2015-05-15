
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.events;

import com.google.gwt.event.shared.GwtEvent;


 /**
 * @title CategoryEnterEvent
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class CategoryEnterEvent extends GwtEvent<CategoryEnterEventHandler> {

  private static final Type<CategoryEnterEventHandler> TYPE = new Type<CategoryEnterEventHandler>();

  private final long parentCategoryId;
  private final long categoryId;


  /**
   * @param parentCategoryId
   * @param categoryId
   */

  public CategoryEnterEvent(long parentCategoryId, long categoryId) {
    super();
    this.parentCategoryId = parentCategoryId;
    this.categoryId = categoryId;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
   */

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<CategoryEnterEventHandler> getAssociatedType() {

    return TYPE;

  }

  /* (non-Javadoc)
   * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
   */

  @Override
  protected void dispatch(CategoryEnterEventHandler handler) {

    handler.onEnter(this);
  }

}

