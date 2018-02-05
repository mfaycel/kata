package fr.societe.generale.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import fr.societe.generale.model.util.AbstractIdModel;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Entity
@Table(name = "KATA_COMPTE")
public class KataCompte extends AbstractIdModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = "$Id: TrilAdministrators.java 1350 2017-10-24 13:26:45Z fmbarki $".hashCode();

	@Id
	@Column(name = "BIC", unique = true, nullable = false)
	private String bic;

	@Column(name = "BALANCE")
	private float balance;

	@Column(name = "DATE_CREATION", nullable = false)
	private Date dateCreation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USER", nullable = false ,insertable = false , updatable = false)
	private KataUser kataUser;
	
	@Transient
	private List<KataOperation> liKataOperations = null;
	
	/**
	 * @return the liKataOperations
	 */
	public List<KataOperation> getLiKataOperations() {
		return liKataOperations ==null? new ArrayList<KataOperation>():null;
	}

	/**
	 * @param liKataOperations the liKataOperations to set
	 */
	public void setLiKataOperations(List<KataOperation> liKataOperations) {
		
		this.liKataOperations = new ArrayList<KataOperation>();
		this.liKataOperations.addAll(liKataOperations) ;
	}

	/**
	 *  
	 */
	public KataCompte() {
		super();
	}

	/**
	 * @param bic
	 * @param balance
	 * @param dateCreation
	 * @param kataUser 
	 */
	public KataCompte(String bic, float balance, Date dateCreation, KataUser kataUser) {
		super();
		this.bic = bic;
		this.balance = balance;
		this.dateCreation = dateCreation;
		this.kataUser = kataUser;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public String getId() {
		return bic;
	}

	/**
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}

	/**
	 * @param bic the bic to set
	 */
	public void setBic(String bic) {
		this.bic = bic;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the kataUser
	 */
	public KataUser getKataUser() {
		return kataUser;
	}

	/**
	 * @param kataUser the kataUser to set
	 */
	public void setKataUser(KataUser kataUser) {
		this.kataUser = kataUser;
	}

}
