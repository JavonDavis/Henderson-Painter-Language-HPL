package hpl.lang;

import hpl.sys.*;
import hpl.values.*;

/**
 * <code>AIRExp</code> is the parent class for all arithmetic
 * expressions.  Since numbers may not be named by HPL variables, no
 * environment is needed to evaluate arithmetic expressions.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public abstract class AIRExp extends ASTExp<AIRExp> {

    public AIRExp() {
	super();
    }

    public abstract <S, T> T visit(AIRVisitor<S, T> v, S arg) throws HPLException;

    @Override
    public <S, T> T visit(ASTVisitor<AIRExp, S, T> v, S state) throws HPLException {
        // We have to delegate to the more specific visit method from here.
        return visit((AIRVisitor<S, T>) v, state);
    }
}
