/**
 * 
 */
package hu.hw.cloud.shared.dto.hk;

import java.util.HashMap;
import java.util.Map;

import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AssignmentSummaryDto implements Dto {

	private AppUserDto attendantDto;

	private Map<RoomStatus, Integer> roomSummary;

	public AssignmentSummaryDto() {
	}

	public AssignmentSummaryDto(AppUserDto attendantDto, Map<RoomStatus, Integer> roomSummary) {
		this();
		this.attendantDto = attendantDto;
		this.roomSummary = roomSummary;
	}

	public AssignmentSummaryDto(AppUserDto attendantDto, RoomStatus roomStatus, Integer count) {
		this(attendantDto, null);

		Map<RoomStatus, Integer> rs = new HashMap<RoomStatus, Integer>();
		rs.put(roomStatus, count);
		setRoomSummary(rs);
	}

	public AppUserDto getAttendantDto() {
		return attendantDto;
	}

	public void setAttendantDto(AppUserDto attendant) {
		this.attendantDto = attendant;
	}

	public Map<RoomStatus, Integer> getRoomSummary() {
		return roomSummary;
	}

	public void setRoomSummary(Map<RoomStatus, Integer> roomSummary) {
		this.roomSummary = roomSummary;
	}

	public void addRoom(RoomStatus roomStatus) {
		Map<RoomStatus, Integer> roomSummary = getRoomSummary();
		Integer count = roomSummary.get(roomStatus);
		if (count == null) {
			roomSummary.put(roomStatus, 1);
		} else {
			roomSummary.remove(roomStatus);
			roomSummary.put(roomStatus, count++);
		}

	}
}
