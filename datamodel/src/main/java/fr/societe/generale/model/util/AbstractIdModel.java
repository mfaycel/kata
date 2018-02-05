package fr.societe.generale.model.util;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 * @param <IdType>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public abstract class AbstractIdModel<IdType extends Serializable> extends AbstractModel implements Serializable {


	/**
	 * retrieve the unique id
	 */
	public abstract IdType getId();
}
