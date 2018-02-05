package fr.societe.generale.service.util;

import java.util.List;

import fr.societe.generale.model.KataUser;
import fr.societe.generale.service.container.UserContainer;
import fr.societe.generale.util.exception.ApplicationException;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <UsereContainer>
 */
public interface UserImplementationInterface<UsereContainer> extends AbstractServiceImplementationInterface<UserContainer>{
	
	void update(UserContainer container ) throws ApplicationException;

	List<KataUser> findAll();

	KataUser authenticate(String username, String passpord);
	
}
