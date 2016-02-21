/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLException;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <E> The type of subexpression that will be visited.
 * @param <S> The type of the context needed by this visitor
 * @param <T> The return type of the result of visiting nodes
 */
public interface ASTVisitor<E extends ASTExp<E>, S, T> {
    
    public T visitVar(ASTVar<E> var, S state) throws HPLException ;
    
    public T visitUnaryExp(ASTUnaryExp<E> exp, S state) throws HPLException ;
    
    public T visitBinaryExp(ASTBinaryExp<E> exp, S state) throws HPLException ;
    
}
