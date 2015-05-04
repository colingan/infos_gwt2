
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.category.rpc;

import java.util.List;
import java.util.Map;

import com.github.colingan.shared.domain.Category;
import com.google.gwt.user.client.rpc.AsyncCallback;


 /**
 * @title CategoryRPCAsync
 * @description TODO 
 * @author colingan
 * @date 2015-5-4
 * @version 1.0
 */

public interface CategoryRPCAsync {

  void getCategoryBrief(AsyncCallback<Map<Category, List<Category>>> callback);

}

