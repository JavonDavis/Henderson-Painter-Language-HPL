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
 * Created on 28-Oct-2015
 * @param <E>
 */
public class ASTUnaryExp<E extends ASTExp<E>> extends ASTExp<E> {

    String operator;
    ASTExp<E> exp;

    public ASTUnaryExp(String op, ASTExp<E> exp) {
        operator = op;
        this.exp = exp;
    }

    public String getOperator() {
        return operator;
    }
    
    public ASTExp<E> getExp() {
        return exp;
    }
    
    
    @Override
    public <S, T> T visit(ASTVisitor<E, S, T> v, S state) throws HPLException {
        return v.visitUnaryExp(this, state);
    }

}
