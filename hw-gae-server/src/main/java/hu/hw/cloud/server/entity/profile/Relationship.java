/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
@Entity
public class Relationship extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(Relationship.class);
	
	private String forward;
	
	private String reverse;
	
	public Relationship() {
		logger.info("ProfileLinkType()");
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getReverse() {
		return reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	
}
