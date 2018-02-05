package fr.societe.generale.service.mem;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.service.container.CompteContainer;
import fr.societe.generale.service.util.CompteImplementationInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service
public class CompteMP extends MemoryContainerImplementation <  CompteContainer   > implements CompteImplementationInterface <CompteContainer >{

	public static final CompteMP SINGLETON=new CompteMP(); 

	private  ConcurrentHashMap<String, CompteContainer> CACHE=new ConcurrentHashMap<String , CompteContainer>();
	private static final Integer INITIAL_VERSION = 1;
	private CompteMP(){
	}

	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());

		return ret;
	}

	@Override
	public List<KataCompte> findCompteByUser(Long id) {
		List<KataCompte> comptes = CACHE.values().stream()
				.filter(p -> p.compte.getKataUser().getId().equals(id))
				.map(p -> p.compte).collect(Collectors.toList());
		return comptes;
	}

	@Override
	public void update(CompteContainer container) {
		CACHE.put(container.getKey(), container);			
	}

	@Override
	public CompteContainer get(String key) {
		return CACHE.get(key);
	}


}

