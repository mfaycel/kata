package fr.societe.generale.model.converter;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

import fr.societe.generale.util.enumeration.AppEnum;
/**
 * 
 * @author mbarki
 *
 * @param <T>
 */
public abstract class AbstractAppEnumConverter<T extends AppEnum> implements AttributeConverter<T, String> {

	@Override
	public String convertToDatabaseColumn(T appEnum) {
		if (appEnum==null){
			return null;
		} else {
			return appEnum.getId();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T convertToEntityAttribute(String value) {
		if (StringUtils.isBlank(value)){
			return null;
		} else {
			return  (T)AppEnum.value(getEnumClass(), value,getDefaultValue());
		}
	}

	protected abstract T getDefaultValue();
	protected abstract Class<T> getEnumClass();

}
