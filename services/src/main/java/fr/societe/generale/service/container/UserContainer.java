package fr.societe.generale.service.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.societe.generale.model.KataUser;
import fr.societe.generale.service.util.Container;

public class UserContainer implements Container{
	private static Logger log = LoggerFactory.getLogger(UserContainer.class);

	public final String id;
	public final KataUser user;
	/**
	 * @param bic
	 * @param compte 
	 */
	public UserContainer(String id, KataUser user) {
		super();
		this.id = id;
		this.user = user;
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
	 * @return the user
	 */
	public KataUser getUser() {
		return user;
	}


}

