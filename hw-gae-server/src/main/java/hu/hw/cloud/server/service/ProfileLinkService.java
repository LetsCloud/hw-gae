/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.List;

import hu.hw.cloud.server.entity.profile.Profile;
import hu.hw.cloud.server.entity.profile.ProfileLink;

/**
 * @author robi
 *
 */
public interface ProfileLinkService extends CrudService<ProfileLink> {
	
	List<ProfileLink> getByProfile(Profile profile, String field);

}
