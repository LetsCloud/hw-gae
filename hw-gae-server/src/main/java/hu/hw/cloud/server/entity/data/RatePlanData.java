/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import hu.hw.cloud.server.entity.hotel.RatePlan;
import hu.hw.cloud.server.repository.CrudRepository;
import hu.hw.cloud.server.service.DataBuilderService;

/**
 * @author CR
 *
 */
public class RatePlanData extends BaseData<RatePlan> {

	public RatePlanData(DataBuilderService dbs, CrudRepository<RatePlan> repo) {
		super(dbs, repo);
	}

}
