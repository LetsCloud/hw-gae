/**
 * 
 */
package hu.hw.cloud.shared.cnst;

import java.io.Serializable;

/**
 * IFA számítás alapja.
 * 
 * @author CR
 *
 */
public enum CityTaxBase implements Serializable {

	/**
	 * A nettó szállásdíj X százaléka.
	 */
	ROOMREVENUE,

	/**
	 * Vendégéjszakánként X forint.
	 */
	GUESTNIGHT

}
