package fr.societe.generale.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import fr.societe.generale.model.KataOperation;
import fr.societe.generale.service.OperationService;
import fr.societe.generale.service.container.OperationContainer;
import fr.societe.generale.service.mem.OperationMP;
import fr.societe.generale.service.util.AbstractCacheService;
import fr.societe.generale.service.util.AbstractServiceImplementationInterface;
import fr.societe.generale.service.util.OperationImplementationInterface;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service("operationService")
public class OperationServiceImpl extends AbstractCacheService<OperationContainer> implements OperationService{
	
	private static OperationImplementationInterface<OperationContainer> impl;
	
	OperationServiceImpl(){
		/*activate memory*/
		this.impl=OperationMP.SINGLETON;
	}
	
	@Override
	protected AbstractServiceImplementationInterface<OperationContainer> getImpl() {
		return impl;
	}

	@Override
	protected String getSvnHeaderString() {
		return "SVN_HEADER_STRIG";
	}

	@Override
	public List<KataOperation> findOperationsBYCompte(String bic) {
		return impl.findOperationsBYCompte(bic);
	}

	@Override
	public void create(KataOperation kataOperation) {
		impl.create(new OperationContainer(kataOperation.getId(), kataOperation));
		
	}

	
}
