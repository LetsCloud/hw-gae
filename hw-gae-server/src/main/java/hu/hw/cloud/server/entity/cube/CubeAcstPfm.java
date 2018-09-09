/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
public abstract class CubeAcstPfm extends CubeBasePfm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeAcstPfm.class.getName());

	/**
	 * Foglalás azonosító (D).
	 */
	private Long resId;

	/**
	 * Szobafoglalás érkezés (M).
	 */
	private Integer roomArr;

	/**
	 * Szobafoglalás utazás (M).
	 */
	private Integer roomDep;

	/**
	 * Vendég érkezés (M).
	 */
	private CubeGuestAgeDetail guestArr = new CubeGuestAgeDetail();

	/**
	 * Vendégéjszaka (M).
	 */
	private CubeGuestAgeDetail guestNts = new CubeGuestAgeDetail();

	/**
	 * Vendég utazás (M).
	 */
	private CubeGuestAgeDetail guestDep = new CubeGuestAgeDetail();

	/**
	 * Bruttó terhelés (M).
	 */
	private CubeDepDetail grsChg = new CubeDepDetail();

	/**
	 * Nettó terhelés (M).
	 */
	private CubeDepDetail netChg = new CubeDepDetail();

	/**
	 * Bruttó engedmény (M).
	 */
	private CubeDepDetail grsDsc = new CubeDepDetail();

	/**
	 * Nettó engedmény (M).
	 */
	private CubeDepDetail netDsc = new CubeDepDetail();

	/**
	 * Bruttó árbevétel (M).
	 */
	@Ignore
	private CubeDepDetail grsRev = new CubeDepDetail();

	/**
	 * Nettó árbevétel (M).
	 */
	@Ignore
	private CubeDepDetail netRev = new CubeDepDetail();

	/**
	 * Bruttó szoba átlagár
	 */
	@Ignore
	private Double grsAdr;

	/**
	 * Nettó szoba átlagár
	 */
	@Ignore
	private Double netAdr;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeAcstPfm() {
		// LOGGER.info("CubePrfmAcst()");
	}

	public CubeAcstPfm(Hotel hotel, HwFroCubDto dto) {
		super(hotel, dto);

		if (dto.getRESNUM() != null)
			this.setResId(dto.getRESNUM().longValue());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->DIM");

		/**
		 * Szobák
		 */
		if (dto.getERKSZO() != null)
			this.setRoomArr(dto.getERKSZO());

		if (dto.getEJSSZO() != null)
			this.setRoomNts(dto.getEJSSZO());

		if (dto.getUTASZO() != null)
			this.setRoomDep(dto.getUTASZO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->ROOMS");

		/**
		 * Érkező vendégek
		 */
		if (dto.getERKFEL() != null)
			this.getGuestArr().setAdult(dto.getERKFEL());

		if (dto.getERKTIN() != null)
			this.getGuestArr().setTeen(dto.getERKTIN());

		if (dto.getERKGYE() != null)
			this.getGuestArr().setChild(dto.getERKGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->ARR");

		/***
		 * Vendégéjszakák
		 */
		if (dto.getEJSFEL() != null)
			this.getGuestNts().setAdult(dto.getEJSFEL());

		if (dto.getEJSTIN() != null)
			this.getGuestNts().setTeen(dto.getEJSTIN());

		if (dto.getEJSGYE() != null)
			this.getGuestNts().setChild(dto.getEJSGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->NTS");

		/**
		 * Utazó vendégek
		 */
		if (dto.getUTAFEL() != null)
			this.getGuestDep().setAdult(dto.getUTAFEL());

		if (dto.getUTATIN() != null)
			this.getGuestDep().setTeen(dto.getUTATIN());

		if (dto.getUTAGYE() != null)
			this.getGuestDep().setChild(dto.getUTAGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->DEP");

		/**
		 * Bruttó terhelések
		 */
		if (dto.getTERSZA() != null)
			this.getGrsChg().setRoom(dto.getTERSZA());

		if (dto.getTERREG() != null)
			this.getGrsChg().setBreakfast(dto.getTERREG());

		if (dto.getTERIFA() != null)
			this.getGrsChg().setCityTax(dto.getTERIFA());

		if (dto.getTERVEN() != null)
			this.getGrsChg().setVen(dto.getTERVEN());

		if (dto.getTERREN() != null)
			this.getGrsChg().setRen(dto.getTERREN());

		if (dto.getTERSPA() != null)
			this.getGrsChg().setSpa(dto.getTERSPA());

		if (dto.getTERFIT() != null)
			this.getGrsChg().setFit(dto.getTERFIT());

		if (dto.getTEREGY() != null)
			this.getGrsChg().setOther(dto.getTEREGY());

		if (dto.getTERELO() != null)
			this.getGrsChg().setDeposit(dto.getTERELO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->GrsChg");

		/**
		 * Bruttó engedmények
		 */
		if (dto.getENGSZA() != null)
			this.getGrsDsc().setRoom(dto.getENGSZA());

		if (dto.getENGREG() != null)
			this.getGrsDsc().setBreakfast(dto.getENGREG());

		if (dto.getENGIFA() != null)
			this.getGrsDsc().setCityTax(dto.getENGIFA());

		if (dto.getENGVEN() != null)
			this.getGrsDsc().setVen(dto.getENGVEN());

		if (dto.getENGREN() != null)
			this.getGrsDsc().setRen(dto.getENGREN());

		if (dto.getENGSPA() != null)
			this.getGrsDsc().setSpa(dto.getENGSPA());

		if (dto.getENGFIT() != null)
			this.getGrsDsc().setFit(dto.getENGFIT());

		if (dto.getENGEGY() != null)
			this.getGrsDsc().setOther(dto.getENGEGY());

		if (dto.getENGELO() != null)
			this.getGrsDsc().setDeposit(dto.getENGELO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->GrsDsc");

		/**
		 * Nettó terhelések
		 */
		if (dto.getTENSZA() != null)
			this.getNetChg().setRoom(dto.getTENSZA());

		if (dto.getTENREG() != null)
			this.getNetChg().setBreakfast(dto.getTENREG());

		if (dto.getTENIFA() != null)
			this.getNetChg().setCityTax(dto.getTENIFA());

		if (dto.getTENVEN() != null)
			this.getNetChg().setVen(dto.getTENVEN());

		if (dto.getTENREN() != null)
			this.getNetChg().setRen(dto.getTENREN());

		if (dto.getTENSPA() != null)
			this.getNetChg().setSpa(dto.getTENSPA());

		if (dto.getTENFIT() != null)
			this.getNetChg().setFit(dto.getTENFIT());

		if (dto.getTENEGY() != null)
			this.getNetChg().setOther(dto.getTENEGY());

		if (dto.getTENELO() != null)
			this.getNetChg().setDeposit(dto.getTENELO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->NetChg");

		/**
		 * Bruttó engedmények
		 */
		if (dto.getENNSZA() != null)
			this.getNetDsc().setRoom(dto.getENNSZA());

		if (dto.getENNREG() != null)
			this.getNetDsc().setBreakfast(dto.getENNREG());

		if (dto.getENNIFA() != null)
			this.getNetDsc().setCityTax(dto.getENNIFA());

		if (dto.getENNVEN() != null)
			this.getNetDsc().setVen(dto.getENNVEN());

		if (dto.getENNREN() != null)
			this.getNetDsc().setRen(dto.getENNREN());

		if (dto.getENNSPA() != null)
			this.getNetDsc().setSpa(dto.getENNSPA());

		if (dto.getENNFIT() != null)
			this.getNetDsc().setFit(dto.getENNFIT());

		if (dto.getENNEGY() != null)
			this.getNetDsc().setOther(dto.getENNEGY());

		if (dto.getENNELO() != null)
			this.getNetDsc().setDeposit(dto.getENNELO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->end");

	}

	public CubeAcstPfm(Hotel hotel, HwFroFocDto dto) {
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

		if (dto.getRESNUM() != null)
			this.setResId(dto.getRESNUM().longValue());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->DIM");

		/**
		 * Szobák
		 */
		if (dto.getERKSZO() != null)
			this.setRoomArr(dto.getERKSZO());

		if (dto.getEJSSZO() != null)
			this.setRoomNts(dto.getEJSSZO());

		if (dto.getUTASZO() != null)
			this.setRoomDep(dto.getUTASZO());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->ROOMS");

		/**
		 * Érkező vendégek
		 */
		if (dto.getERKFEL() != null)
			this.getGuestArr().setAdult(dto.getERKFEL());

		if (dto.getERKTIN() != null)
			this.getGuestArr().setTeen(dto.getERKTIN());

		if (dto.getERKGYE() != null)
			this.getGuestArr().setChild(dto.getERKGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->ARR");

		/***
		 * Vendégéjszakák
		 */
		if (dto.getEJSFEL() != null)
			this.getGuestNts().setAdult(dto.getEJSFEL());

		if (dto.getEJSTIN() != null)
			this.getGuestNts().setTeen(dto.getEJSTIN());

		if (dto.getEJSGYE() != null)
			this.getGuestNts().setChild(dto.getEJSGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->NTS");

		/**
		 * Utazó vendégek
		 */
		if (dto.getUTAFEL() != null)
			this.getGuestDep().setAdult(dto.getUTAFEL());

		if (dto.getUTATIN() != null)
			this.getGuestDep().setTeen(dto.getUTATIN());

		if (dto.getUTAGYE() != null)
			this.getGuestDep().setChild(dto.getUTAGYE());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->DEP");

		/**
		 * Bruttó terhelések
		 */
		if (dto.getFORSZA() != null)
			this.getGrsChg().setRoom(dto.getFORSZA());

		if (dto.getFORREG() != null)
			this.getGrsChg().setBreakfast(dto.getFORREG());

		if (dto.getFORIFA() != null)
			this.getGrsChg().setCityTax(dto.getFORIFA());

		if (dto.getFORVEN() != null)
			this.getGrsChg().setVen(dto.getFORVEN());

		if (dto.getFORREN() != null)
			this.getGrsChg().setRen(dto.getFORREN());

		if (dto.getFORSPA() != null)
			this.getGrsChg().setSpa(dto.getFORSPA());

		if (dto.getFOREGY() != null)
			this.getGrsChg().setOther(dto.getFOREGY());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->GrsChg");

		/**
		 * Nettó terhelések
		 */
		if (dto.getNFORSZA() != null)
			this.getNetChg().setRoom(dto.getNFORSZA());

		if (dto.getNFORREG() != null)
			this.getNetChg().setBreakfast(dto.getNFORREG());

		if (dto.getNFORIFA() != null)
			this.getNetChg().setCityTax(dto.getNFORIFA());

		if (dto.getNFORVEN() != null)
			this.getNetChg().setVen(dto.getNFORVEN());

		if (dto.getNFORREN() != null)
			this.getNetChg().setRen(dto.getNFORREN());

		if (dto.getNFORSPA() != null)
			this.getNetChg().setSpa(dto.getNFORSPA());

		if (dto.getNFOREGY() != null)
			this.getNetChg().setOther(dto.getNFOREGY());

		// LOGGER.info("CubePrfmActl(HwFroCubDto dto)->NetChg");
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Integer getRoomArr() {
		return roomArr;
	}

	public void setRoomArr(Integer roomArr) {
		this.roomArr = roomArr;
	}

	public Integer getRoomDep() {
		return roomDep;
	}

	public void setRoomDep(Integer roomDep) {
		this.roomDep = roomDep;
	}

	public CubeGuestAgeDetail getGuestArr() {
		return guestArr;
	}

	public void setGuestArr(CubeGuestAgeDetail guestArr) {
		this.guestArr = guestArr;
	}

	public CubeGuestAgeDetail getGuestNts() {
		return guestNts;
	}

	public void setGuestNts(CubeGuestAgeDetail guestNts) {
		this.guestNts = guestNts;
	}

	public CubeGuestAgeDetail getGuestDep() {
		return guestDep;
	}

	public void setGuestDep(CubeGuestAgeDetail guestDep) {
		this.guestDep = guestDep;
	}

	public CubeDepDetail getGrsChg() {
		return grsChg;
	}

	public void setGrsChg(CubeDepDetail grsChg) {
		this.grsChg = grsChg;
	}

	public CubeDepDetail getNetChg() {
		return netChg;
	}

	public void setNetChg(CubeDepDetail netChg) {
		this.netChg = netChg;
	}

	public CubeDepDetail getGrsDsc() {
		return grsDsc;
	}

	public void setGrsDsc(CubeDepDetail grsDsc) {
		this.grsDsc = grsDsc;
	}

	public CubeDepDetail getNetDsc() {
		return netDsc;
	}

	public void setNetDsc(CubeDepDetail netDsc) {
		this.netDsc = netDsc;
	}

	public CubeDepDetail getGrsRev() {
		grsRev.clear();
		grsRev.add(grsChg);
		grsRev.add(grsDsc);
		return grsRev;
	}

	public void setGrsRev(CubeDepDetail grsRev) {
		this.grsRev = grsRev;
	}

	public CubeDepDetail getNetRev() {
		netRev.clear();
		netRev.add(netChg);
		netRev.add(netDsc);
		return netRev;
	}

	public void setNetRev(CubeDepDetail netRev) {
		this.netRev = netRev;
	}

	public Double getGrsAdr() {
		return getGrsRev().getRoom() / getRoomNts();
	}

	public void setGrsAdr(Double grsAdr) {
		this.grsAdr = grsAdr;
	}

	public Double getNetAdr() {
		return getNetRev().getRoom() / getRoomNts();
	}

	public void setNetAdr(Double netAdr) {
		this.netAdr = netAdr;
	}

	/**
	 * Visszaadja a dimenzió mező értékét
	 * 
	 * @param dimName
	 * @return
	 */
	@Override
	public String getDimValue(Dimension dimName) {
		switch (dimName) {
		case RES_ID:
			return this.getResId().toString();
		default:
			return super.getDimValue(dimName);
		}
	}

	/**
	 * Visszaadja az entitás measure paraméterrel jelölt mezőjének értékét.
	 * 
	 * @param measure1
	 * @param measure2
	 * @return
	 */
	@Override
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		switch (measure1) {

		case RRES_ARR:
			// LOGGER.info("getMeasValue()->ROOM_ARR");
			if (this.getRoomArr() != null) {
				// LOGGER.info("getMeasValue()->getRoomArr()=" + getRoomArr());
				return this.getRoomArr().doubleValue();
			}
		case RRES_DEP:
			if (this.getRoomDep() != null)
				return this.getRoomDep().doubleValue();

		case GUEST_ARR:
			if (this.getGuestArr() != null)
				return this.getGuestArr().getMeasValue(measure2);

		case GUEST_NTS:
			if (this.getGuestNts() != null)
				return this.getGuestNts().getMeasValue(measure2);

		case GUEST_DEP:
			if (this.getGuestDep() != null)
				return this.getGuestDep().getMeasValue(measure2);

		case GRS_CHG:
			// LOGGER.info("getValueOfMeasure->case GRS_CHG");
			if (this.getGrsChg() != null) {
				// LOGGER.info("getValueOfMeasure->case GRS_CHG->not null");
				return this.getGrsChg().getMeasValue(measure2);
			}

		case NET_CHG:
			if (this.getNetChg() != null)
				return this.getNetChg().getMeasValue(measure2);

		case GRS_DSC:
			if (this.getGrsDsc() != null)
				return this.getGrsDsc().getMeasValue(measure2);

		case NET_DSC:
			if (this.getNetDsc() != null)
				return this.getNetDsc().getMeasValue(measure2);

		case GRS_REV:
			// LOGGER.info("getValueOfMeasure->case GRS_REV");
			if (this.getGrsRev() != null) {
				// LOGGER.info("getValueOfMeasure->case GRS_REV->not null");
				return this.getGrsRev().getMeasValue(measure2);
			}

		case NET_REV:
			if (this.getNetRev() != null)
				return this.getNetRev().getMeasValue(measure2);

		default:
			return super.getValueOfMeasure(measure1, measure2);
		}
	}

	@Override
	public CubeOcc addToOccData(CubeOcc joined) {
		joined = super.addToOccData(joined);
		joined.addGuestNts(getGuestNts().getTotal());
		return joined;
	}
}
