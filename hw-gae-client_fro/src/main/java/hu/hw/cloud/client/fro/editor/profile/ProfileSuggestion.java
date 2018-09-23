/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile;

import com.google.gwt.user.client.ui.SuggestOracle;

import hu.hw.cloud.shared.dto.profile.ProfileDtor;

/**
 * @author robi
 *
 */
public class ProfileSuggestion implements SuggestOracle.Suggestion {

	private ProfileDtor profile;

	public ProfileSuggestion(ProfileDtor profile) {
		this.profile = profile;
	}

	@Override
	public String getDisplayString() {
		return getReplacementString();
	}

	@Override
	public String getReplacementString() {
		return profile.getName();
	}

	public ProfileDtor getProfile() {
		return profile;
	}

	@Override
	public String toString() {
		return profile.toString();
	}
}