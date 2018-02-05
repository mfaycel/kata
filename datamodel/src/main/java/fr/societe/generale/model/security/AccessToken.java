package fr.societe.generale.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fr.societe.generale.model.KataUser;
import fr.societe.generale.model.util.Entity;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@javax.persistence.Entity
public class AccessToken implements Entity
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    private KataUser user;

    @Column
    private Date expiry;

    protected AccessToken()
    {
        /* Reflection instantiation */
    }

    public AccessToken(KataUser user, String token)
    {
        this.user = user;
        this.token = token;
    }

    public AccessToken(KataUser user, String token, Date expiry)
    {
        this(user, token);
        this.expiry = expiry;
    }

    @Override
    public Long getId()
    {
        return this.id;
    }

    public String getToken()
    {
        return this.token;
    }

    public KataUser getUser()
    {
        return this.user;
    }

    public Date getExpiry()
    {
        return this.expiry;
    }

    public boolean isExpired()
    {
        if (null == this.expiry) {
            return false;
        }

        return this.expiry.getTime() > System.currentTimeMillis();
    }
}
