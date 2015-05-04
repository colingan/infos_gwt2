package com.github.colingan.client.index.view;

import com.github.colingan.client.index.presenter.IndexIndexPresenter.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IndexIndexView extends Composite implements View {
	
	private static Binder uiBinder = GWT.create(Binder.class);
	
	@UiTemplate("ui/IndexIndexView.ui.xml")
	interface Binder extends UiBinder<Widget, IndexIndexView> {
		
	}
	
	public IndexIndexView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	@Override
	public void onStart() {

	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

}
