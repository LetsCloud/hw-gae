/**
 * 
 */
package hu.hw.cloud.client.inf.dashboard;

import java.util.Map;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.cube.query.CubeQueryParamDto;

/**
 * @author CR
 *
 */
public interface DashboardUiHandlers extends UiHandlers {

	void loadWidgetValues(Map<String, CubeQueryParamDto> queryParams);
}
