/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
@Entity
public class CubeBdgtCap extends CubeBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBdgtCap.class.getName());

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
	@Ignore
	private Integer roomsAvailable;

	/**
	 * Kiadható ágyak száma.
	 */
	@Ignore
	private Integer bedsAvailable;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeBdgtCap() {
	}

	/**
	 * Kontruktor FRO tény adatokból.
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeBdgtCap(Hotel hotel, HwFroCubDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubeCapAct(HwFroFocDto dto)->" + dto);
		this.roomsTotal = dto.getOSZSZO();
		this.bedsTotal = dto.getOSZAGY();
		this.roomsOoo = dto.getOOOSZO();
		this.bedsOoo = dto.getOOOAGY();
	}

	/**
	 * Konstruktor FRO előrejelzés adatokból.
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeBdgtCap(Hotel hotel, HwFroFocDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubeCapAct(HwFroFocDto dto)->" + dto);
		this.roomsTotal = dto.getOSZSZO();
		this.bedsTotal = dto.getOSZAGY();
		this.roomsOoo = dto.getOOOSZO();
		this.bedsOoo = dto.getOOOAGY();
	}

	public Integer getRoomsTotal() {
		return roomsTotal;
	}

	public void setRoomsTotal(Integer roomsTotal) {
		this.roomsTotal = roomsTotal;
	}

	public Integer getBedsTotal() {
		return bedsTotal;
	}

	public void setBedsTotal(Integer bedsTotal) {
		this.bedsTotal = bedsTotal;
	}

	public Integer getRoomsOoo() {
		return roomsOoo;
	}

	public void setRoomsOoo(Integer roomsOoo) {
		this.roomsOoo = roomsOoo;
	}

	public Integer getBedsOoo() {
		return bedsOoo;
	}

	public void setBedsOoo(Integer bedsOoo) {
		this.bedsOoo = bedsOoo;
	}

	public Integer getRoomsAvailable() {
		return roomsTotal - roomsOoo;
	}

	public void setRoomsAvailable(Integer roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public Integer getBedsAvailable() {
		return bedsTotal - bedsOoo;
	}

	public void setBedsAvailable(Integer bedsAvailable) {
		this.bedsAvailable = bedsAvailable;
	}

	/**
	 * Visszaadja az entitás measure paraméterrel jelölt mezőjének értékét.
	 * 
	 * @param measNamePart1
	 * @param measNamePart2
	 * @return
	 */
	@Override
	protected Double getValueOfMeasure(Measure measNamePart1, Measure measNamePart2) {
		switch (measNamePart1) {
		case ROOMS_TOTAL:
			if (this.getRoomsTotal() != null)
				return this.getRoomsTotal().doubleValue();
		case BEDS_TOTAL:
			if (this.getBedsTotal() != null)
				return this.getBedsTotal().doubleValue();
		case ROOMS_OOO:
			if (this.getRoomsOoo() != null)
				return this.getRoomsOoo().doubleValue();
		case BEDS_OOO:
			if (this.getBedsOoo() != null)
				return this.getBedsOoo().doubleValue();
		case ROOMS_AVAIL:
			if (this.getRoomsAvailable() != null)
				return this.getRoomsAvailable().doubleValue();
		case BEDS_AVAIL:
			if (this.getBedsAvailable() != null)
				return this.getBedsAvailable().doubleValue();
		default:
			return super.getValueOfMeasure(measNamePart1, measNamePart2);
		}
	}

	@Override
	public CubeOcc addToOccData(CubeOcc joined) {
		joined.addRoomsTotal(getRoomsTotal());
		joined.addBedsTotal(getBedsTotal());
		joined.addRoomsOoo(getRoomsOoo());
		joined.addBedsOoo(getBedsOoo());
		return joined;
	}
}
