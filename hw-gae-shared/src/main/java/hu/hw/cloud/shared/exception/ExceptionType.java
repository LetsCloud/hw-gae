/**
 * 
 */
package hu.hw.cloud.shared.exception;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public enum ExceptionType implements Serializable {
	MISMATCHED_ACCOUNT, MISSING_VALUE, FAILD_ENTITIY_VALIDATION, UNIQUE_INDEX_CONFLICT, UNKNOWN;
}
