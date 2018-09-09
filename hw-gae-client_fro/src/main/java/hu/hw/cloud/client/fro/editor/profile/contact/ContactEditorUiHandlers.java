/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.contact;

import hu.hw.cloud.client.fro.editor.AbstractEditorUiHandlers;
import hu.hw.cloud.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public interface ContactEditorUiHandlers extends AbstractEditorUiHandlers<ContactDto> {
	
	void edit(String name);
	
	void cancel(String name);

}
