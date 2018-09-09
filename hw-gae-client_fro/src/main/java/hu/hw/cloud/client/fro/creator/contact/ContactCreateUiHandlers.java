/**
 * 
 */
package hu.hw.cloud.client.fro.creator.contact;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public interface ContactCreateUiHandlers extends UiHandlers {

	void save(ContactDto dto);
	
	void cancel();

}
