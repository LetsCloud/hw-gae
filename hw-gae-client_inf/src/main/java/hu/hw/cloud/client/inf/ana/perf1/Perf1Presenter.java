/**
 * 
 */
package hu.hw.cloud.client.inf.ana.perf1;

import static hu.hw.cloud.shared.api.ApiParameters.DATE_FORMAT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.data.loader.LoadResult;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.event.ContentPushEvent.MenuState;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.inf.InfNameTokens;
import hu.hw.cloud.shared.api.CubeResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.cnst.cube.AnalyticsType;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.cnst.cube.DataWidgetFieldType;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;
import hu.hw.cloud.shared.dto.cube.Perf1Dto;
import hu.hw.cloud.shared.dto.cube.Perf1QueryParamDto;
import hu.hw.cloud.shared.dto.cube.query.CubeMeasureParamDto;
import hu.hw.cloud.shared.dto.cube.query.CubeQueryParamDto;

/**
 * @author CR
 *
 */
public class Perf1Presenter extends Presenter<Perf1Presenter.MyView, Perf1Presenter.MyProxy>
		implements Perf1UiHandlers {
	private static final Logger LOGGER = Logger.getLogger(Perf1Presenter.class.getName());
	private static final String PARAM_NAME = "type";

	interface MyView extends View, HasUiHandlers<Perf1UiHandlers> {
		void setData(List<Perf1Dto> date);

		void refresh(MenuState menuState);
	}

	@ProxyCodeSplit
	@NameToken(InfNameTokens.PERF1)
	interface MyProxy extends ProxyPlace<Perf1Presenter> {
	}

	private final PlaceManager placeManager;

	private final ResourceDelegate<CubeResource> dataCubeDelegate;
	private final CurrentUser currentUser;
	private final String unauthorizedPlace;

	@Inject
	Perf1Presenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<CubeResource> dataCubeDelegate, CurrentUser currentUser,
			@UnauthorizedPlace String unauthorizedPlace) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		LOGGER.log(Level.INFO, "Perf1Presenter()");

		this.placeManager = placeManager;
		this.dataCubeDelegate = dataCubeDelegate;
		this.currentUser = currentUser;
		this.unauthorizedPlace = unauthorizedPlace;

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		String type = request.getParameter(PARAM_NAME, AnalyticsType.UNKNOWN.toString());

	}

	@Override
	protected void onReveal() {
		SetPageTitleEvent.fire("Szobaéj teljesítmény", "", MenuItemType.SUB_MENU, this);
		Timer timer = new Timer() {
			public void run() {
				loadData();
	}
};
timer.schedule(100);
	}

	private void loadData() {
		LOGGER.log(Level.INFO, "Perf1Presenter.loadData()");
		
		Perf1QueryParamDto paramDto = new Perf1QueryParamDto();
		paramDto.setFromBaseDate(new Date());
		paramDto.setFromDate(new Date());
		paramDto.setToBaseDate(new Date());
		paramDto.setToDate(new Date());
		
		dataCubeDelegate.withCallback(new AbstractAsyncCallback<List<Perf1Dto>>() {
			@Override
			public void onSuccess(List<Perf1Dto> result) {
				LOGGER.log(Level.INFO, "Perf1Presenter.loadData.onSuccess()");
				getView().setData(result);
			}
		}).perf1Query(paramDto);

	}
}
