
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.rpc;

import java.util.Map;
import java.util.Set;

import com.github.colingan.client.category.rpc.CategoryRPC;
import com.github.colingan.client.common.rpc.exception.RpcException;
import com.github.colingan.server.category.CategoryService;
import com.github.colingan.shared.domain.Category;
import com.google.inject.Inject;
import com.google.inject.Singleton;


 /**
 * @title CategoryRPCImpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-14
 * @version 1.0
 */
@Singleton
public class CategoryRPCImpl extends AuthRPCImpl implements CategoryRPC {

  @Inject
  private CategoryService categoryService;

  /* (non-Javadoc)
   * @see com.github.colingan.client.category.rpc.CategoryRPC#getCategoryBrief()
   */

  @Override
  public Map<Category, Set<Category>> getCategoryBrief() throws RpcException {

    return categoryService.getValidCategoryBrief();

  }

}

