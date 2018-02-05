package fr.societe.generale.service.util;

import java.util.List;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.util.exception.ApplicationException;

/**
 * 
 * @author mbarki
 *
 * @param <DocumentContainer>
 */
public interface CompteImplementationInterface<CompteContainer> extends AbstractServiceImplementationInterface<CompteContainer>{
	
	void update(CompteContainer container ) throws ApplicationException;

	List<KataCompte> findCompteByUser(Long id);

	
}
