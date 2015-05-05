
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.member;

import com.github.colingan.shared.domain.Member;


 /**
 * @title MemberService
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */

public interface MemberService {

  public Member queryMemberByUserName(String userName);
}

