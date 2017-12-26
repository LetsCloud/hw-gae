package hu.hw.cloud.server.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import hu.hw.cloud.shared.cnst.ErrorCode;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger LOGGER = Logger.getLogger(AuthFailureHandler.class.getName());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.info("onAuthenticationFailure->exception=" + exception.toString());

		String message = ErrorCode.LOGIN_UNKNOWN_PROBLEM.name();
		if (exception instanceof org.springframework.security.authentication.BadCredentialsException) {
			message = ErrorCode.LOGIN_BAD_CREDENTIALS.name();
		}
		if (exception instanceof org.springframework.security.authentication.DisabledException) {
			message = ErrorCode.LOGIN_DISABLED_USER.name();
		}
		if (exception instanceof org.springframework.security.authentication.LockedException) {
			message = ErrorCode.LOGIN_LOCKED_AUTHENTICATION.name();
		}

		// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setStatus(HttpServletResponse.SC_CONFLICT);

		PrintWriter writer = response.getWriter();
		writer.write(message);
		writer.flush();
	}
}
