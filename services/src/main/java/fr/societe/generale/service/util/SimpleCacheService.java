package fr.societe.generale.service.util;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public abstract class SimpleCacheService<T> extends AbstractCacheService<T>{


	protected SimpleImplementationInterface<T> impl;

	protected SimpleCacheService(){
	}
	/**
	 * 
	 * @param impl
	 */
	public void setImpl(SimpleImplementationInterface<T> impl){
		this.impl=impl;
	}
	/**
	 * get impl
	 */
	protected SimpleImplementationInterface<T> getImpl(){
		return this.impl;
	}
	/**
	 * 
	 * @param key
	 * @param t
	 */
	protected void createAndputIntoCache(String key, T t) {
		justPutIntoCache(key,t);		
		getImpl().createOrUpdate(key,t);
	}

}
