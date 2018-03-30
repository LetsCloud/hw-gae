/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.entity.VerificationToken;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.repository.AppUserRepository;

/**
 * @author CR
 *
 */
@Controller
public class RegisterController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private AppUserRepository appUserRepository;

	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		LOGGER.info("confirmRegistration->token=" + token);
		VerificationToken verificationToken = null;

		Locale locale = request.getLocale();

		AppUser appUser = appUserRepository.findByToken(token);
		if (appUser == null)
			LOGGER.info("confirmRegistration->(appUser==null)");

		List<VerificationToken> tokens = appUser.getVerificationTokens();
		if (tokens == null)
			LOGGER.info("confirmRegistration->(tokens==null)");

		for (VerificationToken t : tokens) {
			if (t.getToken().equals(token))
				verificationToken = t;
		}

		if (verificationToken == null) {
			LOGGER.info("confirmRegistration->(verificationToken==null)");
			// String message = messages.getMessage("auth.message.invalidToken",
			// null, locale);
			// model.addAttribute("message", message);
			return "redirect:/index.html?lang=" + locale.getLanguage();
		}

		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			LOGGER.info("confirmRegistration->(verificationToken==expirated)");
			// String messageValue = messages.getMessage("auth.message.expired",
			// null, locale)
			// model.addAttribute("message", messageValue);
			return "redirect:/badUser.html?lang=" + locale.getLanguage();
		}

		appUser.setEnabled(true);
		try {
			appUserRepository.save(appUser);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.info("redirect:/index.html#register");
		return "redirect:/index.html#register";
	}
}
