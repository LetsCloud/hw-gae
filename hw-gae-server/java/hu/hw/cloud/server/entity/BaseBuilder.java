/**
 * 
 */
package hu.hw.cloud.server.entity;

/**
 * @author CR
 *
 */
public abstract class BaseBuilder<T extends BaseBuilder<T>> {

	protected T instance;

	public BaseBuilder() {
		init();
	}

	public T build() {
		return instance;
	}

	protected abstract void init();
}
