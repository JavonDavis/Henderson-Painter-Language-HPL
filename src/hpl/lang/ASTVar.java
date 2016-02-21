/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLException;

/**
 * A generic variable.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 28-Oct-2015
 * @param <E> The type for which this variable is intended to represent.
 */
public class ASTVar<E extends ASTExp<E>> extends ASTExp<E> {
    private final String id;

    public ASTVar(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public <S, T> T visit(ASTVisitor<E, S, T> v, S state) throws HPLException {
        return v.visitVar(this, state);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
