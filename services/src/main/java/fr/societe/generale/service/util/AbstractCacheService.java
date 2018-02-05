package fr.societe.generale.service.util;


import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.societe.generale.util.exception.ApplicationException;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public abstract class AbstractCacheService<T> extends AbstractService{
	/**
	 * load cache
	 */
	private LoadingCache<String, T> CACHE = CacheBuilder.newBuilder()
			.maximumSize(getMaximunSize())
			.expireAfterWrite(getExpirationInMinutes(), TimeUnit.DAYS)
			.build(new CacheLoader<String, T>() {
				public T load(String key) throws ApplicationException {
					T ret=loadInner(key);
					return ret;
				}
			});


	protected AbstractCacheService(){
	}

	@Override
	protected RuntimeCheckResult check(RuntimeCheckResult ret) {

		ret.add("Impl",getImpl());

		return ret;
	}
	/**
	 * 	
	 * @return
	 */
	protected abstract AbstractServiceImplementationInterface<T> getImpl();
	/**
	 * 
	 * @return
	 */
	protected int getMaximunSize(){
		return 1000;
	}
	/**
	 * 
	 * @return
	 */
	protected int getExpirationInMinutes(){
		return 5;
	}
	/**
	 * 
	 * @param key
	 * @return
	 * @throws ApplicationException
	 */
	protected T loadInner(String key) throws ApplicationException{
		T t=getImpl().get(key);

		return t;
	}
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected T getFromCache(String key,T defaultValue) {
		if (key == null){
			return defaultValue;
		} else {
			try {
				T ret=CACHE.get(key);
				if (ret==null){
					return defaultValue;
				} else {
					return ret;
				}
			}catch (Exception e) {
				return defaultValue;
			}
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws ApplicationException
	 */
	protected T getFromCache(String key) throws ApplicationException{
		if (key == null){
			throw new ApplicationException("Not null key supported for:"+getClass().getName());
		} else {
			try {
				return CACHE.get(key);
			}catch (Exception e) {
				throw new ApplicationException("Key:"+key,e);
			}
		}
	}
	/**
	 * 
	 * @param key
	 * @param t
	 */
	protected void justPutIntoCache(String key,T t) {
		CACHE.put(key,t);
	}
	/**
	 * 
	 * @param key
	 */
	protected void justRemoveFromCache(String key) {
		CACHE.invalidate(key);
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	protected boolean exists(String key) {
		if (getFromCache(key,null) != null){
			return true;
		} else {
			return getImpl().exists(key);
		}
	}
}
