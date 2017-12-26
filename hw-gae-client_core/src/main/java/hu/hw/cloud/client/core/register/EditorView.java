/**
 * 
 */
package hu.hw.cloud.client.core.register;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.gwtplatform.mvp.client.View;

/**
 * @author CR
 *
 */
public interface EditorView<T> extends View, Editor<T> {
	SimpleBeanEditorDriver<T, ?> createEditorDriver();
}
