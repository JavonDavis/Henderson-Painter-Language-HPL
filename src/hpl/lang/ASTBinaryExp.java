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
 * @param <E> The type of subexpression expected in this binary operation expression.
 */
public class ASTBinaryExp<E extends ASTExp<E>> extends ASTExp<E> {
    
    String operator;
    ASTExp<E> exp1;
    ASTExp<E> exp2;

    public ASTBinaryExp(String operator, ASTExp<E> exp1, ASTExp<E> exp2) {
        this.operator = operator;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public String getOperator() {
        return operator;
    }

    public ASTExp<E> getExp1() {
        return exp1;
    }

    public ASTExp<E> getExp2() {
        return exp2;
    }

    @Override
    public <S, T> T visit(ASTVisitor<E, S, T> v, S state) throws HPLException {
        return v.visitBinaryExp(this, state);
    }
}
