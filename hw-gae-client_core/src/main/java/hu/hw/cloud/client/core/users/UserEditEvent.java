/**
 * 
 */
package hu.hw.cloud.client.core.users;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author CR
 *
 */
public class UserEditEvent  extends GwtEvent<UserEditEvent.UserEditEventHandler> {
	private static Logger logger = Logger.getLogger(UserEditEvent.class.getName());

	public interface UserEditEventHandler extends EventHandler {
		void onEditUser(UserEditEvent event);
	}

	public static final Type<UserEditEventHandler> TYPE = new Type<>();

	public UserEditEvent() {
	}

	public static void fire(HasHandlers source) {
		logger.log(Level.INFO, "UserEditEvent.fire()");
		source.fireEvent(new UserEditEvent());
	}

	@Override
	public Type<UserEditEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UserEditEventHandler handler) {
		handler.onEditUser(this);
	}
}