/**
 * 
 */
package hu.hw.cloud.shared.exception;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RestApiException extends Exception {

	public RestApiException(Throwable e) {
		super(e);
	}
}
