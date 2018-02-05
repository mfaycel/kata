package fr.societe.generale.service.util;

import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public interface AbstractServiceImplementationInterface<T> extends RuntimeCheckInterface{

	T get(String key);

	boolean exists(String key);
	

}
