
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.category.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.EbeanServer;
import com.github.colingan.server.category.CategoryService;
import com.github.colingan.server.utils.DB;
import com.github.colingan.server.utils.EBeanEnvUtils;
import com.github.colingan.shared.domain.Category;
import com.github.colingan.shared.domain.type.CategoryLevel;
import com.google.inject.Singleton;


 /**
 * @title CategoryServiceImpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */
@Singleton
public class CategoryServiceImpl implements CategoryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

  private static final EbeanServer infosServer = EBeanEnvUtils.get(DB.INFOS);

  /* (non-Javadoc)
   * @see com.github.colingan.server.category.CategoryService#getValidCategoryBrief()
   */

  @Override
  public Map<Category, Set<Category>> getValidCategoryBrief() {
    
    List<Category> categorys = infosServer.find(Category.class)//
        .where()//
        .eq("is_del_", false)//
        .orderBy().asc("level_")//
        .orderBy().asc("id_")//
        .findList();
    Map<Category, Set<Category>> rel = new HashMap<Category, Set<Category>>();

    if (categorys != null) {
      for (Category category : categorys) {
        CategoryLevel level = CategoryLevel.getByValue(category.getLevel_());
        if (level == null) {
          LOGGER.warn(String.format("invalid category by level found: %s", category));
          continue;
        }
        if (level == CategoryLevel.LEVEL1) {
          if (rel.containsKey(category)) {
            LOGGER.warn("duplicated category found: %s", category);
            continue;
          }
          rel.put(category, new LinkedHashSet<Category>());
        } else {
          Category parent = new Category();
          parent.setId_(category.getParent_category_());
          Set<Category> list = rel.get(parent);
          if (list == null) {
            LOGGER.error(String.format("invalid category find, no parent category exists. %s",
                category));
            continue;
          }
          list.add(category);
        }
      }
    }

    return rel;

  }

  public static void main(String[] args) {
    CategoryService service = new CategoryServiceImpl();
    Map<Category, Set<Category>> datas = service.getValidCategoryBrief();
    System.out.println(datas == null);
    System.out.println(datas.size());
  }
}

