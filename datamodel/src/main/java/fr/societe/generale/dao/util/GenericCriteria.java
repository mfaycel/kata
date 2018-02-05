//package fr.societe.generale.dao.util;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.hibernate.CacheMode;
//import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
//import org.hibernate.FlushMode;
//import org.hibernate.HibernateException;
//import org.hibernate.LockMode;
//import org.hibernate.ScrollMode;
//import org.hibernate.ScrollableResults;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projection;
//import org.hibernate.sql.JoinType;
//import org.hibernate.transform.ResultTransformer;
//
//import fr.societe.generale.model.util.AbstractIdModel;
///**
// * 
// * @author mbarki
// *
// * @param <IdType>
// * @param <T>
// */
//public class GenericCriteria<IdType extends Serializable, T extends AbstractIdModel<IdType>> implements Criteria {
//
//	private Criteria criteria;
//
//	public GenericCriteria(Criteria criteria) {
//		this.criteria = criteria;
//	}
//
//	public GenericCriteria<IdType, T> add(Criterion expression) {
//		criteria.add(expression);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> addOrder(Order ordering) {
//		criteria.addOrder(ordering);
//		return this;
//	}
//
//	
//	public Criteria createAlias(String associationPath, String alias, JoinType joinType) throws HibernateException {
//		criteria.createAlias(associationPath, alias, joinType);
//		return this;
//	}
//
//	public Criteria createAlias(String associationPath, String alias, JoinType joinType, Criterion withClause)
//			throws HibernateException {
//		criteria.createAlias(associationPath, alias, joinType, withClause);
//		return this;
//	}
//
//	public Criteria createCriteria(String associationPath, JoinType joinType) throws HibernateException {
//		criteria.createCriteria(associationPath, joinType);
//		return this;
//	}
//
//	public Criteria createCriteria(String associationPath, String alias, JoinType joinType) throws HibernateException {
//		criteria.createCriteria(associationPath, alias, joinType);
//		return this;
//	}
//
//	public Criteria createCriteria(String associationPath, String alias, JoinType joinType, Criterion withClause)
//			throws HibernateException {
//		criteria.createCriteria(associationPath, alias, joinType, withClause);
//		return this;
//	}
//
//	@Deprecated
//	public Criteria createAlias(String associationPath, String alias, int joinType, Criterion withClause) throws HibernateException {
//		criteria.createAlias(associationPath, alias, joinType, withClause);
//		return this;
//	}
//
//	@Deprecated
//	public GenericCriteria<IdType, T> createAlias(String associationPath, String alias, int joinType) throws HibernateException {
//		criteria.createAlias(associationPath, alias, joinType);
//		return this;
//	}
//	
//	public GenericCriteria<IdType, T> createAlias(String associationPath, String alias) throws HibernateException {
//		criteria.createAlias(associationPath, alias);
//		return this;
//	}
//
//	@Deprecated
//	public Criteria createCriteria(String associationPath, int joinType) throws HibernateException {
//		Criteria subCriteria = criteria.createCriteria(associationPath, joinType);
//		return subCriteria;
//	}
//
//	@Deprecated
//	public Criteria createCriteria(String associationPath, String alias, int joinType) throws HibernateException {
//		Criteria subCriteria = criteria.createCriteria(associationPath, alias, joinType);
//		return subCriteria;
//	}
//
//	public Criteria createCriteria(String associationPath, String arg1) throws HibernateException {
//		Criteria subCriteria = criteria.createCriteria(associationPath, arg1);
//		return subCriteria;
//	}
//
//	public Criteria createCriteria(String associationPath) throws HibernateException {
//		Criteria subCriteria = criteria.createCriteria(associationPath);
//		return subCriteria;
//	}
//
//	@Deprecated
//	public Criteria createCriteria(String associationPath, String alias, int joinType, Criterion withClause) throws HibernateException {
//		Criteria subCriteria = criteria.createCriteria(associationPath, alias, joinType, withClause);
//		return subCriteria;
//	}
//
//	public GenericCriteria<IdType, T> setCacheable(boolean cacheable) {
//		criteria.setCacheable(cacheable);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setCacheMode(CacheMode cacheMode) {
//		criteria.setCacheMode(cacheMode);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setCacheRegion(String cacheRegion) {
//		criteria.setCacheRegion(cacheRegion);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setComment(String comment) {
//		criteria.setComment(comment);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setFetchMode(String associationPath, FetchMode mode) throws HibernateException {
//		criteria.setFetchMode(associationPath, mode);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setFetchSize(int fetchSize) {
//		criteria.setFetchSize(fetchSize);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setFirstResult(int firstResult) {
//		criteria.setFirstResult(firstResult);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setFlushMode(FlushMode flushMode) {
//		criteria.setFlushMode(flushMode);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setLockMode(LockMode lockMode) {
//		criteria.setLockMode(lockMode);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setLockMode(String alias, LockMode lockMode) {
//		criteria.setLockMode(alias, lockMode);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setMaxResults(int maxResults) {
//		criteria.setMaxResults(maxResults);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setOrder(String orderBy, Boolean orderAsc) {
//		if (orderBy != null && orderBy.length()>0) {
//			if (Boolean.FALSE.equals(orderAsc)) {
//				criteria.addOrder(Order.desc(orderBy));
//			} else {
//				criteria.addOrder(Order.asc(orderBy));
//			}
//		}
//		return this;
//	}
//
//	public Criteria setProjection(Projection projection) {
//		criteria.setProjection(projection);
//		return criteria;
//	}
//
//	public GenericCriteria<IdType, T> setResultTransformer(ResultTransformer tupleMapper) {
//		criteria.setResultTransformer(tupleMapper);
//		return this;
//	}
//
//	public GenericCriteria<IdType, T> setTimeout(int timeout) {
//		criteria.setTimeout(timeout);
//		return this;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<T> list() throws HibernateException {
//		return (List<T>)criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public Map<Serializable, T> map() throws HibernateException {
//		Map<Serializable, T> map = new LinkedHashMap<Serializable, T>();
//		for (T model : (List<T>)criteria.list()) {
//			map.put(model.getId(), model);
//		}
//		return map;
//	}
//
//	@SuppressWarnings("unchecked")
//	public Set<T> set() throws HibernateException {
//		Set<T> set = new LinkedHashSet<T>(criteria.list());
//		return set;
//	}
//
//	public List<T> uniqueList() throws HibernateException {
//		List<T> list = new ArrayList<T>(set());
//		return list;
//	}
//
//	public String getAlias() {
//		return criteria.getAlias();
//	}
//
//	public ScrollableResults scroll() throws HibernateException {
//		return criteria.scroll();
//	}
//
//	public ScrollableResults scroll(ScrollMode scrollMode) throws HibernateException {
//		return criteria.scroll(scrollMode);
//	}
//
//	@SuppressWarnings("unchecked")
//	public T uniqueResult() throws HibernateException {
//		return (T)criteria.uniqueResult();
//	}
//
//	public boolean isReadOnly() {
//		return criteria.isReadOnly();
//	}
//
//	public boolean isReadOnlyInitialized() {
//		return criteria.isReadOnlyInitialized();
//	}
//
//	public Criteria setReadOnly(boolean readOnly) {
//		criteria.setReadOnly(readOnly);
//		return this;
//	}
//
//	public Criteria addQueryHint(String hint) {
//		criteria.addQueryHint(hint);
//		return this;
//	}
//}
