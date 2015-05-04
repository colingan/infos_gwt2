package com.github.colingan.client.index.component;

import java.util.Set;

import com.github.colingan.client.common.InfosComponent;
import com.github.colingan.client.constants.AppConstants;
import com.github.colingan.client.index.presenter.IndexIndexPresenter;
import com.soso.tg.cmvp.client.presenter.Presenter;
import com.soso.tg.cmvp.client.util.CMVP;

public class IndexIndexComponent extends InfosComponent {

	@Override
	public String getName() {
		return AppConstants.Component.ViewIndexComponent;
	}

	@Override
	public Set<String> getStates() {
		return CMVP.asSet(AppConstants.IndexPage.State.Index);
	}

	@Override
	protected Presenter initPresenter() {
		return new IndexIndexPresenter();
	}

}
