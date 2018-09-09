/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.profile.contact.ContactEditorModule;
import hu.hw.cloud.client.fro.editor.profile.organization.OrganizationEditorModule;

/**
 * @author robi
 *
 */
public class ProfileEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(CommunicationEditor.class);		
		bind(CommunicationListEditor.class);

		bind(AddressEditor.class);		
		bind(AddressListEditor.class);

		install(new OrganizationEditorModule());
		install(new ContactEditorModule());
	}
}
