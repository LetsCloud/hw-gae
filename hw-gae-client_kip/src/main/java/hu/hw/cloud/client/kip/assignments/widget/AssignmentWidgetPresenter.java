/**
 * 
 */
package hu.hw.cloud.client.kip.assignments.widget;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.client.kip.assignments.AssignmentEditEvent;
import hu.hw.cloud.shared.dto.hk.HkAssignmentDto;

/**
 * @author CR
 *
 */
public class AssignmentWidgetPresenter extends PresenterWidget<AssignmentWidgetPresenter.MyView>
		implements AssignmentWidgetUiHandlers {

	interface MyView extends View, HasUiHandlers<AssignmentWidgetUiHandlers> {
		void setData(HkAssignmentDto data);
	}

	@Inject
	AssignmentWidgetPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);

		getView().setUiHandlers(this);
	}

	@Override
	public void editAssignment() {
		AssignmentEditEvent.fire(this);
	}
	
	public void setData(HkAssignmentDto data) {
		getView().setData(data);
	}
}