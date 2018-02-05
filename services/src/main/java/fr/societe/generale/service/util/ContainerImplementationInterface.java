package fr.societe.generale.service.util;

import fr.societe.generale.util.exception.ApplicationException;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public interface ContainerImplementationInterface<T> extends AbstractServiceImplementationInterface<T>{
	/**
	 * 
	 * @param container
	 * @throws ApplicationException
	 */
	void create(T container) throws ApplicationException;
	/**
	 * 
	 * @param container
	 * @throws ApplicationException
	 */
	void update(T container) throws ApplicationException;


}
