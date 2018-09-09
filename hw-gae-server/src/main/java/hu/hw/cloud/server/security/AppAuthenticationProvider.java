/**
 * 
 */
package hu.hw.cloud.server.security;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
public class AppAuthenticationProvider implements AuthenticationProvider {
	private static final Logger LOGGER = Logger.getLogger(AppAuthenticationProvider.class.getName());

	private final AppUserService userService;
	private final LoginAttemptService loginAttemptService;

	private final ModelMapper modelMapper;

	AppAuthenticationProvider(AppUserService userService, LoginAttemptService loginAttemptService,
			ModelMapper modelMapper) {
		LOGGER.info("AppUserDetailsService()");
		this.userService = userService;
		this.loginAttemptService = loginAttemptService;
		this.modelMapper = modelMapper;
		// this.request = request;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = "";
		if (authentication.getCredentials() != null)
			password = authentication.getCredentials().toString();

		String[] split = name.split(":");
		if (split.length < 2) {
			throw new InsufficientAuthenticationException(ExceptionType.LOGIN_INSUFFICIENT_AUTHENTICATION.name());
		}
		String username = split[0];
		String corporateId = split[1];

		LOGGER.info("username: " + username);
		LOGGER.info("corporateId: " + corporateId);

		AppUser appUser = userService.getUserByUsername(username, new Long(corporateId));

		if (appUser == null) {
			throw new UsernameNotFoundException(ExceptionType.LOGIN_USERNAME_NOT_FOUND.name());
		}

		if (!appUser.getPassword().equals(password)) {
			throw new BadCredentialsException(ExceptionType.LOGIN_BAD_CREDENTIALS.name());
		}

		if (!appUser.getEnabled()) {
			throw new DisabledException(ExceptionType.LOGIN_DISABLED_USER.name());
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		List<String> permissions = userService.getPermissions(appUser.getUsername());
		for (String permission : permissions)
			grantedAuthorities.add(new SimpleGrantedAuthority(permission));
		AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
		AppUserDetails aud = new AppUserDetails(name, appUserDto, grantedAuthorities, true);

		// return authentication;

		return new UsernamePasswordAuthenticationToken(aud, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}