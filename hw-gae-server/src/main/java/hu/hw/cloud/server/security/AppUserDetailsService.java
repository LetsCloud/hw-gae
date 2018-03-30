package hu.hw.cloud.server.security;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.service.AppUserService;

public class AppUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = Logger.getLogger(AppUserDetailsService.class.getName());

	private final AppUserService userService;

	private LoginAttemptService loginAttemptService;

	AppUserDetailsService(AppUserService userService, LoginAttemptService loginAttemptService) {
		LOGGER.info("AppUserDetailsService()");
		this.userService = userService;
		this.loginAttemptService = loginAttemptService;
	}

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		LOGGER.info("loadUserByUsername->input: " + input);
		String ip = getRequestRemoteAddr();
		LOGGER.info("loadUserByUsername->ip: " + ip);
		boolean accountNonLocked = !loginAttemptService.isBlocked(ip);

		String[] split = input.split(":");
		if (split.length < 2) {
			throw new UsernameNotFoundException("Must specify both username and corporate domain identifier");
		}
		String username = split[0];
		String corporateId = split[1];

		LOGGER.info("username: " + username);
		LOGGER.info("corporateId: " + corporateId);

		AppUser appUser = userService.getUserByUsername(username, new Long(corporateId));
		if (appUser == null)
			throw new UsernameNotFoundException("User not found");

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		/*
		 * List<String> permissions = userService.getPermissions(appUser.getUsername());
		 * 
		 * for (String permission : permissions) { grantedAuthorities.add(new
		 * SimpleGrantedAuthority(permission)); }
		 */
		AppUserDetails aud = new AppUserDetails(input, AppUser.createDto(appUser), grantedAuthorities,
				accountNonLocked);
		
		LOGGER.info("loadUserByUsername()->out");

		return aud;
	}

	/**
	 * 
	 * @return private String getClientIP() { String xfHeader =
	 *         request.getHeader("X-Forwarded-For"); if (xfHeader == null) { return
	 *         request.getRemoteAddr(); } return xfHeader.split(",")[0]; }
	 */

	public static String getRequestRemoteAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return request.getRemoteAddr();
	}
}
