/**
 * 
 */
package hu.hw.cloud.shared.dto.ant;

import java.util.List;

import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AntConfigDto implements Dto {

	private List<DimensionConfig> dimensions;

	private List<MeasureConfig> measures;

	public AntConfigDto() {
	}

	public List<DimensionConfig> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<DimensionConfig> dimensions) {
		this.dimensions = dimensions;
	}

	public List<MeasureConfig> getMeasures() {
		return measures;
	}

	public void setMeasures(List<MeasureConfig> measures) {
		this.measures = measures;
	}

}
