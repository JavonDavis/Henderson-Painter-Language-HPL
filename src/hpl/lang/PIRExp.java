package hpl.lang;

import hpl.sys.HPLException;

/**
 * Abstract representation for Painter Expressions
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public abstract class PIRExp extends ASTExp<PIRExp> {

    /**
     * Visit this expression with the given visitor and visitor context.
     * @param <S> The type of the visitor context
     * @param <T> The return type of the visitor
     * @param v The visitor
     * @param context The context used by the visitor
     * @return The result of the visit
     * @throws hpl.sys.HPLException if something goes wrong while visiting.
     */
    public abstract <S, T> T visit(HPLVisitor<S, T> v, S context) throws HPLException ;
    
    @Override
    public <S, T> T visit(ASTVisitor<PIRExp, S, T> v, S state) throws HPLException {
        return visit((HPLVisitor<S, T>) v, state);
    }


}
