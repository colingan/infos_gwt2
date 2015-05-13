
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.constants.rpcs;

import com.github.colingan.client.RpcUtils;
import com.github.colingan.client.common.rpc.PageRPC;
import com.github.colingan.client.common.rpc.PageRPCAsync;
import com.google.gwt.core.client.GWT;


 /**
 * @title RpcHolder
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public class RpcHolder {

  static class PageRpcHolder {
    private static PageRPCAsync pageRPC = RpcUtils.createTimeoutRPC(GWT.create(PageRPC.class));
  }

  public final static PageRPCAsync getPageRPC() {
    return PageRpcHolder.pageRPC;
  }
}

