/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import hu.hw.cloud.shared.dto.profile.CommunicationDto;

/**
 * @author robi
 *
 */
public class CommunicationListEditor extends Composite
		implements IsEditor<ListEditor<CommunicationDto, CommunicationEditor>> {
	private static Logger logger = Logger.getLogger(CommunicationListEditor.class.getName());

	interface Binder extends UiBinder<Widget, CommunicationListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<CommunicationEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class CommunicationEditorSource extends EditorSource<CommunicationEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public CommunicationEditor create(final int index) {
			CommunicationEditor subEditor = editorProvider.get();
			if (index % 2 == 0)
				subEditor.setBackgeoundColor();
			subEditor.setIndex(index);
			eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.OPEN, index));
//			subEditor.openDetails(true);

			listPanel.insert(subEditor, index);
			return subEditor;
		}

		/**
		 * Called when an Editor no longer requires a sub-Editor. The default
		 * implementation is a no-op.
		 */
		@Override
		public void dispose(CommunicationEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(CommunicationEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<CommunicationDto, CommunicationEditor> editor = ListEditor.of(new CommunicationEditorSource());

	private final EventBus eventBus;

	/**
	 */
	@Inject
	CommunicationListEditor(Binder uiBinder, EventBus eventBus) {
		logger.info("CommunicationListEditor()");
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));

		eventBus.addHandler(CommunicationActionEvent.TYPE,
				new CommunicationActionEvent.CommunicationActionEventHandler() {

					@Override
					public void onCommunicationAction(CommunicationActionEvent event) {
						if (event.getAction().equals(CommunicationActionEvent.Action.DELETE))
							remove(event.getIndex());
					}

				});
	}

	@Override
	public ListEditor<CommunicationDto, CommunicationEditor> asEditor() {
		return editor;
	}

	public void addItem() {
		CommunicationDto e = new CommunicationDto();
		editor.getList().add(e);
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

}
