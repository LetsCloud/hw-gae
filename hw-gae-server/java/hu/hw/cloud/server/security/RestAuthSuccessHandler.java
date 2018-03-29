package hu.hw.cloud.server.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.hw.cloud.shared.dto.common.AppUserDto;

public class RestAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger LOGGER = Logger.getLogger(RestAuthSuccessHandler.class.getName());

	private final ObjectMapper mapper;

	private final LoginAttemptService loginAttemptService;

	RestAuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter,
			LoginAttemptService loginAttemptService) {
		this.mapper = messageConverter.getObjectMapper();
		this.loginAttemptService = loginAttemptService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LOGGER.info("onAuthenticationSuccess()");

		loginAttemptService.loginSucceeded(request.getRemoteAddr());

		response.setStatus(HttpServletResponse.SC_OK);

		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

		LOGGER.info(userDetails.getUsername() + " got is connected ");

		AppUserDto appUserDto = userDetails.getAppUserDto();

		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, appUserDto);
		writer.flush();
	}
}
