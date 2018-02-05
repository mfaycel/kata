//package fr.societe.generale.dao.util;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import fr.societe.generale.model.util.AbstractIdModel;
//import fr.societe.generale.util.conf.AppConfig;
//import fr.societe.generale.util.exception.ApplicationException;
//import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
//import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
///**
// * 
// * @author mbarki
// *
// * @param <IdType>
// * @param <ModelType>
// */
// public abstract class AbstractDao<IdType extends Serializable, ModelType extends AbstractIdModel<IdType>> implements RuntimeCheckInterface{
//	
//	@Autowired
//	protected SessionFactory sessionFactory;
//
//	@Autowired
//	protected GenericDao genericDao;
//	protected Class<ModelType> modelClass;
//
//	protected AbstractDao() {
//		modelClass = this.getParameterizedType(this.getClass());
//	}
//
//	/**
//	 * Generic reflection. Find and set generic type used
//	 */
//	@SuppressWarnings("unchecked")
//	private Class<ModelType> getParameterizedType(Class<?> clazz) {
//		Class<ModelType> specificType = null;
//		ParameterizedType parameterizedType = (ParameterizedType) clazz
//				.getGenericSuperclass();
//		specificType = (Class<ModelType>) parameterizedType.getActualTypeArguments()[1];
//		return specificType;
//	}
//
//	public void flush() {
//		getSession().flush();
//	}
//
//	public void clear() {
//		getSession().clear();
//	}
//
//	public IdType save(ModelType ob) throws ApplicationException{
//		try {
//			genericDao.save(ob);
//			
//			return ob.getId();
//		} catch (Exception e) {
//			throw new ApplicationException("While saving:" + ob, e);
//		}
//	}
//
//	 public void save(Collection<ModelType> collection) throws ApplicationException {
//		try {
//			genericDao.save(collection);
//		} catch (Exception e) {
//			throw new ApplicationException("While saving:" + collection, e);
//		}
//	 }
//	 public void merge(Collection<ModelType> collection) throws ApplicationException {
//		try {
//			genericDao.merge(collection);
//		} catch (Exception e) {
//			throw new ApplicationException("While saving:" + collection, e);
//		}
//	 }
//	public void delete(ModelType ob) {
//		genericDao.delete(ob);
//	}
//
//	public void delete(IdType id) {
//		genericDao.delete(modelClass, id);
//	}
//
//	public void update(ModelType ob) throws ApplicationException {
//		try {
//			genericDao.update(ob);
//		} catch (Exception e) {
//			throw new ApplicationException("While update:" + ob, e);
//		}
//	}
//
//	public void updateBatch(List<ModelType> objectList) {
//		genericDao.updateBatch(objectList);
//	}
//	public ModelType get(IdType objectId) throws ApplicationException {
//		ModelType ret=genericDao.get(modelClass, objectId);
//		if (ret==null){
//            //TODO remove this exception. This kind of method returns null and that is ok! Check JPA specification!!!!!
//			throw new ApplicationException("No object:"+objectId+" for class:"+modelClass.getName());
//		} else {
//			return ret;
//		}
//	}
//	
//	public ModelType get(IdType objectId,ModelType defaultValue) {
//		ModelType ret=genericDao.get(modelClass, objectId);
//		if (ret==null){
//			return defaultValue;
//		} else {
//			return ret;
//		}
//	}
//	
//	public List<ModelType> findListById(Collection<? extends IdType> objectIdList) {
//		return genericDao.findListById(modelClass, objectIdList);
//	}
//
//	public Map<IdType, ModelType> findMapById(Collection<? extends IdType> objectIdList) {
//		return genericDao.findMapById(modelClass, objectIdList);
//	}
//
//	public List<ModelType> findListByOrderedId(Collection<? extends IdType> objectIdList) {
//		return genericDao.findListByOrderedId(modelClass, objectIdList);
//	}
//
//	public Map<IdType, ModelType> findMapByOrderedId(Collection<? extends IdType> objectIdList) {
//		return genericDao.findMapByOrderedId(modelClass, objectIdList);
//	}
//
//	public boolean checkIfExists(IdType objectId) {
//		return genericDao.checkIfExists(modelClass, objectId);
//	}
//
//	public boolean exists(IdType objectId) {
//		return genericDao.checkIfExists(modelClass, objectId);
//	}
//
//	public int count() {
//		return genericDao.count(modelClass);
//	}
//
//	public List<ModelType> getAll() {
//		return genericDao.getAll(modelClass);
//	}
//	
//	public Query excuteQuery(String queryString) {
//		return getSession().createQuery(queryString);
//	}
//	public List<ModelType> getAll(String orderBy, boolean orderAsc) {
//		return genericDao.getAll(modelClass, orderBy, orderAsc);
//	}
//
//	public List<ModelType> getAll(String orderBy, boolean orderAsc, boolean chacheable) {
//		return genericDao.getAll(modelClass, orderBy, orderAsc, chacheable);
//	}
//
//	public Map<IdType, ModelType> getAllMap() {
//		return genericDao.getAllMap(modelClass);
//	}
//
//	public Map<IdType, ModelType> getAllMap(String orderBy, boolean orderAsc) {
//		return genericDao.getAllMap(modelClass, orderBy, orderAsc);
//	}
//
//	public Map<IdType, ModelType> getAllMap(String orderBy, boolean orderAsc,
//			boolean chacheable) {
//		return genericDao.getAllMap(modelClass, orderBy, orderAsc, chacheable);
//	}
//
//	public ModelType findByUnique(String fieldName, Serializable fieldValue) {
//		return genericDao.findByUnique(modelClass, fieldName, fieldValue);
//	}
//
//	public List<ModelType> findAllBy(String fieldName, Serializable fieldValue) {
//		return genericDao.findAllBy(modelClass, fieldName, fieldValue);
//	}
//
//	public List<ModelType> findAllByAnd(String fieldName1, Serializable fieldValue1, String fieldName2, Serializable fieldValue2) {
//		return genericDao.findAllByAnd(modelClass, fieldName1, fieldValue1, fieldName2, fieldValue2);
//	}
//	public List<ModelType> findAllByAndByAnd(String fieldName1, Serializable fieldValue1, String fieldName2, Serializable fieldValue2, String fieldName3, Serializable fieldValue3) {
//		return genericDao.findAllByAndByAnd(modelClass, fieldName1, fieldValue1, fieldName2, fieldValue2, fieldName3, fieldValue3);
//	}
//	public boolean checkIfExistsByUnique(String fieldName, Serializable fieldValue) {
//		return genericDao.checkIfExistsByUnique(modelClass, fieldName, fieldValue);
//	}
//
//	public GenericCriteria<IdType, ModelType> createCriteria() {
//		return new GenericCriteria<IdType, ModelType>(getSession().createCriteria(modelClass));
//	}
//
//	protected <IdType2 extends Serializable, ModelType2 extends AbstractIdModel<IdType2>>
//			GenericCriteria<IdType2, ModelType2> createCriteria(Class<ModelType2> modelClass2) {
//
//		return new GenericCriteria<IdType2, ModelType2>(getSession().createCriteria(modelClass2));
//	}
//
//
//
//	 protected void checkQuery(RuntimeCheckResult ret,String query) {
//		 try {
//			 Session session=getSession();
//			 try {
//				 session.createSQLQuery(query).uniqueResult();
//			 }finally {
//				 session.close();	
//			 }
//		 }catch(Throwable e){
//			 ret.addError("Exception while cheking:", e);
//		 }
//	 }
//	 
//	 @Override
//	 public RuntimeCheckResult check() {
//		 RuntimeCheckResult ret=new RuntimeCheckResult(AbstractDao.class);
//		
//		 checkQuery(ret,"Select 1 from dual");
//		return ret;
//	}
//
//	protected Session geNewtSession() {
//		if (sessionFactory == null){
//			sessionFactory=AppConfig.getBean("sessionFactory", null);
//		}
//		return  sessionFactory.openSession();
//	}
//
//	public Session getSession() {
//		if (sessionFactory == null){
//			sessionFactory=AppConfig.getBean("sessionFactory", null);
//		}
//		try {
//			return sessionFactory.getCurrentSession();
//		} catch (HibernateException e) {
//		    return  sessionFactory.openSession();
//		}
//	}
//
//	public void persist(Object entity) {
//		getSession().persist(entity);
//	}
//
//	public void merge(Object entity) {
//		getSession().merge(entity);
//	}
//	
//	public void delete(Object entity) {
//		getSession().delete(entity);
//	}
//}
