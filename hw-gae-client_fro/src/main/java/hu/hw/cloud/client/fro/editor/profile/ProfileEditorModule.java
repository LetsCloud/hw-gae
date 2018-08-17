/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.profile.customer.CustomerEditorModule;

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

		install(new CustomerEditorModule());
	}
}
