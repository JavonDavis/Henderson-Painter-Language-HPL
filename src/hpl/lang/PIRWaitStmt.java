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
 */
public class PIRWaitStmt extends PIRStatement {
    
    ASTExp<AIRExp> duration;

    public PIRWaitStmt(ASTExp<AIRExp> timeExp) {
        this.duration = timeExp;
    }

    public ASTExp<AIRExp> getDuration() {
        return duration;
    }

    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitPIRWaitStmt(this, state);
    }

}
