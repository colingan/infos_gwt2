package com.github.colingan.client.index.page;

import com.github.colingan.client.common.BorderPage;
import com.github.colingan.client.constants.AppConstants;
import com.github.colingan.client.index.component.IndexIndexComponent;

public class IndexPage extends BorderPage {

	public IndexPage() {
		super(AppConstants.Page.IndexPage, new IndexIndexComponent());
	}
}
