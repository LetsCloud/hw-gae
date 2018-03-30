/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import hu.hw.cloud.shared.cnst.cube.DataWidgetFieldType;
import hu.hw.cloud.shared.cnst.cube.Measure;

/**
 * @author CR
 *
 */
public class MeasureParam {

	private Integer widgetIndex;

	private DataWidgetFieldType fieldIndex;

	private Measure measure1;

	private Measure measure2;

	public MeasureParam() {
	}

	public MeasureParam(Integer widgetIndex, DataWidgetFieldType fieldIndex, Measure measure1, Measure measure2) {
		this();
		this.widgetIndex = widgetIndex;
		this.fieldIndex = fieldIndex;
		this.measure1 = measure1;
		this.measure2 = measure2;
	}

	public Integer getWidgetIndex() {
		return widgetIndex;
	}

	public void setWidgetIndex(Integer widgetIndex) {
		this.widgetIndex = widgetIndex;
	}

	public DataWidgetFieldType getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(DataWidgetFieldType fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public Measure getMeasure1() {
		return measure1;
	}

	public void setMeasure1(Measure measure1) {
		this.measure1 = measure1;
	}

	public Measure getMeasure2() {
		return measure2;
	}

	public void setMeasure2(Measure measure2) {
		this.measure2 = measure2;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " MeasureParam {" + NEW_LINE);
		result.append(" widgetIndex: " + widgetIndex + NEW_LINE);
		result.append(" fieldIndex: " + fieldIndex + NEW_LINE);
		result.append(" measure1: " + measure1 + NEW_LINE);
		result.append(" measure2: " + measure2 + NEW_LINE);
		result.append("}");

		return result.toString();
	}
}
