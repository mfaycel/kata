package fr.societe.generale.dao.util;

import java.util.List;

import fr.societe.generale.model.util.Entity;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <T>
 * @param <I>
 */
public interface Dao<T extends Entity, I>
{
    List<T> findAll();

    T find(I id);

    T save(T entity);

    void delete(I id);

    void delete(T entity);
}
