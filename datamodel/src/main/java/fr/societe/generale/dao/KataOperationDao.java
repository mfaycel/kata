//package fr.societe.generale.dao;
//
//import org.springframework.stereotype.Repository;
//
//import fr.societe.generale.dao.util.AbstractDao;
//import fr.societe.generale.model.KataOperation;
//import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
///**
// * 
// * @author mbarki
// *
// */
//@Repository("kataOperationDao")
//public class KataOperationDao  extends AbstractDao<String,KataOperation> {
//
//	@Override
//	public RuntimeCheckResult check() {
//		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());
//
//		ret.add(super.check());
//		checkQuery(ret,"select * from Kata_OPERATION where rownum <= 1 ");
//
//		return ret;
//	}
//}
