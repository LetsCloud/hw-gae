/**
 * 
 */
package hu.hw.cloud.client.fro.meditor.marketgroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.config.hotel.HotelConfigPresenter;
import hu.hw.cloud.client.fro.config.profile.ProfileConfigPresenter;
import hu.hw.cloud.client.fro.meditor.AbstractMeditorPresenter;
import hu.hw.cloud.client.fro.meditor.MeditorView;

import hu.hw.cloud.shared.api.MarketGroupResource;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupEditorPresenter
		extends AbstractMeditorPresenter<MarketGroupDto, MarketGroupEditorPresenter.MyView>
		implements MarketGroupEditorUiHandlers {
	private static Logger logger = Logger.getLogger(MarketGroupEditorPresenter.class.getName());

	public interface MyView extends MeditorView<MarketGroupDto>, HasUiHandlers<MarketGroupEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<MarketGroupResource> resourceDelegate;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	MarketGroupEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<MarketGroupResource> resourceDelegate, HotelDataSource hotelDataSource,
			CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, view);
		logger.info("MarketGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected MarketGroupDto createDto() {
		MarketGroupDto dto = new MarketGroupDto();
		dto.setHotel(currentUser.getAppUserDto().getDefaultHotel());
		return dto;
	}

	@Override
	public void saveDto(MarketGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<MarketGroupDto>() {
			@Override
			public void onSuccess(MarketGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.HOTEL_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, HotelConfigPresenter.MARKET_GROUPS).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
}