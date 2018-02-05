package fr.societe.generale.service.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.societe.generale.model.KataOperation;
import fr.societe.generale.service.util.Container;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class OperationContainer implements Container{
	private static Logger log = LoggerFactory.getLogger(OperationContainer.class);

	public final String id;
	public final KataOperation operation;
	/**
	 * @param bic
	 * @param compte 
	 */
	public OperationContainer(String id, KataOperation operation) {
		super();
		this.id = id;
		this.operation = operation;
	}
	@Override
	public String getKey() {
		return id;
	}
	/**
	 * @return the bic
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the compte
	 */
	public KataOperation getOperation() {
		return operation;
	}

	
}

