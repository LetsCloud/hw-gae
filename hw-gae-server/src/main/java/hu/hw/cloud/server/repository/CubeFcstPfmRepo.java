/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.cube.CubeActlPfm;
import hu.hw.cloud.server.entity.cube.CubeFcstPfm;

/**
 * @author CR
 *
 */
public interface CubeFcstPfmRepo extends CrudRepository<CubeFcstPfm>, CubeRepo<CubeFcstPfm> {

	List<CubeFcstPfm> getPickupForStayDate(String hotelWebSafeKey, Date stayDate);
}
