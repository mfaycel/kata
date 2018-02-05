package fr.societe.generale.service.mem;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import fr.societe.generale.service.util.Container;
import fr.societe.generale.service.util.ContainerImplementationInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <T>
 */
public class MemoryContainerImplementation<T extends Container> implements ContainerImplementationInterface< T >{

	private  ConcurrentHashMap<String, T> CACHE=new ConcurrentHashMap<String , T>();

	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());

		return ret;
	}

	@Override
	public void create(T container) {
		CACHE.put(container.getKey(), container);			
	}

	@Override
	public void update(T container) {
		CACHE.put(container.getKey(), container);			
	}

	protected String generateRandomCellId() {
		return getSaltString()+"-"+getSaltString();
	}

	/**
	 * random string
	 * @return
	 */
	protected String getSaltString() {
		String SALTCHARS = "ABCDEF1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 4) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Override
	public T get(String key) {
		return CACHE.get(key);
	}

	@Override
	public boolean exists(String key) {
		return CACHE.containsKey(key);
	}
}
