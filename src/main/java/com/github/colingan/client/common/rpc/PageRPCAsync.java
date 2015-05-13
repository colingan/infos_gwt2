
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.common.rpc;

import com.github.colingan.shared.PageInitData;
import com.google.gwt.user.client.rpc.AsyncCallback;


 /**
 * @title PageRPCAsync
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public interface PageRPCAsync {

  void getInitData(String href, AsyncCallback<PageInitData> callback);

}

