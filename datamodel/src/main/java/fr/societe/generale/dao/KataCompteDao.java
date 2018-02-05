//package fr.societe.generale.dao;
//
//import org.springframework.stereotype.Repository;
//
//import fr.societe.generale.dao.util.AbstractDao;
//import fr.societe.generale.model.KataCompte;
//import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
///**
// * 
// * @author mbarki
// *
// */
//@Repository("kataCompteDao")
//public class KataCompteDao  extends AbstractDao<String,KataCompte> {
//
//	@Override
//	public RuntimeCheckResult check() {
//		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());
//
//		ret.add(super.check());
//		checkQuery(ret,"select * from Kata_Compte where rownum <= 1 ");
//
//		return ret;
//	}
//}
