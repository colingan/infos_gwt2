
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.rpc;

import com.github.colingan.client.common.rpc.PageRPC;
import com.github.colingan.client.common.rpc.exception.RpcException;
import com.github.colingan.client.login.InfosToken;
import com.github.colingan.shared.PageInitData;
import com.google.inject.Singleton;


 /**
 * @title PageRPCImpl
 * @author colingan
 * @date 2015-5-6
 * @version 1.0
 */
@Singleton
public class PageRPCImpl extends AuthRPCImpl implements PageRPC {
  

  /* (non-Javadoc)
   * @see com.github.colingan.client.common.rpc.PageRPC#getInitData(java.lang.String)
   */

  @Override
  public PageInitData getInitData(String href) throws RpcException {
    PageInitData initData = InfosToken.getInitData(getThreadLocalRequest());
    return initData;
  }

}

