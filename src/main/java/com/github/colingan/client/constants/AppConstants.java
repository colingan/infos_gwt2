
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.client.constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;


 /**
 * @title AppConstants
 * @description TODO 
 * @author colingan
 * @date 2015-5-3
 * @version 1.0
 */

public interface AppConstants extends Constants {

  public final static String PAGE_INIT_DATA = "pageInitData";

  public final static AppConstants i18n = GWT.create(AppConstants.class);

  public final static class URL {
    public final static String BaseUrl = GWT.getHostPageBaseURL();
  }

  public final static class Page {
    public final static String IndexPage = "index"; // 首页
    public final static String BlogPage = "blog"; // 文章页

  }

  public final static class IndexPage {
    public final static class State {
      public final static String Index = "index"; // ��ҳ
    }
  }

  public final static class BlogPage {
    public final static class State {
      public final static String Edit = "edit"; // 编辑
      public final static String View = "view"; // 查看
    }
  }

  public final static class DataSourcePage {
    public final static class State {
      public final static String ViewList = "view"; // �鿴�б�
    }
  }

  public final static class AddDataSourcePage {
    public final static class State {
      public final static String AddSite = "addsite"; // ����
      public final static String AddFile = "addfile"; // ����
    }
  }

  public final static class SegmentPage {
    public final static class State {
      public final static String ViewList = "view"; // �鿴�б�
      public final static String Edit = "edit"; // �༭��Ⱥ
      public final static String Add = "add"; // �����Ⱥ
    }
  }

  public final static class DemographyPage {
    public final static class State {
      public final static String All = "all"; // ȫ��͸��ͼ
      public final static String Age = "age"; // ����
      public final static String Gender = "gender"; // �Ա�
      public final static String Geo = "geo"; // ����
      public final static String Degree = "degree"; // ѧ��
      public final static String Interest = "interest"; // ��Ȥ
    }
  }

  public final static class Component {
    public final static String Navigation = "navigation";
    public final static String Nav = "nav";
    public final static String Header = "header";
    public final static String Footer = "footer";

    public final static String ViewIndexComponent = "index-view";

    public final static String BlogComponent = "blog";
    public final static String BlogViewComponent = "blog-view";
    public final static String BlogEditComponent = "blog-edit";
  }

  public final static class Param {
  }
}

