/**
 * 
 */
package hu.hw.cloud.client.inf.dashboard.widget.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import hu.hw.cloud.client.inf.dashboard.widget.DataWidgetPresenter;
import hu.hw.cloud.shared.cnst.cube.DataWidgetFieldType;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.dw.DataWidgetConfigDto;
import hu.hw.cloud.shared.dto.cube.dw.DataWidgetFieldDto;

/**
 * @author CR
 *
 */
public class ValueDeltaView extends ValueOnlyView implements DataWidgetPresenter.DataWidgetView {
	private static Logger logger = Logger.getLogger(ValueDeltaView.class.getName());

	private DeltaField changeField;

	private Double baseValue;

	@Inject
	ValueDeltaView() {
		super();
		logger.log(Level.INFO, "ValueDeltaView()");
		changeField = new DeltaField();
		getValuePanel().addWidget(changeField);
	}

	@Override
	public void setConfig(DataWidgetConfigDto config) {
		super.setConfig(config);

		for (DataWidgetFieldDto field : config.getFields()) {
			if (field.getFieldType().equals(DataWidgetFieldType.DELTA)) {
				return;
			}
		}
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		super.setValues(values);

		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.DELTA)) {
				baseValue = value.getValue();
				if (baseValue > 0) {
					changeField.setDelta(100 * (factValue - baseValue) / baseValue);

				} else {
					changeField.setDelta((double) 100);
				}
				return;
			}
		}
	}

}
