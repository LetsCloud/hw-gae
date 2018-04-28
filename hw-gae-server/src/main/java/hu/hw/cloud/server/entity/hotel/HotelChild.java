/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Parent;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.shared.dto.hotel.HotelChildDto;

/**
 * @author CR
 *
 */
public class HotelChild extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(HotelChild.class.getName());

	@Parent
	private Ref<Hotel> hotelRef;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public HotelChild() {
		logger.info("HotelChild()");
	}

	/**
	 * Konstruktor DTO-ból.
	 * 
	 * @param dto
	 */
	public HotelChild(HotelChildDto dto) {
		super(dto);
		this.update(dto);
	}

	/**
	 * 
	 * @param source
	 */
	public HotelChild(HotelChild source) {
		super(source);
		this.setHotel(source.getHotel());
	}

	public Hotel getHotel() {
		return hotelRef.get();
	}

	public void setHotel(Hotel hotel) {
		this.hotelRef = Ref.create(hotel);
	}

	public void update(HotelChildDto dto) {
		super.update(dto);
		this.setHotel(new Hotel(dto.getHotelDto()));
	}

	public void update(HotelChild entity) {
		super.update(entity);
		this.setHotel(entity.getHotel());
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public HotelChildDto updateDto(HotelChildDto dto) {
		dto = (HotelChildDto) super.updateDto(dto);
		logger.info("HotelChild()-updateDto-1");
		if (this.getHotel() != null) {
			logger.info("HotelChild()-updateDto-2");
			dto.setHotelDto(Hotel.createDto(this.getHotel()));
		}
		return dto;
	}

	/**
	 * 
	 * @author CR
	 *
	 * @param <T>
	 */
	public static class Builder<T extends Builder<?>> {

		private Hotel hotel;

		public Builder() {
		}

		@SuppressWarnings("unchecked")
		public T hotel(Hotel hotel) {
			this.hotel = hotel;
			return (T) this;
		}

		public HotelChild build() {
			return new HotelChild(this);
		}
	}

	protected HotelChild(Builder<?> builder) {
		this.setHotel(builder.hotel);
	}

}
