//package fr.societe.generale.dao.util;
//
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.persistence.Id;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import fr.societe.generale.model.util.AbstractIdModel;
//import fr.societe.generale.util.enumeration.TechnicalException;
///**
// * 
// * @author mbarki
// *
// */
//@Repository
//public class GenericDao {
//
//	private ConcurrentHashMap<Class<? extends AbstractIdModel<?>>, Field> modelIdFieldMap =
//			new ConcurrentHashMap<Class<? extends AbstractIdModel<?>>, Field>();
//
//	@Autowired
//	protected SessionFactory sessionFactory;
//
//	public void flush() {
//		getSession().flush();
//	}
//
//	public void clear() {
//		getSession().clear();
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void save(ModelType ob) {
//		// The id of the objects is updated
//		getSession().save(ob);
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void merge(ModelType ob) {
//
//		// The id of the objects is updated
//		getSession().merge(ob);
//	}
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void save(Collection<ModelType> collection) {
//
//		int i=0;
//		for (ModelType ob:collection){
//			i++;
//			save(ob);
//
//			if ( i % 20 == 0 ) {
//				getSession().flush();
//				getSession().clear();
//			}
//		}
//	}
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void merge(Collection<ModelType> collection) {
//
//		int i=0;
//		for (ModelType ob:collection){
//			i++;
//			merge(ob);
//
//			if ( i % 20 == 0 ) {
//				getSession().flush();
//				getSession().clear();
//			}
//		}
//	}
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void delete(ModelType ob) {
//
//		getSession().delete(ob);
//	}
//
//	@SuppressWarnings("unchecked")
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void delete(Class<ModelType> modelClass, IdType id) {
//
//		Session session = getSession();
//		ModelType c = (ModelType) session.load(modelClass, id);
//		session.delete(c);
//		// Flush to get potential exception
//		session.flush();
//	}	
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void update(ModelType ob) {
//
//		getSession().update(ob);
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	void updateBatch(List<ModelType> objectList) {
//
//		if (objectList != null && objectList.size() > 0) {
//			Session session = getSession();
//			for (ModelType obj : objectList) {
//				session.update(obj);
//			}
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	ModelType get(Class<ModelType> modelClass, IdType objectId) {
//
//		ModelType c = (ModelType) getSession().get(modelClass, objectId);
//		return c;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Field getModelIdField(Class<ModelType> modelClass) {
//
//		Field idField = modelIdFieldMap.get(modelClass);
//		if (idField == null) {
//			for(Class<?> parentClass=modelClass; !parentClass.equals(Object.class); parentClass=parentClass.getSuperclass()) {
//				for(Field field : parentClass.getDeclaredFields()) {
//					Id idAno = field.getAnnotation(Id.class);
//					if (idAno != null) {
//						idField = field;
//						break;
//					}
//				}
//			}
//			if (idField != null) {
//				modelIdFieldMap.put(modelClass, idField);
//			} else {
//				throw new TechnicalException("No Id annotation in AbstractModel class "+modelClass);
//			}
//		}
//		return idField;
//	}
//
//	@SuppressWarnings("unchecked")
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> findListById(Class<ModelType> modelClass, Collection<? extends IdType> objectIdList) {
//
//		List<ModelType> list;
//		if (objectIdList == null || objectIdList.isEmpty()) {
//			list = Collections.emptyList();
//		} else {
//			String idFieldName = getModelIdField(modelClass).getName();
//			list = (List<ModelType>) getSession().createCriteria(modelClass).add(Restrictions.in(idFieldName, objectIdList)).list();
//		}
//		return list;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Map<IdType, ModelType> findMapById(Class<ModelType> modelClass, Collection<? extends IdType> objectIdList) {
//
//		List<ModelType> list = findListById(modelClass, objectIdList);
//		Map<IdType, ModelType> map = new HashMap<IdType, ModelType>(list.size());
//		for (ModelType item : list) {
//			map.put(item.getId(), item);
//		}
//		return map;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> findListByOrderedId(Class<ModelType> modelClass, Collection<? extends IdType> objectIdList) {
//
//		Map<IdType, ModelType> map = findMapById(modelClass, objectIdList);
//		List<ModelType> orderedList = new ArrayList<ModelType>(map.size());
//		for (IdType itemId : objectIdList) {
//			ModelType item = map.get(itemId);
//			if (item != null) {
//				orderedList.add(item);
//			}
//		}
//		return orderedList;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Map<IdType, ModelType> findMapByOrderedId(Class<ModelType> modelClass, Collection<? extends IdType> objectIdList) {
//
//		Map<IdType, ModelType> map = findMapById(modelClass, objectIdList);
//		Map<IdType, ModelType> orderedMap = new LinkedHashMap<IdType, ModelType>(map.size());
//		for (IdType itemId : objectIdList) {
//			ModelType item = map.get(itemId);
//			if (item != null) {
//				orderedMap.put(itemId, item);
//			}
//		}
//		return orderedMap;
//	}
//
//	@SuppressWarnings("unchecked")
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	boolean checkIfExists(Class<ModelType> modelClass, IdType objectId) {
//
//		ModelType obj = (ModelType) getSession().get(modelClass, objectId);
//		return obj != null;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	int count(Class<ModelType> modelClass) {
//
//		int count = (Integer)getSession().createCriteria(modelClass).setProjection(Projections.rowCount()).uniqueResult();
//		return count; 
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> getAll(Class<ModelType> modelClass) {
//
//		return createCriteria(modelClass).list();
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> getAll(Class<ModelType> modelClass, String orderBy, boolean orderAsc) {
//
//		return createCriteria(modelClass).setOrder(orderBy, orderAsc).list();
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> getAll(Class<ModelType> modelClass, String orderBy, boolean orderAsc, boolean chacheable) {
//
//		return createCriteria(modelClass).setOrder(orderBy, orderAsc).setCacheable(chacheable).list();
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Map<IdType, ModelType> getAllMap(Class<ModelType> modelClass) {
//
//		List<ModelType> list = createCriteria(modelClass).list();
//		Map<IdType, ModelType> map = new HashMap<IdType, ModelType>(list.size());
//		for (ModelType item : list) {
//			map.put(item.getId(), item);
//		}
//		return map;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Map<IdType, ModelType> getAllMap(Class<ModelType> modelClass, String orderBy,
//			boolean orderAsc) {
//
//		List<ModelType> list = createCriteria(modelClass).setOrder(orderBy, orderAsc).list();
//		Map<IdType, ModelType> map = new LinkedHashMap<IdType, ModelType>(list.size());
//		for (ModelType item : list) {
//			map.put(item.getId(), item);
//		}
//		return map;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	Map<IdType, ModelType> getAllMap(Class<ModelType> modelClass, String orderBy,
//			boolean orderAsc, boolean chacheable) {
//
//		List<ModelType> list = createCriteria(modelClass).setOrder(orderBy, orderAsc)
//				.setCacheable(chacheable).list();
//		Map<IdType, ModelType> map = new HashMap<IdType, ModelType>(list.size());
//		for (ModelType item : list) {
//			map.put(item.getId(), item);
//		}
//		return map;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	ModelType findByUnique(Class<ModelType> modelClass, String fieldName, Serializable fieldValue) {
//
//		ModelType item = createCriteria(modelClass).add(Restrictions.eq(fieldName, fieldValue)).uniqueResult();
//		return item;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> findAllBy(Class<ModelType> modelClass, String fieldName, Serializable fieldValue) {
//		GenericCriteria<IdType, ModelType> genericCriteria = createCriteria(modelClass).add(Restrictions.eq(fieldName, fieldValue));
//		List<ModelType> list = genericCriteria.list();
//		return list;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> findAllByAnd(Class<ModelType> modelClass, String fieldName1, Serializable fieldValue1,
//			String fieldName2, Serializable fieldValue2) {
//
//		List<ModelType> list = createCriteria(modelClass).add(Restrictions.eq(fieldName1, fieldValue1))
//				.add(Restrictions.eq(fieldName2, fieldValue2)).list();
//		return list;
//	}
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	List<ModelType> findAllByAndByAnd(Class<ModelType> modelClass, String fieldName1, Serializable fieldValue1,
//			String fieldName2, Serializable fieldValue2,String fieldName3, Serializable fieldValue3) {
//
//		List<ModelType> list = createCriteria(modelClass).add(Restrictions.eq(fieldName1, fieldValue1))
//				.add(Restrictions.eq(fieldName2, fieldValue2)).add(Restrictions.eq(fieldName3, fieldValue3)).list();
//		return list;
//	}
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	boolean checkIfExistsByUnique(Class<ModelType> modelClass, String fieldName, Serializable fieldValue) {
//
//		long nbItems = (Long) createCriteria(modelClass).add(Restrictions.eq(fieldName, fieldValue))
//				.setProjection(Projections.rowCount()).uniqueResult();
//
//		return nbItems >= 1;
//	}
//
//	public <IdType extends Serializable, ModelType extends AbstractIdModel<IdType>>
//	GenericCriteria<IdType, ModelType> createCriteria(Class<ModelType> paramClass) {
//
//		return new GenericCriteria<IdType, ModelType>(getSession().createCriteria(paramClass));
//	}
//
//	private Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//}
