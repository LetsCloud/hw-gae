/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * Kapacitás tény adatokat tároló entitás.
 * 
 * @author CR
 *
 */
@Entity
public class CubeActlCap extends CubeBdgtCap {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeActlCap.class.getName());

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeActlCap() {
	}

	/**
	 * Kontruktor FRO tény adatokból.
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeActlCap(Hotel hotel, HwFroCubDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubeCapAct(HwFroFocDto dto)->" + dto);
		setRoomsTotal(dto.getOSZSZO());
		setBedsTotal(dto.getOSZAGY());
		setRoomsOoo(dto.getOOOSZO());
		setBedsOoo(dto.getOOOAGY());
	}

	/**
	 * Konstruktor FRO előrejelzés adatokból.
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeActlCap(Hotel hotel, HwFroFocDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubeCapAct(HwFroFocDto dto)->" + dto);
		setRoomsTotal(dto.getOSZSZO());
		setBedsTotal(dto.getOSZAGY());
		setRoomsOoo(dto.getOOOSZO());
		setBedsOoo(dto.getOOOAGY());
	}
}
