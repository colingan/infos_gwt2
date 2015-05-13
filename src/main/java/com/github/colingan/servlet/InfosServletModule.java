package com.github.colingan.servlet;

import com.github.colingan.server.rpc.PageRPCImpl;
import com.google.inject.servlet.ServletModule;

public class InfosServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    // rpc
    serve("/infos_gwt/page").with(PageRPCImpl.class);

    serve("/groovy").with(GroovyServlet.class);

  }

}
