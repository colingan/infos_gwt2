
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.server.member.impl;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.EbeanServer;
import com.github.colingan.server.member.MemberService;
import com.github.colingan.server.utils.DB;
import com.github.colingan.server.utils.EBeanEnvUtils;
import com.github.colingan.shared.domain.Member;
import com.google.inject.Singleton;


 /**
 * @title MemberServiceImpl
 * @description TODO 
 * @author colingan
 * @date 2015-5-5
 * @version 1.0
 */
@Singleton
public class MemberServiceImpl implements MemberService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
  private static final EbeanServer infosServer = EBeanEnvUtils.get(DB.INFOS);

  public static final Member MEMBER_NOBODY = new Member();

  /* (non-Javadoc)
   * @see com.github.colingan.server.member.MemberService#queryMemberByUserName(java.lang.String)
   */

  @Override
  public final Member queryMemberByUserName(String userName) {

    Validate.notNull(userName);
    Member member = infosServer.find(Member.class)//
        .where()//
        .eq("user_name_", userName)//
        .eq("is_del_", false)//
        .findUnique();

    if (member == null) {
      return MEMBER_NOBODY;
    }
    return member;

  }

}

