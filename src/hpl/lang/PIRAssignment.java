package hpl.lang;

import hpl.sys.*;
import hpl.values.*;

/**
 * Representation for an HPL definition, which contains the name of
 * the variable to be bound, and the expression whose value will be
 * bound to the variable in the current environment at the time of
 * evaluation.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class PIRAssignment extends PIRStatement {

    protected String var;
    protected ASTExp<PIRExp> valExp;

    public PIRAssignment(String id, ASTExp<PIRExp> exp) {
	var = id;
	valExp = exp;
    }

    public final String getVar() {
	return var;
    }

    public final ASTExp<PIRExp> getExp() {
	return valExp;
    }

    /**
     * Call the visitPIRAssignment method within <code>v</code> on this
     * assignment representation and the given argument.
     *
     * @param v the visitor visiting this node
     * @param arg the data to be passed to this assignment's components
     * @return the result of visiting this assignment
     * @throws hpl.sys.HPLException if something goes wrong while visiting the
     * assignment
     */
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S arg) throws HPLException {
	return v.visitPIRAssignment(this, arg);
    }

}
