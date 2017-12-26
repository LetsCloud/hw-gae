/**
 * 
 */
package hu.hw.cloud.client.inf.dashboard.widget;

import java.util.List;

import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public interface DataWidget {

	DataWidgetConfigDto getConfig();

	DataWidgetPresenter.DataWidgetView getView();

	void setValues(List<DataWidgetValueM1Dto> values);
}
