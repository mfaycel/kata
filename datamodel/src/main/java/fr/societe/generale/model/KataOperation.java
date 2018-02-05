package fr.societe.generale.model;

import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import fr.societe.generale.model.converter.OperationTypeEnumConverter;
import fr.societe.generale.model.def.OperationTypeEnum;
import fr.societe.generale.model.util.AbstractIdModel;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Entity
@Table(name = "KATA_OPERATION")
public class KataOperation extends AbstractIdModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = "$Id: TrilAdministrators.java 1350 2017-10-24 13:26:45Z fmbarki $".hashCode();

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "AMOUNT", nullable = false)
	private float amount;

	@Column(name = "DATE_EXCHANGE", nullable = false)
	private Date dateExcahnge;
	
	@Convert(converter = OperationTypeEnumConverter.class)
	@Column(name = "OPERATION_TYPE", nullable = false)
	private OperationTypeEnum operationType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BIC", nullable = false ,insertable = false , updatable = false)
	private KataCompte compte;
	
	/**
	 *  
	 */
	public KataOperation() {
		super();
	}

	/**
	 * @param id
	 * @param amount
	 * @param dateExcahnge
	 * @param operationType
	 * @param compte 
	 */
	public KataOperation(String id, float amount, Date dateExcahnge, OperationTypeEnum operationType,
			KataCompte compte) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateExcahnge = dateExcahnge;
		this.operationType = operationType;
		this.compte = compte;
	}

	/**
	 * @param id
	 * @param amount
	 * @param dateExcahnge
	 * @param operationType
	 * @param compte 
	 */
	public KataOperation( float amount, Date dateExcahnge, OperationTypeEnum operationType,
			KataCompte compte) {
		super();
		this.id = generateRandomId();
		this.amount = amount;
		this.dateExcahnge = dateExcahnge;
		this.operationType = operationType;
		this.compte = compte;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public String getId() {
		return id;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the dateExcahnge
	 */
	public Date getDateExcahnge() {
		return dateExcahnge;
	}

	/**
	 * @param dateExcahnge the dateExcahnge to set
	 */
	public void setDateExcahnge(Date dateExcahnge) {
		this.dateExcahnge = dateExcahnge;
	}

	/**
	 * @return the operationType
	 */
	public OperationTypeEnum getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(OperationTypeEnum operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return the compte
	 */
	public KataCompte getCompte() {
		return compte;
	}

	/**
	 * @param compte the compte to set
	 */
	public void setCompte(KataCompte compte) {
		this.compte = compte;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 */
		protected String generateRandomId() {
			return getSaltString()+"-"+getSaltString();
		}
	/**
	 * random string
	 * @return
	 */
		protected String getSaltString() {
	        String SALTCHARS = "ABCDEF1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 4) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    }
}
