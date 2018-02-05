//package fr.societe.generale.dao;
//
//import org.springframework.stereotype.Repository;
//
//import fr.societe.generale.dao.util.AbstractDao;
//import fr.societe.generale.model.KataUser;
//import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
///**
// * 
// * @author mbarki
// *
// */
//@Repository("kataUserDao")
//public class KataUserDao  extends AbstractDao<Long,KataUser> {
//
//	@Override
//	public RuntimeCheckResult check() {
//		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());
//
//		ret.add(super.check());
//		checkQuery(ret,"select * from Kata_user where rownum <= 1 ");
//
//		return ret;
//	}
//}
