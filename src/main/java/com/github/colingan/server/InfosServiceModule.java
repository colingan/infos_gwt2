package com.github.colingan.server;

import com.github.colingan.server.member.MemberService;
import com.github.colingan.server.member.impl.MemberServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class InfosServiceModule extends AbstractModule {

  @Override
  protected void configure() {

    bind(MemberService.class).to(MemberServiceImpl.class).in(Singleton.class);
  }

}
