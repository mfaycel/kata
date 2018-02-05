package fr.societe.generale.service.logger;

import ch.qos.logback.classic.PatternLayout;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class PatternLayoutWithUserContext extends PatternLayout {
    static {
        PatternLayout.defaultConverterMap.put(
                "user", UserConverter.class.getName());
        PatternLayout.defaultConverterMap.put(
                "session", SessionConverter.class.getName());
    }
}