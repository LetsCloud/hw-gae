/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.googlecode.objectify.annotation.Entity;

/**
 * ÁFA kulcs.
 * 
 * @author CR
 *
 */
@Entity
public class VatRate extends AccountChild {

	/**
	 * Az ÁFA kulcsok érvényeségi dátum szerint rendezését szolgáló Comparator
	 */
	public static Comparator<VatRate> ORDER_BY_VALIDFROM = new Comparator<VatRate>() {
		public int compare(VatRate one, VatRate other) {
			return one.getValidFrom().compareTo(other.getValidFrom());
		}
	};

	/**
	 * 
	 * @param vatRates
	 * @param code
	 * @param date
	 * @return
	 */
	public static VatRate getValidVatRate(List<VatRate> vatRates, final String code, final Date date) {
		Predicate<VatRate> condition = new Predicate<VatRate>() {
			public boolean evaluate(VatRate object) {
				return ((object.getCode().equals(code)) && (object.getValidFrom().compareTo(date) >= 0));
			}
		};
		List<VatRate> result = (List<VatRate>) CollectionUtils.select(vatRates, condition);

		if (result.isEmpty())
			return null;

		return Collections.max(result, VatRate.ORDER_BY_VALIDFROM);
	}

	/**
	 * ÁFA kód.
	 */
	private String code;

	/**
	 * Mettől érvényes
	 */
	private Date validFrom;

	/**
	 * Milyen kulccsal(%)
	 */
	private Float rate;

	/**
	 * Megnevezés
	 */
	private String description;

	public VatRate() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
