package fr.societe.generale.service.util;

import java.util.List;

import fr.societe.generale.model.KataOperation;
import fr.societe.generale.service.container.OperationContainer;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <OperationContainer>
 */
public interface OperationImplementationInterface<OperationContainer> extends AbstractServiceImplementationInterface<OperationContainer>{

	List<KataOperation> findOperationsBYCompte(String bic);

	void create(OperationContainer operationContainer);
	
	
	
}
