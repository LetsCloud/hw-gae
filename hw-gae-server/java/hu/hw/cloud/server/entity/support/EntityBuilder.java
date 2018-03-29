/**
 * 
 */
package hu.hw.cloud.server.entity.support;

import hu.hw.cloud.server.entity.BaseEntity;

/**
 * @author CR
 *
 */
public abstract class EntityBuilder<T extends BaseEntity> {

	protected T product;

	{
		initProduct();
	}

	public T build(Boolean... doNotPersist) {
		T product = assembleProduct();
		T temp = saveProduct(product);
		initProduct();
		return temp;
	}

	abstract void initProduct();

	abstract T assembleProduct();

	abstract T saveProduct(T product);
}