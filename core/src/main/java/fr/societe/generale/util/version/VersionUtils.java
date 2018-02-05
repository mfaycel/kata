package fr.societe.generale.util.version;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class VersionUtils {
	
	/**
	 * Gets the application version from the SVN position.
	 * 
	 * @return the version
	 */
	public static String getVersion(String svnHeaderString,String defaultValue) {
        if (svnHeaderString.contains("trunk")) {
            return "trunk";
        } else if (svnHeaderString.contains("tags")) {
            int index = svnHeaderString.lastIndexOf("tags");
            String tmp = svnHeaderString.substring(index + "tags".length(), svnHeaderString.length());
            String array[] = StringUtils.split(tmp, '/');
            if (array.length > 0) {
                return array[0];
            } else {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
	}
}
