package fr.societe.generale.model.def;

import java.util.List;

import fr.societe.generale.util.enumeration.AppEnum;

/**
 * 
 * @author mbarki
 *
 */
public class OperationTypeEnum extends AppEnum   {

	private static final long serialVersionUID = "$Id: OperationTypeEnum.java 1726 2017-12-22 10:57:39Z fmbarki $".hashCode();
	
	public static final OperationTypeEnum DEPOSIT=new OperationTypeEnum("DEPOSIT","Deposit in compte");
	public static final OperationTypeEnum WITHDRAWAL=new OperationTypeEnum("WITHDRAWAL","WITHDRAWAL");


	private OperationTypeEnum(String id,String name){
		super(id,name,name);
	}

	/**
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static OperationTypeEnum value(String value,OperationTypeEnum defaultValue) {
		return  (OperationTypeEnum)AppEnum.value(OperationTypeEnum.class, value,defaultValue);			
	}

	@SuppressWarnings("unchecked")
	public static List<OperationTypeEnum> values() {
		return (List<OperationTypeEnum>)AppEnum.values(OperationTypeEnum.class);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static OperationTypeEnum fromString(String value) {
		return (OperationTypeEnum)AppEnum.fromString(OperationTypeEnum.class, value);
	}
}

