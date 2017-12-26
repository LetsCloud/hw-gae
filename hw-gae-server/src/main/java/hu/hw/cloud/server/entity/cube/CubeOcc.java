/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import java.util.List;

import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;

/**
 * @author CR
 *
 */
public class CubeOcc extends CubeBase {

	/**
	 * A szálloda fizikailag létező szobáinak száma.
	 */
	private Integer roomsTotal;

	/**
	 * A szálloda fizikailag létező ágyainak száma.
	 */
	private Integer bedsTotal;

	/**
	 * Üzemen kívül helyezett szobák száma
	 */
	private Integer roomsOoo;

	/**
	 * Üzemen kívül helyezett ágyak száma
	 */
	private Integer bedsOoo;

	/**
	 * Kiadható szobák száma.
	 */
	private Integer roomsAvail;

	/**
	 * Kiadható ágyak száma.
	 */
	private Integer bedsAvail;

	/**
	 * Szobaéjszaka (M).
	 */
	private Integer roomNts;

	/**
	 * Vendégéjszaka (M).
	 */
	private Integer guestNts;

	/**
	 * Szobafoglaltság (M).
	 */
	private Float roomOccFull;

	/**
	 * Szobafoglaltság (M).
	 */
	@Ignore
	private Float roomOccOoo;

	/**
	 * Ágyfoglaltság (M).
	 */
	private Float bedOccFull;

	/**
	 * Ágyafoglaltság (M).
	 */
	private Float bedOccOoo;

	public CubeOcc() {
		roomsTotal = 0;
		bedsTotal = 0;
		roomsOoo = 0;
		bedsOoo = 0;
		roomNts = 0;
		guestNts = 0;
	}

	public Integer getRoomsTotal() {
		return roomsTotal;
	}

	public void setRoomsTotal(Integer roomsTotal) {
		this.roomsTotal = roomsTotal;
	}

	public void addRoomsTotal(Integer roomsTotal) {
		this.roomsTotal += roomsTotal;
	}

	public Integer getBedsTotal() {
		return bedsTotal;
	}

	public void setBedsTotal(Integer bedsTotal) {
		this.bedsTotal = bedsTotal;
	}

	public void addBedsTotal(Integer bedsTotal) {
		this.bedsTotal += bedsTotal;
	}

	public Integer getRoomsOoo() {
		return roomsOoo;
	}

	public void setRoomsOoo(Integer roomsOoo) {
		this.roomsOoo = roomsOoo;
	}

	public void addRoomsOoo(Integer roomsOoo) {
		this.roomsOoo += roomsOoo;
	}

	public Integer getBedsOoo() {
		return bedsOoo;
	}

	public void setBedsOoo(Integer bedsOoo) {
		this.bedsOoo = bedsOoo;
	}

	public void addBedsOoo(Integer bedsOoo) {
		this.bedsOoo += bedsOoo;
	}

	public Integer getRoomsAvail() {
		roomsAvail = roomsTotal - roomsOoo;
		return roomsAvail;
	}

	public void setRoomsAvail(Integer roomsAvail) {
		this.roomsAvail = roomsAvail;
	}

	public Integer getBedsAvail() {
		bedsAvail = bedsTotal - bedsOoo;
		return bedsAvail;
	}

	public void setBedsAvail(Integer bedsAvail) {
		this.bedsAvail = bedsAvail;
	}

	public Integer getRoomNts() {
		return roomNts;
	}

	public void setRoomNts(Integer roomNts) {
		this.roomNts = roomNts;
	}

	public void addRoomNts(Integer roomNts) {
		this.roomNts += roomNts;
	}

	public Integer getGuestNts() {
		return guestNts;
	}

	public void setGuestNts(Integer guestNts) {
		this.guestNts = guestNts;
	}

	public void addGuestNts(Integer guestNts) {
		this.guestNts += guestNts;
	}

	public Float getRoomOccFull() {
		if ((roomsTotal == null) || (roomsTotal == 0) || (roomNts == null))
			return 0f;
		return (float) (100 * roomNts / roomsTotal);
	}

	public void setRoomOccFull(Float roomOccFull) {
		this.roomOccFull = roomOccFull;
	}

	public Float getRoomOccOoo() {
		return roomOccOoo;
	}

	public void setRoomOccOoo(Float roomOccOoo) {
		this.roomOccOoo = roomOccOoo;
	}

	public Float getBedOccFull() {
		return bedOccFull;
	}

	public void setBedOccFull(Float bedOccFull) {
		this.bedOccFull = bedOccFull;
	}

	public Float getBedOccOoo() {
		return bedOccOoo;
	}

	public void setBedOccOoo(Float bedOccOoo) {
		this.bedOccOoo = bedOccOoo;
	}

	@Override
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		switch (measure1) {
		case ROOM_OCC_FULL:
			if (this.getRoomOccFull() != null)
				return this.getRoomOccFull().doubleValue();
		case ROOM_OCC_OOO:
			if (this.getRoomOccOoo() != null)
				return this.getRoomOccOoo().doubleValue();
		case BED_OCC_FULL:
			if (this.getBedOccFull() != null)
				return this.getBedOccFull().doubleValue();
		case BED_OCC_OOO:
			if (this.getBedOccOoo() != null)
				return this.getBedOccOoo().doubleValue();

		default:
			return super.getValueOfMeasure(measure1, measure2);
		}
	}

}
