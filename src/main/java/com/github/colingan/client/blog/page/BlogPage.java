
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.blog.page;

import com.github.colingan.client.blog.component.BlogComponent;
import com.github.colingan.client.common.BorderPage;
import com.github.colingan.client.constants.AppConstants;


 /**
 * @title BlogEditPage
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */

public class BlogPage extends BorderPage {

  public BlogPage() {
    super(AppConstants.Page.BlogPage, new BlogComponent());
  }
}

