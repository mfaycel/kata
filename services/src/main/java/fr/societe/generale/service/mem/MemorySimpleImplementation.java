package fr.societe.generale.service.mem;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import fr.societe.generale.service.util.SimpleImplementationInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <T>
 */
public class MemorySimpleImplementation<T> implements SimpleImplementationInterface< T >{

	protected  ConcurrentHashMap<String, T> CACHE=new ConcurrentHashMap<String , T>();

	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());
		
		return ret;
	}

	@Override
	public T get(String key) {
		return CACHE.get(key);
	}
	

	@Override
	public boolean exists(String key) {
		return CACHE.containsKey(key);
	}

	@Override
	public void createOrUpdate(String key, T t) {
		CACHE.put(key, t);		
	}
	
/**
 * 
 * @return
 */
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

}
