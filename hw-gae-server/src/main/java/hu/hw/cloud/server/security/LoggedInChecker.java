package hu.hw.cloud.server.security;

import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;

import hu.hw.cloud.server.entity.MyMapper;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * A Spring Security context-be bejelentkezett felhasználó lekérdezését szolgáló
 * osztály
 * 
 * @author CR
 *
 */
public class LoggedInChecker {
	private static final Logger logger = LoggerFactory.getLogger(LoggedInChecker.class);

	private final MyMapper modelMapper;

	public LoggedInChecker(MyMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó adatait AppUser entitás formájában
	 * 
	 * @return
	 */
	public AppUser getLoggedInUser() {
		logger.info("getLoggedInUser()");

		AppUser appUser = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			logger.info("getLoggedInUser()->(authentication != null)");
			Object principal = authentication.getPrincipal();

			// principal can be "anonymousUser" (String)
			if (principal instanceof AppUserDetails) {
				AppUserDetails userDetails = (AppUserDetails) principal;
				AppUserDto dto = userDetails.getAppUserDto();
				logger.info("getLoggedInUser()->before mapper->dto=" + dto);
				try {
					appUser = modelMapper.map(dto, AppUser.class);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				logger.info("getLoggedInUser()->after mapper");
			}
		}
		return appUser;
	}
}
