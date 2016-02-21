/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLException;

/**
 * Placeholder class to represent a generic expression.  
 * Should never be instantiated directly, instead one of the subclasses (e.g.
 * AIRExp or LIRExp would be instantiated instead).
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 28-Oct-2015
 * @param <E> The type of subexpressions expected in this expression
 */
public abstract class ASTExp<E extends ASTExp<E>> extends ASTNode {
    
    /**
     * Call the appropriate visit... method in the visitor with this
     * object and the given argument.  In general, each visitor might
     * need to use different data to flow up and down the tree.
     *
     
     * @param <S> The type of state that the visitor requires
     * @param <T> The return type of the visitor
     * @param v The visitor to be used to visit this node (statement)
     * @param state The state needed by the visitor
     * @return The return value of the visitor calling its method for visiting
     * statements.
     * @throws HPLException if the visitor encounters an error
     */
    public abstract <S, T> T visit (ASTVisitor<E, S, T> v, S state) throws HPLException;

}
