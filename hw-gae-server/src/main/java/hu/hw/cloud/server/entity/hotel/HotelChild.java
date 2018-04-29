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
		this.updEntityWithDto(dto);
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

	/**
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(HotelChildDto dto) {
		super.updEntityWithDto(dto);
		
		this.setHotel(new Hotel(dto.getHotelDto()));
	}

	/**
	 * 
	 * @param entity
	 */
	public void updEntityWithEntity(HotelChild entity) {
		super.updEntityWithEntity(entity);
		
		this.setHotel(entity.getHotel());
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public HotelChildDto updDtoWithEntity(HotelChildDto dto) {
		dto = (HotelChildDto) super.updDtoWithEntity(dto);

		if (this.getHotel() != null)
			dto.setHotelDto(Hotel.createDto(this.getHotel()));

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
