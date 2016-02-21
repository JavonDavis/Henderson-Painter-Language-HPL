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
 * Created on 25-Oct-2015
 */
public class PIRPaintStmt extends PIRStatement {

    ASTExp<PIRExp> painterExp;
    PIRFrameExp frameExp;

    public PIRPaintStmt(ASTExp<PIRExp> painterExp, PIRFrameExp frameExp) {
        this.painterExp = painterExp;
        this.frameExp = frameExp;
    }
    
    public PIRPaintStmt(ASTExp<PIRExp> painterExp) {
        this.painterExp = painterExp;
        // we create a frame that is equivalent to the identity transformation.
        AIRExpFrac zero = new AIRExpFrac(0D);
        AIRExpFrac one = new AIRExpFrac(1D);
        this.frameExp = new PIRFrameExp(zero, zero, one, zero, zero, one);
    }

    public ASTExp<PIRExp> getPainterExp() {
        return painterExp;
    }

    public PIRFrameExp getFrameExp() {
        return frameExp;
    }
    
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitPIRPaintStmt(this, state);
    }

}
