package fr.societe.generale.service;

import java.util.List;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.model.def.OperationTypeEnum;
import fr.societe.generale.util.exception.ApplicationException;
import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public interface CompteService extends RuntimeCheckInterface{

	void initForTest(KataCompte c) throws ApplicationException;

	KataCompte getById(String bic) throws ApplicationException;

	Boolean addOperation(String bic, OperationTypeEnum operationTypeEnum, Float montant) throws ApplicationException;

	List<KataCompte> findCompteByUser(Long id);
	

}