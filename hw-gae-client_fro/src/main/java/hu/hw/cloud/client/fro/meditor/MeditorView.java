/**
 * 
 */
package hu.hw.cloud.client.fro.meditor;

import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface MeditorView<T extends BaseDto> extends View {

	void open(T dto);

	void close();

}
