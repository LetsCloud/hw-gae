package hu.hw.cloud.server.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.hw.cloud.shared.cnst.ErrorCode;
import hu.hw.cloud.shared.dto.ErrorResponseDto;
import hu.hw.cloud.shared.exception.ExceptionType;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger LOGGER = Logger.getLogger(AuthFailureHandler.class.getName());

	private final ObjectMapper mapper;

	public AuthFailureHandler(MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.info("onAuthenticationFailure->exception=" + exception.toString());

		ErrorResponseDto error = new ErrorResponseDto();

		String message = ErrorCode.LOGIN_UNKNOWN_PROBLEM.name();

		if (exception instanceof org.springframework.security.authentication.InsufficientAuthenticationException) {
			error.setExceptionType(ExceptionType.LOGIN_INSUFFICIENT_AUTHENTICATION);
			message = ExceptionType.LOGIN_INSUFFICIENT_AUTHENTICATION.name();
		}
		if (exception instanceof org.springframework.security.core.userdetails.UsernameNotFoundException) {
			error.setExceptionType(ExceptionType.LOGIN_USERNAME_NOT_FOUND);
			message = ExceptionType.LOGIN_USERNAME_NOT_FOUND.name();
		}
		if (exception instanceof org.springframework.security.authentication.BadCredentialsException) {
			error.setExceptionType(ExceptionType.LOGIN_BAD_CREDENTIALS);
			message = ExceptionType.LOGIN_BAD_CREDENTIALS.name();
		}
		if (exception instanceof org.springframework.security.authentication.DisabledException) {
			error.setExceptionType(ExceptionType.LOGIN_DISABLED_USER);
			message = ExceptionType.LOGIN_DISABLED_USER.name();
		}
		error.setMessage(message);

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// response.setStatus(HttpServletResponse.SC_CONFLICT);

		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, error);
		// writer.write(message);

		writer.flush();
	}
}
