package fr.societe.generale.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.societe.generale.dao.AccessTokenDao;
import fr.societe.generale.dao.util.DaoImpl;
import fr.societe.generale.model.security.AccessToken;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Repository("accessTokenDao")
public class AccessTokenDaoImpl extends DaoImpl<AccessToken, Long> implements AccessTokenDao
{
    public AccessTokenDaoImpl()
    {
        super(AccessToken.class);
    }

    @Override
    @Transactional(readOnly = true, noRollbackFor = NoResultException.class)
    public AccessToken findByToken(String accessTokenString)
    {
        CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AccessToken> query = builder.createQuery(this.entityClass);
        Root<AccessToken> root = query.from(this.entityClass);
        query.where(builder.equal(root.get("token"), accessTokenString));

        try {
            return this.getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
