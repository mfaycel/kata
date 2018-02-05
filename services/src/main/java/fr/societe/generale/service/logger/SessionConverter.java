package fr.societe.generale.service.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class SessionConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            return attrs.getSessionId();
        }
        return "NO_SESSION";
    }
}