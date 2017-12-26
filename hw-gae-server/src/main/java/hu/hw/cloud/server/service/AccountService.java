/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public interface AccountService {

	AppUser register(RegisterDto registerDto) throws EntityValidationException, UniqueIndexConflictException;
	
	Boolean sameAccountIds(String id, Long generatedId);
}