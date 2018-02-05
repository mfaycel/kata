package fr.societe.generale.model.security;

import org.springframework.security.core.GrantedAuthority;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public enum Role implements GrantedAuthority
{
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String authority;

    Role(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String getAuthority()
    {
        return this.authority;
    }
}
