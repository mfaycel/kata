package fr.societe.generale.service.mem;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.societe.generale.model.KataOperation;
import fr.societe.generale.service.container.OperationContainer;
import fr.societe.generale.service.util.OperationImplementationInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service
public class OperationMP extends MemoryContainerImplementation <  OperationContainer   > implements OperationImplementationInterface <OperationContainer >{

	public static final OperationMP SINGLETON=new OperationMP(); 

	private  ConcurrentHashMap<String, OperationContainer> CACHE=new ConcurrentHashMap<String , OperationContainer>();
	private static final Integer INITIAL_VERSION = 1;
	private OperationMP(){
	}

	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());

		return ret;
	}


	@Override
	public List<KataOperation> findOperationsBYCompte(String bic) {
		try {
			List<KataOperation> kataOperations = CACHE.values().stream()
					.filter(p -> p.operation.getCompte().getBic().equals(bic))
					.map(p -> p.operation).collect(Collectors.toList());
			return kataOperations;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void update(OperationContainer container) {
		CACHE.put(container.getKey(), container);			
	}
	
	@Override
	public void create(OperationContainer container) {
		CACHE.put(generateRandomCellId(), container);			
	}
	@Override
	public OperationContainer get(String key) {
		return CACHE.get(key);
	}
}

