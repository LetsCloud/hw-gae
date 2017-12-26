/**
 * 
 */
package hu.hw.cloud.shared.exception;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class UniqueIndexConflictException extends Exception {

	private String property;

	private Object value;

	public UniqueIndexConflictException(String property, Object value) {
		super();
		this.property = property;
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public Object getValue() {
		return value;
	}
}
