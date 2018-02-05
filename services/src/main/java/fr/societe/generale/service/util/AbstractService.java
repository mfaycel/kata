package fr.societe.generale.service.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.societe.generale.util.runtimechecks.RuntimeCheckInterface;
import fr.societe.generale.util.runtimechecks.RuntimeCheckResult;
import fr.societe.generale.util.version.VersionUtils;



/**
 * 
 * @author mbarki
 *
 */
public abstract class AbstractService implements RuntimeCheckInterface{

	public static final Logger log = LoggerFactory.getLogger(AbstractService.class);

	private final String version;


	protected AbstractService(){		
		this.version=getVersion();
		log.info("New Instance Of "+getClass().getName()+", version:"+version);
	}
	
	
	@Override
	public RuntimeCheckResult check() {
		RuntimeCheckResult ret=new RuntimeCheckResult(getClass());
		
		check(ret);
		
		return ret;
	}

	
	/**
	 * This check the status using the RuntimeCheckResult that already contains the version.
	 *
	 * @param ret The Module Chec retuls.
	 * @return a {@link fr.societe.generale.util.runtimechecks.RuntimeCheckResult} object.
	 */
	abstract protected RuntimeCheckResult check(RuntimeCheckResult ret);

	/**
	 * The svn:keywords should be on the file of the class file: LastChangedDate LastChangedRevision LastChangedBy HeadURL Id Header
	 *
	 * @return a {@link java.lang.String} object.
	 */
	abstract protected String getSvnHeaderString();

	/**
	 * Gets the application version from the SVN position.
	 *
	 * @return the version
	 * @param svnHeaderString a {@link java.lang.String} object.
	 */
	protected String getVersion() {
		String version=null;
		if (StringUtils.isBlank(version)) {
			version=VersionUtils.getVersion(getSvnHeaderString(),"trunk");
		}

		return version;
	}

	
}
