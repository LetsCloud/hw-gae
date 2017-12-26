/**
 * 
 */
package hu.hw.cloud.shared.dto.pf;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class GuestDto extends PersonDto {

	private String nationality;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
