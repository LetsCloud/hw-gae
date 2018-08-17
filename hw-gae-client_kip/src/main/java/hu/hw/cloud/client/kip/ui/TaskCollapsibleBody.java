/**
 * 
 */
package hu.hw.cloud.client.kip.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.client.base.MaterialWidget;

import hu.hw.cloud.client.kip.resources.KipGssResources;

/**
 * @author robi
 *
 */
public class TaskCollapsibleBody extends MaterialWidget {

	public TaskCollapsibleBody(KipGssResources kipGss) {
		super(Document.get().createDivElement(), kipGss.taskStyle().task_collapsible_body());
	}

}
