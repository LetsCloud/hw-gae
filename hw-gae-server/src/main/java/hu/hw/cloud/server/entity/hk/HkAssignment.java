/**
 * 
 */
package hu.hw.cloud.server.entity.hk;

import java.util.Comparator;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.shared.cnst.AssignmentStatus;
import hu.hw.cloud.shared.dto.hk.HkAssignmentDto;

/**
 * @author CR
 *
 */
@Entity
public class HkAssignment extends HotelChild {

	@Index
	private Date businessDate;

	@Index
	private AssignmentStatus status;

	private Ref<Room> roomRef;

	private Ref<AppUser> attendantRef;

	private Ref<AppUser> inspectorRef;

	private CleanType cleanType;

	private String notice;

	public HkAssignment() {
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public AssignmentStatus getStatus() {
		return status;
	}

	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}

	public Room getRoom() {
		return roomRef.get();
	}

	public void setRoom(Room room) {
		this.roomRef = Ref.create(room);
	}

	public AppUser getAttendant() {
		return attendantRef.get();
	}

	public void setAttendant(AppUser attendant) {
		this.attendantRef = Ref.create(attendant);
	}

	public AppUser getInspector() {
		return inspectorRef.get();
	}

	public void setInspector(AppUser inspector) {
		this.inspectorRef = Ref.create(inspector);
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public CleanType getCleanType() {
		return cleanType;
	}

	public void setCleanType(CleanType cleanType) {
		this.cleanType = cleanType;
	}

	/**
	 * Az átadott entitásból létrehozza a megfelelő DTO-t.
	 * 
	 * @param entity
	 * @return
	 */
	public static HkAssignmentDto createDto(HkAssignment entity) {
		HkAssignmentDto dto = new HkAssignmentDto();
		return dto;
	}

	/**
	 * Szobaszám szerint rendezi az entitás listát
	 */
	public static Comparator<HkAssignment> ORDER_BY_ROOM = new Comparator<HkAssignment>() {
		public int compare(HkAssignment one, HkAssignment other) {
			return one.getRoom().getCode().compareTo(other.getRoom().getCode());
		}
	};

}
