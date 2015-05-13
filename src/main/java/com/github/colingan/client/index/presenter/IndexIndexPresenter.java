package com.github.colingan.client.index.presenter;

import java.util.Map;

import com.github.colingan.client.common.InfosPresenter;
import com.github.colingan.client.index.view.IndexIndexView;
import com.soso.tg.cmvp.client.async.Promise;
import com.soso.tg.cmvp.client.async.Promises;
import com.soso.tg.cmvp.client.display.Display;

public class IndexIndexPresenter extends InfosPresenter {
	
	public interface View extends Display {
		
		void onStart();
	}
	
	private final View view;
	
	public IndexIndexPresenter() {
		this.view = new IndexIndexView();
	}

	@Override
	public Display getDisplay() {
		return view;
	}

	@Override
	protected Promise<String> onStart(String state, Map<String, Object> params) {
		view.onStart();
		return Promises.done();
	}

}
