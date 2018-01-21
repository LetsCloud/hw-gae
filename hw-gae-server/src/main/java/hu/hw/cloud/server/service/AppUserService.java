package hu.hw.cloud.server.service;

import java.util.List;

import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;
import hu.hw.cloud.server.entity.common.AppUser;

public interface AppUserService extends CrudService<AppUser, AppUserDto> {

	AppUser getUserByUsername(String username, Long accountGeneratedId);

	List<String> getPermissions(String username);

	AppUser getCurrentUser();

	Boolean isCurrentUserLoggedIn();

	Boolean activate(String token) throws EntityValidationException, UniqueIndexConflictException;

	void createVerificationToken(AppUser user, String token) throws Throwable;
	
	AppUser createAdminUser(RegisterDto registerDto) throws EntityValidationException, UniqueIndexConflictException;
	
	void fcmSubscribe(String iidToken, String userAgent) throws Throwable;
}
