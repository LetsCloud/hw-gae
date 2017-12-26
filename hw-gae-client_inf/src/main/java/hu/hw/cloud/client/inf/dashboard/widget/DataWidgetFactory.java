/**
 * 
 */
package hu.hw.cloud.client.inf.dashboard.widget;

import hu.hw.cloud.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public interface DataWidgetFactory {
	DataWidgetPresenter create(DataWidgetPresenter.DataWidgetView view, DataWidgetConfigDto configDto);
}
