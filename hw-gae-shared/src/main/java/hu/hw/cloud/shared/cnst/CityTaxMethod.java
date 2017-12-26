/**
 * 
 */
package hu.hw.cloud.shared.cnst;

/**
 * IFA kalkulálás módja.
 * 
 * @author CR
 *
 */
public enum CityTaxMethod {
	/**
	 * Beleértve az árba
	 */
	INCLUDED,
	/**
	 * Nincs beleértve az árba
	 */
	EXCLUDED,
	/**
	 * IFA mentes
	 */
	EXEMPT
}
