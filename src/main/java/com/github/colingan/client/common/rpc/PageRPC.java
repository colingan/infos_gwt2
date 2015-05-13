
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.rpc;

import com.github.colingan.client.common.rpc.exception.RpcException;
import com.github.colingan.shared.PageInitData;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;
import com.google.gwt.user.server.rpc.NoXsrfProtect;


 /**
 * @title PageRPC
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */
@RemoteServiceRelativePath("page")
public interface PageRPC extends XsrfProtectedService {

  @NoXsrfProtect
  PageInitData getInitData(String href) throws RpcException;
}

