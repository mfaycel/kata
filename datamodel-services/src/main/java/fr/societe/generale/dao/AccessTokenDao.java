package fr.societe.generale.dao;

import fr.societe.generale.dao.util.Dao;
import fr.societe.generale.model.security.AccessToken;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public interface AccessTokenDao extends Dao<AccessToken, Long>
{
    AccessToken findByToken(String accessTokenString);
}
