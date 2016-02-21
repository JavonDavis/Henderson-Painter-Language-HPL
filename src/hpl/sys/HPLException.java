package hpl.sys;

/** Parent class for all exceptions thrown by the function calculator. */
public class HPLException extends Exception {
    private static final long serialVersionUID = 1L;

    HPLException cause;

    public HPLException() {
	super();
    }

    public HPLException(String s) {
	super(s);
    }

    public HPLException(String s, HPLException hple) {
	super(s);
	cause = hple;
    }

    public String report() {
	if (cause == null)
	    return getMessage();
	else
	    return getMessage() + " caused by " + cause.report();
    }
}
