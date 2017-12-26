/**
 * 
 */
package hu.hw.cloud.client.core.util;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import hu.hw.cloud.client.core.event.DisplayMessageEvent;
import hu.hw.cloud.client.core.ui.message.Message;
import hu.hw.cloud.client.core.ui.message.MessageStyle;
import hu.hw.cloud.client.core.util.exceptiontranslators.ForeignTranslator;
import hu.hw.cloud.client.core.util.exceptiontranslators.NotNullTranslator;
import hu.hw.cloud.client.core.util.exceptiontranslators.Translator;

/**
 * @author CR
 *
 */
public abstract class ErrorHandlerAsyncCallback<R> implements AsyncCallback<R> {

	private final HasHandlers hasHandlers;

	public ErrorHandlerAsyncCallback(HasHandlers hasHandlers) {
		this.hasHandlers = hasHandlers;
	}

	@Override
	public void onFailure(Throwable caught) {
		Message message = new Message(translateCauses(caught), MessageStyle.ERROR);
		DisplayMessageEvent.fire(hasHandlers, message);
	}

	private String translateCauses(Throwable caught) {
		StringBuilder sb = new StringBuilder(translateCause(caught));

		for (Throwable t = caught.getCause(); t != null; t = t.getCause()) {
			sb = sb.append(translateCause(t)).append("<br />");
		}

		return sb.toString();
	}

	private String translateCause(Throwable caught) {
		String message = caught.getMessage();

		Translator translator = new NotNullTranslator(message);
		if (translator.isMatching()) {
			return translator.getTranslatedMessage();
		}

		translator = new ForeignTranslator(message);
		if (translator.isMatching()) {
			return translator.getTranslatedMessage();
		}

		return message;
	}
}
