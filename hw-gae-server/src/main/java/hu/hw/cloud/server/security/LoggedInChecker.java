package hu.hw.cloud.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;

import hu.hw.cloud.server.entity.common.AppUser;

/**
 * A Spring Security context-be bejelentkezett felhasználó lekérdezését szolgáló
 * osztály
 * 
 * @author CR
 *
 */
public class LoggedInChecker {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInChecker.class);

	/**
	 * Visszaadja a bejelentkezett felhasználó adatait AppUser entitás formájában
	 * 
	 * @return
	 */
	public AppUser getLoggedInUser() {
		LOGGER.info("getLoggedInUser()");

		AppUser appUser = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			// principal can be "anonymousUser" (String)
			if (principal instanceof AppUserDetails) {
				LOGGER.info("principal instanceof AppUserDetails");
				AppUserDetails userDetails = (AppUserDetails) principal;
				appUser = new AppUser(userDetails.getAppUserDto());
			}
		}

		return appUser;
	}
}
