package fr.societe.generale.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.model.KataOperation;
import fr.societe.generale.model.def.OperationTypeEnum;
import fr.societe.generale.service.CompteService;
import fr.societe.generale.service.OperationService;
import fr.societe.generale.service.container.CompteContainer;
import fr.societe.generale.service.mem.CompteMP;
import fr.societe.generale.service.util.AbstractCacheService;
import fr.societe.generale.service.util.AbstractServiceImplementationInterface;
import fr.societe.generale.service.util.CompteImplementationInterface;
import fr.societe.generale.util.exception.ApplicationException;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Service("compteService")
public class CompteServiceImpl extends AbstractCacheService<CompteContainer> implements CompteService{
	@Autowired
	OperationService operationService;

	private static CompteImplementationInterface<CompteContainer> impl;

	CompteServiceImpl(){
		/*activate memory*/
		this.impl=CompteMP.SINGLETON;
	}

	@Override
	protected AbstractServiceImplementationInterface<CompteContainer> getImpl() {
		return impl;
	}

	@Override
	protected String getSvnHeaderString() {
		return "SVN_HEADER_STRIG";
	}

	@Override
	public void initForTest(KataCompte c) throws ApplicationException {
		impl.update(new CompteContainer(c.getBic(), c));

	}

	@Override
	public KataCompte getById(String bic) throws ApplicationException {
		CompteContainer c = impl.get(bic);
		if(c!=null && c.getCompte()!=null){
			c.getCompte().setLiKataOperations(operationService.findOperationsBYCompte(bic));
			return c.getCompte();
		}
		return null;
	}

	@Override
	@Transactional //should be transactionel 
	public Boolean addOperation(String bic, OperationTypeEnum operationTypeEnum, Float montant) throws ApplicationException {
		CompteContainer c = impl.get(bic);
		if(c!=null && c.getCompte()!=null){
			KataCompte kataCompte = c.getCompte();
			if(OperationTypeEnum.WITHDRAWAL.equals(operationTypeEnum)){
				if(kataCompte.getBalance()>montant){
					kataCompte.setBalance(kataCompte.getBalance()-montant);
					impl.update(new CompteContainer(bic,kataCompte));
					operationService.create(new KataOperation( montant, new Date(), OperationTypeEnum.WITHDRAWAL, kataCompte));
				}else{
					return false;
				}
			} else if(OperationTypeEnum.DEPOSIT.equals(operationTypeEnum)){
				kataCompte.setBalance(kataCompte.getBalance()+montant);
				impl.update(new CompteContainer(bic,kataCompte));
				operationService.create(new KataOperation( montant, new Date(), OperationTypeEnum.DEPOSIT, kataCompte));
				
			}
			return true;
		}
		return false;
	}

	@Override
	public List<KataCompte> findCompteByUser(Long id) {
		return impl.findCompteByUser( id);
	}






}
