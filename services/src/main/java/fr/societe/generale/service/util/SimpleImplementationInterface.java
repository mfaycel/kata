package fr.societe.generale.service.util;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public interface SimpleImplementationInterface<T> extends AbstractServiceImplementationInterface<T>{
	/**
	 * get key
	 */
	T get(String key);
	/**
	 * 
	 * @param key
	 * @param t
	 */
	void createOrUpdate(String key, T t);

}
