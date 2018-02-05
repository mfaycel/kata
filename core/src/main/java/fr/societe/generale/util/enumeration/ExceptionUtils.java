package fr.societe.generale.util.enumeration;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class ExceptionUtils {

	/**
	 * <p>formatException.</p>
	 *
	 * @param exception a {@link java.lang.Throwable} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String formatException(Throwable exception) {
		StringBuilder buffer = new StringBuilder();

		formatException(exception, buffer);

		return buffer.toString();
	}


	/**
	 * <p>formatException.</p>
	 *
	 * @param exception a {@link java.lang.Throwable} object.
	 * @param buffer a {@link java.lang.StringBuilder} object.
	 * @return a {@link java.lang.StringBuilder} object.
	 */
	public static StringBuilder formatException(Throwable exception,
												StringBuilder buffer) {
		
		StringBuilder ret;
        exception.printStackTrace();
		
		if (exception == null) {
			ret=buffer;
			
		} else {
			buffer.append(exception.getClass().getName());
			buffer.append(": \"");
			buffer.append(exception.getMessage());
			buffer.append("\" \n");
	
			StackTraceElement array[] = exception.getStackTrace();
	
			for (StackTraceElement element : array) {
				buffer.append("\tat ");
				printStackTraceElement(element,buffer);
				buffer.append('\n');
			}
	
			if (exception.getCause() != null) {
				buffer.append("Parent exception: ");
				ret=formatException(exception.getCause(), buffer);
			} else {
				ret=buffer;
			}
		}
		return ret;
	}
	
	/**
	 * <p>printStackTraceElement.</p>
	 *
	 * @param element a {@link java.lang.StackTraceElement} object.
	 * @param buffer a {@link java.lang.StringBuilder} object.
	 */
	public static void printStackTraceElement(StackTraceElement element,StringBuilder buffer){		
		buffer.append(element.getClassName());
		buffer.append('.');
		buffer.append(element.getMethodName());
		buffer.append('(');
		buffer.append(element.getFileName());
		if (element.getLineNumber() > 0) {
			buffer.append(':');
			buffer.append(element.getLineNumber());
		}
		buffer.append(')');
	}


	/**
	 * <p>getMessage.</p>
	 *
	 * @param exception a {@link java.lang.Throwable} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String getMessage(Throwable exception) {
		if (exception==null){
			return "null";
		} else {
			String message=exception.getMessage();
			if (message==null){
				message=exception.getClass().getName();
			}
			
			return message;
		}
	}
}
