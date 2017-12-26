/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.Locale;

import org.springframework.context.MessageSourceAware;

/**
 * @author CR
 *
 */
public interface MessageSourceHandler extends MessageSourceAware {

	String getMessage(String key, Locale locale);

}
