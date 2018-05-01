/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import hu.hw.cloud.client.fro.filter.room.RoomFilterPresenter;
import hu.hw.cloud.client.fro.filter.roomtype.RoomTypeFilterPresenter;
import hu.hw.cloud.client.fro.table.filter.FilterWidgetPresenter;

/**
 * @author robi
 *
 */
public interface FilterPresenterFactory {

	FilterWidgetPresenter createFilterWidgetPresenter();

	RoomTypeFilterPresenter createRoomTypeFilterPresenter();

	RoomFilterPresenter createRoomFilterPresenter();

}
