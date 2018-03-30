/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import hu.hw.cloud.shared.dto.ErrorResponseDto;

/**
 * A ControllerAdvice osztály a kontrolerekben le nem kezelt kivételek
 * lekezelését oldja meg.
 * 
 * @author CR
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> exceptionHandler(Exception ex) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		error.setMessage("Please contact your administrator");
		error.setMessage(ex.getClass().getName() + " / " + ex.getMessage());
		return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.OK);
	}
}