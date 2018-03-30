/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
public abstract class CubeBasePfm extends CubeBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBasePfm.class.getName());

	/**
	 * Piaci szegmens (D).
	 */
	private String market;

	/**
	 * Értékesítési csatorna (D).
	 */
	private String channel;

	/**
	 * Forrás (D).
	 */
	private String source;

	/**
	 * Foglalás típusa (D).
	 */
	private String type;

	/**
	 * Országkód (D).
	 */
	private String country;

	/**
	 * Vevőkód (D).
	 */
	private String customer;

	/**
	 * Árkod (D).
	 */
	private String rateCode;

	/**
	 * Szobaéjszaka (M).
	 */
	private Integer roomNts=0;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeBasePfm() {
		// LOGGER.info("CubePrfmBase()");
	}

	public CubeBasePfm(Hotel hotel, HwFroCubDto dto) {
		super(hotel, dto);

		if (dto.getSTAKOD() != null)
			this.setMarket(dto.getSTAKOD());

		if (dto.getERTKOD() != null)
			this.setChannel(dto.getERTKOD());

		if (dto.getSOUKOD() != null)
			this.setSource(dto.getSOUKOD());

		if (dto.getRESTIP() != null)
			this.setType(dto.getRESTIP());

		if (dto.getNEMZET() != null)
			this.setCountry(dto.getNEMZET());

		if (dto.getPARKOD() != null)
			this.setCustomer(dto.getPARKOD());

		if (dto.getPACKOD() != null)
			this.setRateCode(dto.getPACKOD());
	}

	public CubeBasePfm(Hotel hotel, HwFroFocDto dto) {
		super(hotel, dto);

		if (dto.getSTAKOD() != null)
			this.setMarket(dto.getSTAKOD());

		if (dto.getERTKOD() != null)
			this.setChannel(dto.getERTKOD());

		if (dto.getSOUKOD() != null)
			this.setSource(dto.getSOUKOD());

		if (dto.getRESTIP() != null)
			this.setType(dto.getRESTIP());

		if (dto.getNEMZET() != null)
			this.setCountry(dto.getNEMZET());

		if (dto.getPARKOD() != null)
			this.setCustomer(dto.getPARKOD());

		if (dto.getPACKOD() != null)
			this.setRateCode(dto.getPACKOD());
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRate() {
		return rateCode;
	}

	public void setRate(String rate) {
		this.rateCode = rate;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public Integer getRoomNts() {
		return roomNts;
	}

	public void setRoomNts(Integer roomNts) {
		this.roomNts = roomNts;
	}

	/**
	 * Visszaadja a dimenzió mező értékét
	 * 
	 * @param dim
	 * @return
	 */
	@Override
	public String getDimValue(Dimension dimName) {
		switch (dimName) {
		case MARKET:
			return this.market;
		case CHANNEL:
			return this.channel;
		case SOURCE:
			return this.source;
		case TYPE:
			return this.type;
		case COUNTRY:
			return this.country;
		case CUSTOMER:
			return this.customer;
		case RATE_CODE:
			return this.rateCode;
		default:
			return super.getDimValue(dimName);
		}
	}

	/**
	 * Visszaadja az entitás measure paraméterrel jelölt mezőjének értékét.
	 * 
	 * @param measNamePart1
	 * @param measNamePart2
	 * @return
	 */
	@Override
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		switch (measure1) {
		case ROOM_NTS:
			if (this.getRoomNts() != null)
				return this.getRoomNts().doubleValue();
		default:
			return super.getValueOfMeasure(measure1, measure2);
		}
	}

	@Override
	public CubeOcc addToOccData(CubeOcc joined) {
		joined.addRoomNts(getRoomNts());
		return joined;
	}
}
