/**
 * 
 */
package hu.hw.cloud.client.firebase;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author robi
 *
 */
public class FirebaseRegister {

    private final Map<HandlerRegistration, Boolean> handlerRegistrations = Maps.newHashMap();

    public HandlerRegistration add(HandlerRegistration handlerRegistration, boolean auth, boolean on) {
        handlerRegistrations.put(handlerRegistration, auth);
        if (on) {
            handlerRegistration.on();
        } else {
            handlerRegistration.off();
        }
        return handlerRegistration;
    }

    public void enableFirebaseEvents() {
        Sets.newHashSet(handlerRegistrations.keySet()).forEach(HandlerRegistration::on);
    }

    public void disableFirebaseEvents() {
        Sets.newHashSet(handlerRegistrations.keySet()).forEach(HandlerRegistration::off);
    }

    public void removeRegistration(HandlerRegistration handlerRegistration) {
        if (handlerRegistration != null) {
            handlerRegistration.off();
            handlerRegistrations.remove(handlerRegistration);
        }
    }

    public void removeFirebaseAuthEvents() {
        Set<HandlerRegistration> authHandlerRegistrations = Maps
                .filterValues(handlerRegistrations, auth -> auth).keySet();
        Sets.newHashSet(authHandlerRegistrations).forEach(this::removeRegistration);
    }

    public void removeAllRegistrations() {
        Sets.newHashSet(handlerRegistrations.keySet()).forEach(this::removeRegistration);
    }
}
