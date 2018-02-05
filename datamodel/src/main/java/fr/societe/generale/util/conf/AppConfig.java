package fr.societe.generale.util.conf;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Configuration
@ComponentScan(basePackages = "fr.societe.generale.*")
@PropertySource("classpath:application.properties")
@Resource(name="jdbc/KATA", type=javax.sql.DataSource.class, lookup="jdbc/KATA")
public class AppConfig {


	private static Logger log = LoggerFactory.getLogger(AppConfig.class);

	private static AbstractApplicationContext springContext;

	public static <T> T getBean(String beanName,T defaultValue){
		try {
			if (springContext == null){
				springContext= new AnnotationConfigApplicationContext(AppConfig.class);
			}

			if (springContext == null){
				return defaultValue;
			} else {
				@SuppressWarnings("unchecked")
				T ret=(T)springContext.getBean(beanName);

				if (ret==null){
					return defaultValue;
				} else {
					return ret;
				}
			}
		}catch (Throwable e) {
			log.error("While getting bean:"+beanName,e);
			return defaultValue;
		}	
	}


}
