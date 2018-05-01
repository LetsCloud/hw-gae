/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import hu.hw.cloud.client.fro.table.AbstractTablePresenter;

/**
 * @author robi
 *
 */
public class TableStore {
	
	private String caption;
	
	private AbstractTablePresenter<?,?> table;
	
	public TableStore(String caption, AbstractTablePresenter<?,?> table) {
		this.caption = caption;
		this.table = table;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public AbstractTablePresenter<?,?> getTable() {
		return table;
	}

	public void setTable(AbstractTablePresenter<?,?> table) {
		this.table = table;
	}

}
