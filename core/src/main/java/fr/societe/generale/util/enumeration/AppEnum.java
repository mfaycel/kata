package fr.societe.generale.util.enumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.societe.generale.util.exception.ApplicationException;



/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public abstract class AppEnum implements Serializable {
	/**
	 * This is the ID based on the file Id
	 */
	private static final long serialVersionUID = "$Id: AppEnum.java 1354 2017-10-24 15:42:19Z fmbarki $".hashCode();

	/** Constant <code>log</code> */
	public static final Logger log = LoggerFactory.getLogger(AppEnum.class);
	
	private static long lastModified=System.currentTimeMillis();

	static private final Hashtable<Class<? extends AppEnum>,List<AppEnum>> valuesList=new Hashtable<Class<? extends AppEnum>,List<AppEnum>>();

	static private final Hashtable<Class<? extends AppEnum>,Map<String,AppEnum>> valuesCache=new Hashtable<Class<? extends AppEnum>,Map<String,AppEnum>>();

	
	protected final String id;
	
	@XmlTransient
	protected final String name;
	
	@XmlTransient
	protected final String description;	
	
	private final Class<? extends AppEnum> clazz;
	
	/**
	 * <p>Constructor for AppEnum.</p>
	 *
	 * @param id a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param description a {@link java.lang.String} object.
	 * @param clazz a {@link java.lang.Class} object.
	 */
	protected AppEnum(String id,String name,String description,Class<? extends AppEnum> clazz){
		this.id=id;
		this.name=name;
		this.description=description;
		this.clazz=clazz;

		init();
	}


	/**
	 * <p>Constructor for AppEnum.</p>
	 *
	 * @param id a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param description a {@link java.lang.String} object.
	 */
	protected AppEnum(String id,String name,String description){
		this.id=id;
		this.name=name;
		this.description=description;
		this.clazz=this.getClass();
		
		init();
	}
	
	private void init(){		
		synchronized(valuesList){
			if (!valuesList.containsKey(clazz)){
				List <AppEnum>list=new ArrayList<AppEnum>();
				valuesList.put(clazz,list);
				
				Map<String,AppEnum> map=new Hashtable<String,AppEnum>();
				valuesCache.put(clazz,map);
			}
		}
		
		Map<String,AppEnum> map=valuesCache.get(clazz);
		if (!map.containsKey(id)){
			map.put(id,this);	
			
			List <AppEnum> list=valuesList.get(clazz);
			list.add(this);
		} 
		
		lastModified=System.currentTimeMillis();
	}
	
	
	/**
	 * <p>Getter for the field <code>id</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>Getter for the field <code>name</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>Getter for the field <code>description</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		return id;
	}
	
	/**
	 * <p>fromString.</p>
	 *
	 * @param clazz a {@link java.lang.Class} object.
	 * @param value a {@link java.lang.String} object.
	 * @return a {@link ep.triloedi.util.enumeration.AppEnum} object.
	 * @throws ApplicationException 
	 */
	public static AppEnum value(Class<? extends AppEnum> clazz,String value) throws ApplicationException{
		AppEnum ret=value(clazz, value, null);
		if (ret == null){
			throw new ApplicationException("Not Enum for value:"+value+" in enum:"+clazz.getName());
		} else {
			return ret;
		}
	}
	
	public static AppEnum fromString(Class<? extends AppEnum> clazz,String value) {
		AppEnum ret=value(clazz, value, null);
		if (ret == null){
			return null;
		} else {
			return ret;
		}
	}
	
	/**
	 *
	 * @param clazz The class
	 * @param value The value to look for
	 * @param defaultValule a {@link ep.triloedi.util.enumeration.AppEnum} object.
	 * @return a {@link ep.triloedi.util.enumeration.AppEnum} object.
	 */
	public static AppEnum value(Class<? extends AppEnum> clazz,String value,AppEnum defaultValule) {
		if (value == null) {
			return defaultValule;
		}
		
		value=value.trim();
		
		if (valuesCache.containsKey(clazz)){
			Map<String,AppEnum> map2=valuesCache.get(clazz);
			
			if (map2.containsKey(value)){
				return map2.get(value);
			}
		}

		return defaultValule;
	}

	/**
	 * <p>values.</p>
	 *
	 * @param clazz a {@link java.lang.Class} object.
	 * @return a {@link java.util.List} object.
	 */
	public static List<? extends AppEnum> values(Class<? extends AppEnum> clazz) {
		if (valuesList.containsKey(clazz)){
			return valuesList.get(clazz);
		} else {
			return null;
		}
	}

	/**
	 * <p>equals.</p>
	 *
	 * @param a a {@link ep.triloedi.util.enumeration.AppEnum} object.
	 * @param b a {@link ep.triloedi.util.enumeration.AppEnum} object.
	 * @return a boolean.
	 */
	public static boolean equals(AppEnum a,AppEnum b){
		if (a==null){
			if (b==null){
				return true;
			} else {
				return false;
			}
		} if (b == null){
			return false;
		} else {
			if (!StringUtils.equals(a.id, b.id)){
				return false;
			} else {
				if (a.clazz == null){
					if (b.clazz==null){
						return true;
					} else {
						return false;
					}
				} else if (b.clazz == null){
					return false;
				} else {
					return a.clazz == b.clazz;
				}
			}
		}
	}


	/**
	 * <p>Getter for the field <code>lastModified</code>.</p>
	 *
	 * @return a long.
	 */
	public static long getLastModified() {
		return lastModified;
	}
	

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj){
		if (obj==null){
			return false;
		} if (obj instanceof AppEnum){
			return AppEnum.equals(this,(AppEnum)obj);
		} else {
			return false;
		}
	}
	
	/**
	 * <p>hashCode.</p>
	 *
	 * @return a int.
	 */
	@Override
	public int hashCode(){
		return getId().hashCode();
	}
}
