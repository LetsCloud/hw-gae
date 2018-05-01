/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging;

import hu.hw.cloud.client.firebase.HandlerRegistration;
import hu.hw.cloud.client.firebase.messaging.js.Fnx;
import hu.hw.cloud.client.firebase.messaging.js.Messaging;

/**
 * @author robi
 *
 */
public class MessagingRegistration implements HandlerRegistration {
	final Messaging messaging;
	final Fnx.Arg fn;
	Fnx.NoArg unsubscribe;

	public MessagingRegistration(Messaging messaging, Fnx.Arg fn) {
		this.messaging = messaging;
		this.fn = fn;
	}

	
	public Messaging getMessaging() {
		return messaging;
	}

	public Fnx.Arg getFn() {
		return fn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MessagingRegistration on() {
		this.unsubscribe = getMessaging().onMessage(getFn());
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MessagingRegistration off() {
		if (unsubscribe != null) {
			unsubscribe.apply();
		}
		return this;
	}
}