/**
 * 
 */
package hu.hw.cloud.client.fro.creator.organization;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public interface CustomerCreateUiHandlers extends UiHandlers {

	void save(OrganizationDto dto);
	
	void cancel();

}
