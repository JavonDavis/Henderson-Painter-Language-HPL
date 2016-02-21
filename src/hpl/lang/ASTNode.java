package hpl.lang;

import hpl.sys.*;

public abstract class ASTNode {

    // abstract placeholder parent class of all AST nodes

//    /**
//     * Call the appropriate visit... method in the visitor with this
//     * object and the given argument.  In general, each visitor might
//     * need to use different data to flow up and down the tree.
//     *
//     * @param <S> The type of state that the visitor requires
//     * @param <T> The return type of the visitor
//     * @param v The visitor to be used to visit this node (statement)
//     * @param state The state needed by the visitor
//     * @return The return value of the visitor calling its method for visiting
//     * statements.
//     * @throws HPLException if the visitor encounters an error
//     */
//    public abstract <S, T> T visit (ASTVisitor<S, T> v, S state) throws HPLException;

    /**
     *
     * @return the printed representation of this statement
     */
    @Override
    public String toString() {
	return "no String representation defined for " + getClass();
    }
}
