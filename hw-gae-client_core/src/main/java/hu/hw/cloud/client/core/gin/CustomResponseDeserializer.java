/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.HttpHeaders;

import com.google.gwt.http.client.Response;
import com.gwtplatform.dispatch.rest.client.core.ResponseDeserializer;
import com.gwtplatform.dispatch.rest.client.serialization.Serialization;
import com.gwtplatform.dispatch.rest.client.serialization.SerializationException;
import com.gwtplatform.dispatch.rest.shared.ContentType;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.shared.ActionException;

import hu.hw.cloud.shared.dto.ErrorResponseDto;

/**
 * @author robi
 *
 */
public class CustomResponseDeserializer implements ResponseDeserializer {
	private static Logger logger = Logger.getLogger(CustomResponseDeserializer.class.getName());

	private final Provider<Set<Serialization>> serializationsProvider;

	private Set<Serialization> serializations;

	@Inject
	protected CustomResponseDeserializer(Provider<Set<Serialization>> serializationsProvider) {
		this.serializationsProvider = serializationsProvider;
	}

	@Override
	public <A extends RestAction<R>, R> R deserialize(A action, Response response) throws ActionException {
		if (isSuccessStatusCode(response)) {
			return getDeserializedResponse(action, response);
		} else {
			String resultClass = ErrorResponseDto.class.getName();
			logger.log(Level.INFO, "deserialize()->resultClass=" + resultClass);

			ContentType contentType = ContentType.valueOf(response.getHeader(HttpHeaders.CONTENT_TYPE));
			logger.log(Level.INFO, "deserialize()->contentType=" + contentType);

			Serialization serialization = findSerialization(resultClass, contentType);

			if (serialization != null) {
				logger.log(Level.INFO, "deserialize()->serialization != null");
				ErrorResponseDto dto = deserializeValue(serialization, resultClass, contentType, response.getText());
				throw new CustomActionException(response.getText(), dto);
			}
			logger.log(Level.INFO, "deserialize()->END");

			throw new ActionException(response.getText() + "XX");
		}
	}

	/**
	 * Find a deserializer capable of handling <code>resultType</code> and
	 * <code>contentType</code>.
	 *
	 * @param resultType
	 *            the parameterized type to verify if it can be deserialized.
	 * @param contentType
	 *            the contentType of the value that requires deserialization.
	 *
	 * @return <code>true</code> if <code>resultType</code> can be deserialized,
	 *         <code>false</code> otherwise.
	 */
	protected Serialization findSerialization(String resultType, ContentType contentType) {
		logger.log(Level.INFO, "findSerialization()->resultType=" + resultType);
		logger.log(Level.INFO, "findSerialization()->contentType.getType()=" + contentType.getType());
		logger.log(Level.INFO, "findSerialization()->contentType.getSubType()=" + contentType.getSubType());
		for (Serialization serialization : getSerializations()) {
			logger.log(Level.INFO, "findSerialization()->serialization=" + serialization);
			if (serialization.canDeserialize(resultType, contentType)) {
				return serialization;
			}
		}

		return null;
	}

	/**
	 * Deserializes the data as an object of the <code>resultClass</code> type using
	 * the given <code>serialization</code> instance.
	 *
	 * @param <R>
	 *            the type of the object once deserialized.
	 * @param serialization
	 *            the serialization object to be used.
	 * @param resultClass
	 *            the parameterized type of the object once deserialized.
	 * @param contentType
	 *            the contentType of <code>data</code>.
	 * @param data
	 *            the data to deserialize. @return The deserialized object.
	 */
	protected <R> R deserializeValue(Serialization serialization, String resultClass, ContentType contentType,
			String data) throws ActionException {
		try {
			return serialization.deserialize(resultClass, contentType, data);
		} catch (SerializationException e) {
			throw new ActionException(e);
		}
	}

	private boolean isSuccessStatusCode(Response response) {
		int statusCode = response.getStatusCode();

		return (statusCode >= 200 && statusCode < 300) || statusCode == 304;
	}

	private <R> R getDeserializedResponse(RestAction<R> action, Response response) throws ActionException {
		String resultClass = action.getResultClass();
		logger.log(Level.INFO, "getDeserializedResponse()->resultClass=" + resultClass);

		if (resultClass != null) {
			ContentType contentType = ContentType.valueOf(response.getHeader(HttpHeaders.CONTENT_TYPE));
			logger.log(Level.INFO, "getDeserializedResponse()->contentType=" + contentType);
			Serialization serialization = findSerialization(resultClass, contentType);

			if (serialization != null) {
				return deserializeValue(serialization, resultClass, contentType, response.getText());
			}
		}

		throw new ActionException("Unable to deserialize response. No serializer found.");
	}

	private Set<Serialization> getSerializations() {
		if (serializations == null) {
			serializations = serializationsProvider.get();
		}

		return serializations;
	}
}