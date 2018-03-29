/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.hk.HkAssignment;
import hu.hw.cloud.shared.dto.hk.HkAssignmentDto;
import hu.hw.cloud.shared.dto.hk.AssignmentSummaryDto;

/**
 * @author CR
 *
 */
public interface HkAssignmentService extends CrudService<HkAssignment, HkAssignmentDto> {

	List<HkAssignment> getByDateAndHotel(String hotelWebSafeKey, Date date);
	
	List<AssignmentSummaryDto> getAssignemntSummary(String hotelWebSafeKey, Date date);
}
