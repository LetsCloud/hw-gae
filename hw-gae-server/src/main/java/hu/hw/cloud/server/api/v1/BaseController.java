/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.service.AccountService;
import hu.hw.cloud.shared.dto.ErrorResponseDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;
import hu.hw.cloud.shared.exception.RestApiException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public abstract class BaseController {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());

	@Autowired
	AccountService accountService;

	/**
	 * 
	 * @param request
	 * @param accountId
	 * @return
	 * @throws RestApiException
	 */
	public void accountIdValidation(WebRequest request, String accountId) throws RestApiException {
		LOGGER.info("accountIdValidation->accountId=" + accountId);
		if (!accountService.sameAccountIds(accountId, getAccountId(request)))
			throw new RestApiException(new Exception(ExceptionType.MISMATCHED_ACCOUNT + " " + accountId));
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<ErrorResponseDto> exceptionHandler(RestApiException ex) {
		LOGGER.info("exceptionHandler()");
		ErrorResponseDto error = new ErrorResponseDto();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ExceptionType.UNKNOWN.toString() + "-" + ex.getMessage());

		if (ex.getCause() instanceof EntityValidationException) {
			error.setExceptionType(ExceptionType.FAILD_ENTITIY_VALIDATION);
			error.setMessage(ExceptionType.FAILD_ENTITIY_VALIDATION.toString() + "-" + ex.getMessage());
		}

		if (ex.getCause() instanceof UniqueIndexConflictException) {
			error = createUiceResponse((UniqueIndexConflictException) ex.getCause());
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<ErrorResponseDto>(error, headers, HttpStatus.NOT_FOUND);
	}

	private ErrorResponseDto createUiceResponse(UniqueIndexConflictException e) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setExceptionType(ExceptionType.UNIQUE_INDEX_CONFLICT);
		error.setProperty(e.getProperty());
		error.setMessage(ExceptionType.UNIQUE_INDEX_CONFLICT.toString() + "-" + e.getMessage());
		return error;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public Long getAccountId(WebRequest request) {
		LOGGER.info("getAccountId()");
		String username = request.getUserPrincipal().getName();
		LOGGER.info("getAccountId()->username=" + username);
		String[] split = username.split(":");
		Long generatedId = new Long(split[1]);
		LOGGER.info("getAccountId()->generatedId=" + generatedId);
		return generatedId;
	}
}
