/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.cubegen.Generator;
import hu.hw.cloud.server.entity.TestBooking;
import hu.hw.cloud.server.entity.cube.CubeFcstPfm;
import hu.hw.cloud.server.entity.cube.CubeActlCap;
import hu.hw.cloud.server.entity.cube.CubeFcstCap;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.entity.cube.CubeActlPfm;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeFcstCapRepo;
import hu.hw.cloud.server.repository.CubeActlPfmRepo;
import hu.hw.cloud.server.repository.CubeBdgtCapRepo;
import hu.hw.cloud.server.repository.CubeBdgtPfmRepo;
import hu.hw.cloud.server.repository.CubeFcstPfmRepo;
import hu.hw.cloud.server.repository.CubeActlCapRepo;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.CubeService;
import hu.hw.cloud.server.service.impl.bigquery.BigQueryEngine;
import hu.hw.cloud.server.service.impl.cube.d3m6.CubeActlQueryD3m6;
import hu.hw.cloud.server.service.impl.cube.d3m6.CubeBdgtQueryD3m6;
import hu.hw.cloud.server.service.impl.cube.m1.CubeActlQueryM1;
import hu.hw.cloud.server.service.impl.cube.m1.CubeBdgtQueryM1;
import hu.hw.cloud.server.service.impl.cube.m1.CubeFcstQueryM1;
import hu.hw.cloud.server.utils.DateUtils;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.Perf1Dto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
public class CubeServiceImpl implements CubeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeServiceImpl.class.getName());

	private HotelRepository hotelRepository;

	private CubeBdgtCapRepo cubeBdgtCapRepo;

	private CubeBdgtPfmRepo cubeBdgtPfmRepo;

	private CubeActlCapRepo cubeActlCapRepo;

	private CubeActlPfmRepo cubeActlPfmRepo;

	private CubeFcstCapRepo cubeFcstCapRepo;

	private CubeFcstPfmRepo cubeFcstPfmRepo;

	public void setHotelRepository(HotelRepository hoteRepository) {
		this.hotelRepository = hoteRepository;
	}

	public void setCubeBdgtCapRepo(CubeBdgtCapRepo cubeBdgtCapRepo) {
		this.cubeBdgtCapRepo = cubeBdgtCapRepo;
	}

	public void setCubeBdgtPfmRepo(CubeBdgtPfmRepo cubeBdgtPfmRepo) {
		this.cubeBdgtPfmRepo = cubeBdgtPfmRepo;
	}

	public void setCubeActlCapRepo(CubeActlCapRepo cubeActlCapRepo) {
		this.cubeActlCapRepo = cubeActlCapRepo;
	}

	public void setCubeActlPfmRepo(CubeActlPfmRepo cubeActlPfmRepo) {
		this.cubeActlPfmRepo = cubeActlPfmRepo;
	}

	public void setCubeFcstCapRepo(CubeFcstCapRepo cubeCapFrcstRepo) {
		this.cubeFcstCapRepo = cubeCapFrcstRepo;
	}

	public void setCubeFcstPfmRepo(CubeFcstPfmRepo cubePerfFrcstRepo) {
		this.cubeFcstPfmRepo = cubePerfFrcstRepo;
	}

	@Override
	public void insertCapacityForecast(HwFroFocDto dto) throws Throwable {
		// LOGGER.info("insertCapacityForecast");

		Hotel hotel = hotelRepository.findByWebSafeKey(dto.getHotelId());
		CubeFcstCap entity = new CubeFcstCap(hotel, dto);
		cubeFcstCapRepo.save(entity);
	}

	@Override
	public void insertMeasuresForecast(HwFroFocDto dto) throws Throwable {
		// LOGGER.info("insertMeasuresForecast");

		Hotel hotel = hotelRepository.findByWebSafeKey(dto.getHotelId());
		CubeFcstPfm entity = new CubeFcstPfm(hotel, dto);
		cubeFcstPfmRepo.save(entity);
	}

	@Override
	public void deleteForecast(String hotelWebSafeKey) throws Throwable {
		Key<Hotel> hotelKey = Key.create(hotelWebSafeKey);
		cubeFcstCapRepo.deleteAll(hotelKey);
		cubeFcstPfmRepo.deleteAll(hotelKey);
	}

	@Override
	public void generateTestData(String hotelWebSafeKey) {
		LOGGER.info("generateTestData_1");
		/*
		 * try { deleteForecast(hotelWebSafeKey); } catch (Throwable e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		LOGGER.info("generateTestData_2");

		Generator generator = new Generator();
		List<TestBooking> bookingCases = generator.start();
		/*
		 * Collections.sort(bookingCases, BookingCase.ORDER_BY_ARRIVALDATE); DateTime
		 * checkDateTime = new DateTime(2017, 03, 15, 0, 0, 0, 0); Date checkDate =
		 * checkDateTime.toDate();
		 * 
		 * for (BookingCase bx : bookingCases) { if
		 * ((checkDate.before(bx.getDeparture())) &&
		 * ((checkDate.equals(bx.getArrival())) || (checkDate.after(bx.getArrival()))))
		 * { LOGGER.info("generateTestData()->bookingCase->bookingDate=" +
		 * bx.getBookingDate() + ", arrival=" + bx.getArrival() + ", departure=" +
		 * bx.getDeparture() + ", nights=" + bx.getNights()); } }
		 */
		TestBooking minBookingDate = Collections.min(bookingCases, TestBooking.ORDER_BY_BOOKINGDATE);
		DateTime minDate = new DateTime(minBookingDate.getBookingDate());

		TestBooking maxBookingDate = Collections.max(bookingCases, TestBooking.ORDER_BY_BOOKINGDATE);
		DateTime maxDate = new DateTime(maxBookingDate.getBookingDate());
		int days = Days.daysBetween(minDate, maxDate).getDays();

		Collections.sort(bookingCases, TestBooking.ORDER_BY_BOOKINGDATE);
		// LOGGER.info("generateTestData()->days=" + days);

		Date forecastDate = minBookingDate.getBookingDate();
		// LOGGER.info("generateTestData()->forecastDate=" + forecastDate);
		for (int i = 0; i < days; i++) {
			LOGGER.info("--- " + i + ". ELOREJELZESI NAP->" + forecastDate);
			for (TestBooking bc : bookingCases) {
				// LOGGER.info("generateTestData()->for(BookingCase),bookingDate="
				// + bc.getBookingDate() + ",arrival="
				// + bc.getArrival());

				// Ha a foglalást tárgynapot követően rögzítettek, akkor nem
				// foglalkozunk vele
				if (bc.getBookingDate().after(forecastDate)) {
					// LOGGER.info("generateTestData()->break");
					break;
				}

				LOGGER.info("------ FOGLALAS: bookingDate=" + bc.getBookingDate() + ", arrival=" + bc.getArrival()
						+ ", nights=" + bc.getNights() + ", departure=" + bc.getDeparture());

				try {
					saveBookingCase(hotelWebSafeKey, forecastDate, bc);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// LOGGER.info("generateTestData()->for(i)->end");
			forecastDate = DateUtils.addDays(forecastDate, 1);
		}
		LOGGER.info("generateTestData()->end");
	}

	/**
	 * 
	 * @param hotelWebSafeKey
	 * @param forecastDate
	 * @param bc
	 * @throws Throwable
	 */
	private void saveBookingCase(String hotelWebSafeKey, Date forecastDate, TestBooking bc) throws Throwable {
		int nights = Days.daysBetween(new DateTime(bc.getArrival()), new DateTime(bc.getDeparture())).getDays();
		// LOGGER.info("saveBookingCase()->nights=" + nights);

		Date stayDate = bc.getArrival();
		for (int i = 0; i <= nights; i++) {
			LOGGER.info("--------- stayDate=" + stayDate);
			createMeasuresForecast(hotelWebSafeKey, forecastDate, stayDate, bc);
			stayDate = DateUtils.addDays(stayDate, 1);
		}
	}

	/**
	 * 
	 * @param hotelWebSafeKey
	 * @param forecastDate
	 * @param stayDate
	 * @param bc
	 * @throws Throwable
	 */
	private void createMeasuresForecast(String hotelWebSafeKey, Date forecastDate, Date stayDate, TestBooking bc)
			throws Throwable {
		// LOGGER.info("createMeasuresForecast()->forecastDate=" + forecastDate
		// + ", stayDate=" + stayDate + ", arrival="
		// + bc.getArrival() + ", departure=" + bc.getDeparture());
		Hotel hotel = hotelRepository.findByWebSafeKey(hotelWebSafeKey);
		CubeFcstPfm entity = new CubeFcstPfm();
		entity.setChannel(bc.getChannel());
		entity.setCountry(bc.getCountry());
		entity.setCustomer(bc.getCustomer());
		entity.setBsnsDate(stayDate);
		// entity.setGeneratedId(generatedId);
		entity.setHotel(hotel);
		// entity.setHotelWebSafeKey(hotelWebSafeKey);
		// entity.setId(id);
		entity.setMarket(bc.getMarket());
		entity.setFcstDate(forecastDate);
		entity.setRate(bc.getRate());
		// entity.setReservationId("");
		if (stayDate.equals(bc.getArrival())) {
			// entity.setGuestArr(2);
			entity.setRoomArr(1);
		}
		if (stayDate.equals(bc.getDeparture())) {
			// entity.setGuestDep(2);
			entity.setRoomDep(1);
		}
		if ((stayDate.before(bc.getDeparture()))
				&& (stayDate.equals(bc.getArrival()) || stayDate.after(bc.getArrival()))) {
			// entity.setGuestNts(2);
			entity.setRoomNts(1);
		}

		entity.setRoomType(bc.getRoomType());
		entity.setSource(bc.getSource());
		entity.setStatus("D");
		entity.setType(bc.getType());
		// entity.setVersion(version);
		DateTime sd = new DateTime(stayDate);
		// entity.setYearMonth(sd.getYear() + "-" + sd.getMonthOfYear());
		// entity.setYearWeek(sd.getYear() + "-" + sd.getWeekyear());

		LOGGER.info("--------- forecastDate=" + entity.getFcstDate() + ", stayDate=" + entity.getBsnsDate()
				+ ", roomArrivals=" + entity.getRoomArr() + ", roomNights=" + entity.getRoomNts() + ", roomDepartures="
				+ entity.getRoomDep());

		cubeFcstPfmRepo.save(entity);
	}

	@Override
	public void insCapAct(HwFroCubDto dto) throws Throwable {
		LOGGER.info("insCapAct");

		Hotel hotel = hotelRepository.findByWebSafeKey(dto.getHotelId());
		CubeActlCap entity = new CubeActlCap(hotel, dto);
		cubeActlCapRepo.save(entity);
	}

	@Override
	public void insPerfAct(HwFroCubDto dto) throws Throwable {
		LOGGER.info("insPerfAct");

		Hotel hotel = hotelRepository.findByWebSafeKey(dto.getHotelId());
		CubeActlPfm entity = new CubeActlPfm(hotel, dto);
		LOGGER.info("insPerfAct->before save");
		cubeActlPfmRepo.save(entity);
		LOGGER.info("insPerfAct->after save");
	}

	@Override
	public List<D3m6Dto> getD3M6Data(CubeQueryParam param) {
		LOGGER.info("getD3M6Data()");
		// LOGGER.info("getD3M6Data->hotelKey=" + hotelKey);
		// LOGGER.info("getD3M6Data->getFromDate()=" + params.getFromDate());
		// LOGGER.info("getD3M6Data->getToDate()=" + params.getToDate());
		Map<String, CubeResultDto> modelD3M6DtoMap = new HashMap<String, CubeResultDto>();

		if (param.isBdgtRequired()) {
			LOGGER.info("getD3M6Data->isBdgtRequired()");
			CubeBdgtQueryD3m6 cubeBdgtQuery = new CubeBdgtQueryD3m6(param, cubeBdgtCapRepo, cubeBdgtPfmRepo,
					modelD3M6DtoMap);
			cubeBdgtQuery.collectData();
		}

		if (param.isActlRequired()) {
			LOGGER.info("getD3M6Data->isActlRequired()");
			CubeActlQueryD3m6 cubeActlQuery = new CubeActlQueryD3m6(param, cubeActlCapRepo, cubeActlPfmRepo,
					modelD3M6DtoMap);
			cubeActlQuery.collectData();
		}

		List<D3m6Dto> result = new ArrayList<D3m6Dto>();
		for (Map.Entry<String, CubeResultDto> entry : modelD3M6DtoMap.entrySet()) {
			D3m6Dto m = (D3m6Dto) entry.getValue();
			if ((m.getM1() + m.getM2() + m.getM3() + m.getM4() + m.getM5() + m.getM6()) != 0)
				result.add(m);
		}
		return result;
	}

	@Override
	public List<DataWidgetValueM1Dto> getDataWidgetValues(CubeQueryParam param) {
		LOGGER.info("getDataWidgetValues()->param=" + param);

		Map<String, CubeResultDto> cubeResultDtoMap = new HashMap<String, CubeResultDto>();

		if (param.isBdgtRequired()) {
			CubeBdgtQueryM1 cubeBdgtQuery = new CubeBdgtQueryM1(param, cubeBdgtCapRepo, cubeBdgtPfmRepo,
					cubeResultDtoMap);
			cubeBdgtQuery.collectData();
		}

		if (param.isActlRequired()) {
			CubeActlQueryM1 cubeActlQuery = new CubeActlQueryM1(param, cubeActlCapRepo, cubeActlPfmRepo,
					cubeResultDtoMap);
			cubeActlQuery.collectData();
		}

		if (param.isFcstRequired()) {
			CubeFcstQueryM1 cubeFcstQuery = new CubeFcstQueryM1(param, cubeFcstCapRepo, cubeFcstPfmRepo,
					cubeResultDtoMap);
			cubeFcstQuery.collectData();
		}

		List<DataWidgetValueM1Dto> result2 = new ArrayList<DataWidgetValueM1Dto>();
		for (Map.Entry<String, CubeResultDto> entry : cubeResultDtoMap.entrySet()) {
			result2.add((DataWidgetValueM1Dto) entry.getValue());
		}

		return result2;
	}

	@Override
	public List<DataWidgetValueM1Dto> getM1ValuesFromBigQuery(CubeQueryParam param) {
		LOGGER.info("getDataWidgetValues()->param=" + param);

		BigQueryEngine bqe = new BigQueryEngine(param);

		for (String sql : bqe.buildQueries()) {
			LOGGER.info("getDataWidgetValues()->sql=" + sql);
		}
		return null;
	}

	@Override
	public List<Perf1Dto> queryPerf1() {
		LOGGER.info("queryPerf1-start");
		List<Perf1Dto> result = new ArrayList<Perf1Dto>();

		result.add(new Perf1Dto("2015.05.01.",98.38,98.38,29842.00,18031.00,29359.00,17739.00));
		result.add(new Perf1Dto("2015.05.02.",98.06,98.38,29046.00,18820.00,28482.00,18515.00));
		result.add(new Perf1Dto("2015.05.03.",44.34,93.53,22550.00,18024.00,9998.00,16857.00));
		result.add(new Perf1Dto("2015.05.04.",42.39,61.17,17476.00,17253.00,7409.00,10553.00));
		result.add(new Perf1Dto("2015.05.05.",43.69,60.19,17945.00,17885.00,7840.00,10766.00));
		result.add(new Perf1Dto("2015.05.06.",65.05,74.11,16145.00,18560.00,10502.00,13755.00));
		result.add(new Perf1Dto("2015.05.07.",92.23,88.67,15884.00,14749.00,14650.00,13079.00));
		result.add(new Perf1Dto("2015.05.08.",89.97,92.88,18028.00,15621.00,16220.00,14509.00));
		result.add(new Perf1Dto("2015.05.09.",82.52,97.73,17672.00,17116.00,14584.00,16728.00));
		result.add(new Perf1Dto("2015.05.10.",30.74,70.87,15121.00,16920.00,4649.00,11992.00));
		result.add(new Perf1Dto("2015.05.11.",43.04,28.80,17011.00,16463.00,7322.00,4742.00));
		result.add(new Perf1Dto("2015.05.12.",52.10,44.01,18769.00,16743.00,9779.00,7369.00));
		result.add(new Perf1Dto("2015.05.13.",52.75,55.99,18614.00,18799.00,9819.00,10525.00));
		result.add(new Perf1Dto("2015.05.14.",82.52,71.84,18421.00,20704.00,15202.00,14874.00));
		result.add(new Perf1Dto("2015.05.15.",87.06,61.81,19829.00,18734.00,17262.00,11580.00));
		result.add(new Perf1Dto("2015.05.16.",62.14,69.58,22472.00,19072.00,13963.00,13270.00));
		result.add(new Perf1Dto("2015.05.17.",43.37,77.67,19348.00,18506.00,8390.00,14374.00));
		result.add(new Perf1Dto("2015.05.18.",54.69,35.60,19457.00,21939.00,10642.00,7810.00));
		result.add(new Perf1Dto("2015.05.19.",66.02,51.13,20416.00,20488.00,13478.00,10476.00));
		result.add(new Perf1Dto("2015.05.20.",94.82,61.17,19484.00,20241.00,18475.00,12380.00));
		result.add(new Perf1Dto("2015.05.21.",90.61,58.25,19441.00,18619.00,17617.00,10846.00));
		result.add(new Perf1Dto("2015.05.22.",47.57,82.20,23326.00,16500.00,11097.00,13563.00));
		result.add(new Perf1Dto("2015.05.23.",77.02,82.52,24235.00,16487.00,18666.00,13606.00));
		result.add(new Perf1Dto("2015.05.24.",65.37,53.72,23387.00,19782.00,15288.00,10627.00));
		result.add(new Perf1Dto("2015.05.25.",36.89,31.72,20632.00,16971.00,7612.00,5382.00));
		result.add(new Perf1Dto("2015.05.26.",55.02,45.95,19391.00,17462.00,10668.00,8024.00));
		result.add(new Perf1Dto("2015.05.27.",65.05,38.51,15795.00,18425.00,10275.00,7096.00));
		result.add(new Perf1Dto("2015.05.28.",89.97,47.57,17386.00,19574.00,15642.00,9312.00));
		result.add(new Perf1Dto("2015.05.29.",89.32,94.82,19470.00,25071.00,17391.00,23773.00));
		result.add(new Perf1Dto("2015.05.30.",66.67,95.79,21511.00,26469.00,14341.00,25356.00));
		result.add(new Perf1Dto("2015.05.31.",44.34,96.76,20138.00,26628.00,8929.00,25767.00));

		LOGGER.info("queryPerf1-end");
		return result;
	}
}
