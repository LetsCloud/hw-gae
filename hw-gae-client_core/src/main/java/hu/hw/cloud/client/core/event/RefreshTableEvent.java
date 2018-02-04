/**
 * 
 */
package hu.hw.cloud.client.core.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author robi
 *
 */
public class RefreshTableEvent extends GwtEvent<RefreshTableEvent.RefreshTableHandler> {

	public interface RefreshTableHandler extends EventHandler {
		void onRefresh(RefreshTableEvent event);
	}

	public static final Type<RefreshTableHandler> TYPE = new Type<>();

	RefreshTableEvent() {
	}

	public static Type<RefreshTableHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new RefreshTableEvent());
	}

	@Override
	public Type<RefreshTableHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RefreshTableHandler handler) {
		handler.onRefresh(this);
	}
}