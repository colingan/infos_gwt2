package com.github.colingan.inject;

import com.github.colingan.server.InfosServiceModule;
import com.github.colingan.servlet.InfosServletModule;
import com.google.inject.AbstractModule;

@Inherits({InfosServletModule.class, InfosServiceModule.class})
public class InfosModule extends AbstractModule {

  @Override
  protected void configure() {
  }

}
