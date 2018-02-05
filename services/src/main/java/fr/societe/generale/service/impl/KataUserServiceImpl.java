package fr.societe.generale.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.model.KataUser;
import fr.societe.generale.service.CompteService;
import fr.societe.generale.service.KataUserService;
import fr.societe.generale.service.container.UserContainer;
import fr.societe.generale.service.mem.UserMP;
import fr.societe.generale.service.util.AbstractCacheService;
import fr.societe.generale.service.util.UserImplementationInterface;
import fr.societe.generale.util.exception.ApplicationException;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service("kataUserService")
public class KataUserServiceImpl extends AbstractCacheService<UserContainer> implements KataUserService{
	
	@Autowired
	CompteService compteService;
	
	private static UserImplementationInterface<UserContainer> impl;
	
	KataUserServiceImpl(){
		/*activate memory*/
		this.impl=UserMP.SINGLETON;
	}
	
	@Override
	protected UserImplementationInterface<UserContainer> getImpl() {
		return impl;
	}

	@Override
	protected String getSvnHeaderString() {
		return "SVN_HEADER_STRIG";
	}

	@Override
	public List<KataUser> findAllUsers() {
		return impl.findAll();
	}

	@Override
	public KataUser authenticate(String username, String passpord) {
		
		return impl.authenticate(username,passpord);
	}

	@Override
	public KataUser getById(String id) throws ApplicationException {
		UserContainer container = getFromCache(id);
		return container ==null?null:container.getUser();
	}

	@Override
	public void initForTest() throws ApplicationException {
		KataUser kataUser1 = new KataUser((long) 1, "mbarki", "mbarki");
		KataUser kataUser2 = new KataUser((long) 2, "fmbarki", "fmbarki");
		KataUser kataUser3 = new KataUser((long) 3, "faycel", "faycel");
		KataCompte kataCompte1 = new KataCompte("bic1", 50, new Date(), kataUser1);
		KataCompte kataCompte2 = new KataCompte("bic2", 50, new Date(), kataUser2);
		KataCompte kataCompte3 = new KataCompte("bic3", 50, new Date(), kataUser3);
		
		
		UserContainer userContainre1 = new UserContainer(String.valueOf(kataUser1.getId()), kataUser1);
		UserContainer userContainre2 = new UserContainer(String.valueOf(kataUser2.getId()), kataUser2);
		UserContainer userContainre3 = new UserContainer(String.valueOf(kataUser3.getId()), kataUser3);
		impl.update(userContainre1);
		impl.update(userContainre2);
		impl.update(userContainre3);
		compteService.initForTest(kataCompte1);
		compteService.initForTest(kataCompte2);
		compteService.initForTest(kataCompte3);
		
	}

	
}
