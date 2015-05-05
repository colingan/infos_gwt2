
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.category;

import java.util.Map;
import java.util.Set;

import com.github.colingan.shared.domain.Category;


 /**
 * @title CategoryService
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public interface CategoryService {

  public Map<Category, Set<Category>> getValidCategoryBrief();
}

