/**
 * 
 */
package hu.hw.cloud.shared.dto.ant;

import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class MeasureConfig implements Dto {

	private Integer index;

	private String dataSource;

	private String measCode1;

	private String measCode2;

	private String title;

	public MeasureConfig() {
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getMeasCode1() {
		return measCode1;
	}

	public void setMeasCode1(String measCode1) {
		this.measCode1 = measCode1;
	}

	public String getMeasCode2() {
		return measCode2;
	}

	public void setMeasCode2(String measCode2) {
		this.measCode2 = measCode2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
