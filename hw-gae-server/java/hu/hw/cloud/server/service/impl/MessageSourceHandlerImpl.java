package hu.hw.cloud.server.service.impl;

import java.util.Locale;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Component;

import hu.hw.cloud.server.service.MessageSourceHandler;

//@Component
public class MessageSourceHandlerImpl implements MessageSourceHandler {

//	@Autowired
	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public String getMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}
}
