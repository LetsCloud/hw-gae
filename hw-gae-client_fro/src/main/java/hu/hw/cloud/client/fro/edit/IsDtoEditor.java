/**
 * 
 */
package hu.hw.cloud.client.core.ui.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public interface IsDtoEditor<T extends BaseDto> extends UiHandlers {

	void create();

	void edit(T dto);

	void save(T dto);
}
