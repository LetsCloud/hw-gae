/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ACCOUNT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Locale;

import javax.annotation.security.PermitAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.security.OnRegistrationCompleteEvent;
import hu.hw.cloud.server.service.AccountService;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends BaseController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(AccountController.class);
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class.getName());

	private final AccountService accountService;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	AccountController(AccountService accountService, ApplicationEventPublisher eventPublisher) {
		this.accountService = accountService;
		this.eventPublisher = eventPublisher;
	}

	@RequestMapping(value = ACCOUNT, method = POST)
	@PermitAll
	ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto, WebRequest request) {
		if (registerDto.getAccountName() == null) {
			logger.info("register->accountName=null");
		} else {
			logger.info("register->accountName=" + registerDto.getAccountName());
		}

		AppUser appuser;
		try {
			appuser = accountService.register(registerDto);
			logger.info("register->appuser=" + appuser);
			registerDto.setAccountId(appuser.getAccount().getId());
			try {
				String appUrl = request.getContextPath();
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appuser, request.getLocale(), appUrl));
				Locale locale = request.getLocale();
				logger.info("register->locale=" + locale);
				return new ResponseEntity<RegisterDto>(registerDto, OK);
			} catch (Exception e) {
				logger.info(e.toString());
				e.printStackTrace();
				return new ResponseEntity<RegisterDto>(NOT_ACCEPTABLE);
			}
		} catch (EntityValidationException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RegisterDto>(CONFLICT);
		} catch (UniqueIndexConflictException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RegisterDto>(FORBIDDEN);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RegisterDto>(NOT_FOUND);
		}
	}

}
