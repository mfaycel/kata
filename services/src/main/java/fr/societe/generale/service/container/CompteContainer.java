package fr.societe.generale.service.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mbarki
 *
 */
import fr.societe.generale.model.KataCompte;
import fr.societe.generale.service.util.Container;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class CompteContainer implements Container{
	private static Logger log = LoggerFactory.getLogger(CompteContainer.class);

	public final String bic;
	public final KataCompte compte;
	
	/**
	 * @param bic
	 * @param compte 
	 */
	public CompteContainer(String bic, KataCompte compte) {
		super();
		this.bic = bic;
		this.compte = compte;
	}
	@Override
	public String getKey() {
		return bic;
	}
	/**
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}
	/**
	 * @return the compte
	 */
	public KataCompte getCompte() {
		return compte;
	}

	
}

