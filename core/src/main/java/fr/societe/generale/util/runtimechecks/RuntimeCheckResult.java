package fr.societe.generale.util.runtimechecks;

import java.util.Vector;

import fr.societe.generale.util.enumeration.ExceptionUtils;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
final public class RuntimeCheckResult {

	private final String name;

	private boolean isError = false;

	private String errorMessage;

	private Vector<RuntimeCheckResult> childs = new Vector<RuntimeCheckResult>();
	private Throwable exception = null;;

	/**
	 * <p>check.</p>
	 *
	 * @param systemName a {@link java.lang.String} object.
	 * @param system a {@link ep.triloedi.util.runtimechecks.RuntimeCheckInterface} object.
	 * @return a {@link fr.societe.generale.util.runtimechecks.RuntimeCheckResult} object.
	 */
	public static RuntimeCheckResult check(String systemName, RuntimeCheckInterface system) {
		RuntimeCheckResult ret = new RuntimeCheckResult(systemName);

		if (system == null) {
			ret.addError("The system to test:" + systemName + " is null.");
		} else {
			ret.add(system.check());
		}

		return ret;
	}

	/**
	 * <p>Constructor for RuntimeCheckResult.</p>
	 *
	 * @param nombreDelSistema a {@link java.lang.String} object.
	 */
	public RuntimeCheckResult(String nombreDelSistema) {
		this.name = nombreDelSistema;
	}

	/**
	 * <p>Constructor for RuntimeCheckResult.</p>
	 *
	 * @param clazz a {@link java.lang.Class} object.
	 */
	public RuntimeCheckResult(Class<?> clazz) {
		this.name = clazz.getName();
	}

	/**
	 * <p>Constructor for RuntimeCheckResult.</p>
	 *
	 * @param clazz a {@link java.lang.Class} object.
	 * @param version a {@link java.lang.String} object.
	 */
	public RuntimeCheckResult(Class<?> clazz,String version) {
		this.name = clazz.getName()+" ["+version+"]";
	}

	/**
	 * <p>add.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param system a {@link ep.triloedi.util.runtimechecks.RuntimeCheckInterface} object.
	 */
	public void add(String name, RuntimeCheckInterface system) {
		if (system == null) {
			addError("The system to check:" + name + ", is null.");
		} else {
			add(system.check());
		}
	}

	/**
	 * <p>addError.</p>
	 *
	 * @param string a {@link java.lang.String} object.
	 */
	public void addError(String string) {
		this.errorMessage = string;
		this.exception = null;
		this.isError = true;
	}

	/**
	 * <p>addError.</p>
	 *
	 * @param string a {@link java.lang.String} object.
	 * @param e a {@link java.lang.Throwable} object.
	 */
	public void addError(String string, Throwable e) {
		this.errorMessage = string;
		this.exception = e;
		this.isError = true;
	}

	/**
	 * <p>add.</p>
	 *
	 * @param test a {@link fr.societe.generale.util.runtimechecks.RuntimeCheckResult} object.
	 */
	public void add(RuntimeCheckResult test) {
		if (test.isError == true) {
			this.isError = test.isError;
		}
		this.childs.add(test);
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		toString(buffer, "");
		return buffer.toString();
	}

	private void toString(StringBuilder buffer, String level) {
		buffer.append(level);
		buffer.append(name);
		buffer.append(": ");
		if (isError) {
			buffer.append("ERROR");
		} else {
			buffer.append("OK");
		}
		buffer.append('\n');

		if (errorMessage != null || exception != null) {
			buffer.append(level);
			buffer.append(errorMessage);
			buffer.append(ExceptionUtils.formatException(exception));
			buffer.append("\n");
		}

		for (RuntimeCheckResult test : childs) {
			test.toString(buffer, level + "  --");
		}
	}

	/**
	 * <p>isOK.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isOK() {
		return !isError;
	}

}
