/**
 * 
 */
package hu.hw.cloud.server.entity;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.hk.AssignmentSummaryDto;

/**
 * @author CR
 *
 */
public class Utils {

	/**
	 * 
	 * @param dtos
	 * @param attendant
	 * @return
	 */
	public static AssignmentSummaryDto findByAttendant(List<AssignmentSummaryDto> dtos, final AppUserDto attendant) {
		Predicate<AssignmentSummaryDto> condition = new Predicate<AssignmentSummaryDto>() {
			public boolean evaluate(AssignmentSummaryDto object) {
				return (object.getAttendantDto().equals(attendant));
			}
		};
		AssignmentSummaryDto result = (AssignmentSummaryDto) CollectionUtils.select(dtos, condition);

		return result;
	}

}
