/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import com.gwtplatform.dispatch.shared.ActionException;

import hu.hw.cloud.shared.dto.ErrorResponseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CustomActionException extends ActionException {

	private ErrorResponseDto erDto;
	
    public CustomActionException() {
    	super();
    }

    public CustomActionException(String message) {
        super(message);
    }

    public CustomActionException(String message, ErrorResponseDto erDto) {
        super(message);
        this.erDto = erDto;
    }

    public CustomActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomActionException(Throwable cause) {
        super(cause.getMessage());
    }
	public ErrorResponseDto getErDto() {
		return erDto;
	}

	public void setErDto(ErrorResponseDto erDto) {
		this.erDto = erDto;
	}

}
