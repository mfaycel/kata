package fr.societe.generale.service;

import java.util.List;

import fr.societe.generale.model.KataOperation;
import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public interface OperationService extends RuntimeCheckInterface{

	List<KataOperation> findOperationsBYCompte(String bic);


	void create(KataOperation kataOperation);

	

}