package com.github.colingan.server;

import com.github.colingan.server.category.CategoryService;
import com.github.colingan.server.category.impl.CategoryServiceImpl;
import com.github.colingan.server.member.MemberService;
import com.github.colingan.server.member.impl.MemberServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class InfosServiceModule extends AbstractModule {

  @Override
  protected void configure() {

    bind(MemberService.class).to(MemberServiceImpl.class).in(Singleton.class);
    bind(CategoryService.class).to(CategoryServiceImpl.class).in(Singleton.class);
  }

}
