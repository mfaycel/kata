package fr.societe.generale.service.mem;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.societe.generale.model.KataUser;
import fr.societe.generale.service.container.UserContainer;
import fr.societe.generale.service.util.UserImplementationInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service
public class UserMP  extends MemoryContainerImplementation <  UserContainer   > implements UserImplementationInterface <UserContainer >{

	public static final UserMP SINGLETON=new UserMP(); 

	private  ConcurrentHashMap<String, UserContainer> CACHE=new ConcurrentHashMap<String , UserContainer>();
	private static final Integer INITIAL_VERSION = 1;
	private UserMP(){
	}

	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());

		return ret;
	}



	@Override
	public KataUser authenticate(String username, String password) {
		try {
			List<KataUser> kataUsers = CACHE.values().stream()
					.filter(p -> p.user.getName().equals(username))
					.filter(p -> p.user.getPassword().equals(password))
					.map(p -> p.user).collect(Collectors.toList());
			return kataUsers ==null ?null :kataUsers.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<KataUser> findAll() {
		return  CACHE.values().stream()
				.map(p -> p.user).collect(Collectors.toList());
	}


	@Override
	public void update(UserContainer container) {
		CACHE.put(container.getKey(), container);			
	}	
	@Override
	public UserContainer get(String key) {
		return CACHE.get(key);
	}
}

