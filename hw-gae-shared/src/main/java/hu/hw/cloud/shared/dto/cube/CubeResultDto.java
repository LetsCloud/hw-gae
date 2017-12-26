/**
 * 
 */
package hu.hw.cloud.shared.dto.cube;

import java.util.List;

import hu.hw.cloud.shared.cnst.cube.DataWidgetFieldType;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
public interface CubeResultDto extends Dto {

	void setDimensionValues(List<String> dimensionValues);

	void setWidgetIndex(Integer widgetIndex);

	void setFieldType(DataWidgetFieldType fieldType);

	void addValue(Integer widgetIndex, DataWidgetFieldType fieldType, Double value);

}
