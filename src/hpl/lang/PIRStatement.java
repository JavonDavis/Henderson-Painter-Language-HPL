package hpl.lang;

import hpl.sys.*;

/**
 * Abstract Representation for Statements.  This class is actually
 * empty, but its presence allows us to add functionality specific to
 * statements at a later time without breaking the rest of the
 * language processor (interpreter or compiler).
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public abstract class PIRStatement extends ASTNode {

    // abstract class placeholder for statements

    /**
     * Call the <code>visitPIRStatement</code> method in the given visitor.
     * @param <S> The type of the state used by the visitor
     * @param <T> The return type of the visitor
     * @param v The visitor visiting this node.
     * @param state The context available to the visitor at the time it 
     * encountered this statement.
     * @return The result of calling the relevant statement visiting method of the given 
     * visitor.
     * @throws hpl.sys.HPLException     
     */
        public abstract <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException;
}
