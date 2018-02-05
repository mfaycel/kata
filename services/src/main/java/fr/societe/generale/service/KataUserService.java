package fr.societe.generale.service;

import java.util.List;

import fr.societe.generale.model.KataUser;
import fr.societe.generale.util.exception.ApplicationException;
import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public interface KataUserService extends RuntimeCheckInterface{

	List<KataUser> findAllUsers();

	KataUser authenticate(String username, String username2);

	KataUser getById(String id) throws ApplicationException;

	void initForTest() throws ApplicationException;
	

}