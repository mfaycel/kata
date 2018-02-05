package fr.societe.generale.model.converter;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

import fr.societe.generale.model.def.OperationTypeEnum;
/**
 * 
 * @author mbarki
 *
 */
public class OperationTypeEnumConverter implements AttributeConverter<OperationTypeEnum, String> {

	public OperationTypeEnum convertToEntityAttribute(String value) {
		if (StringUtils.isBlank(value)){
			return null;
		} else {
			OperationTypeEnum ret=OperationTypeEnum.value(value, null);
			
			if (ret==null){
				throw new RuntimeException("The value:"+value+" can not be transformed into OperationTypeEnum");
			} else {
				return ret;
			
			}
		}
	}
	
	@Override
	public String convertToDatabaseColumn(OperationTypeEnum appEnum) {
		if (appEnum==null){
			return null;
		} else {
			return appEnum.getId();
		}
	}

}
