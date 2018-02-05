package fr.societe.generale.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import fr.societe.generale.model.util.AbstractIdModel;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Entity
@Table(name = "KATA_USER")
public class KataUser extends AbstractIdModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = "$Id: TrilAdministrators.java 1350 2017-10-24 13:26:45Z fmbarki $".hashCode();

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	/**
	 *  
	 */
	public KataUser() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param passpord 
	 */
	public KataUser(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the passpord
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param passpord the passpord to set
	 */
	public void setPasspord(String password) {
		this.password = password;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	

}
