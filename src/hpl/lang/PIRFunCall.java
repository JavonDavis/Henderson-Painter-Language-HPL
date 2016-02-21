/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLException;
import java.util.ArrayList;

/**
 * ASTNode to represent function calls.  Functions in HPL+ take a (possibly
 * empty) list of painters and produce a painter.  Note that functions are
 * not themselves painters.
 * @author newts
 */
public class PIRFunCall extends PIRExp {
    private final String funName;
    private final ArrayList<ASTExp<AIRExp>> nArgExps;
    private final ArrayList<ASTExp<PIRExp>> pArgExps;
    
    public PIRFunCall(String fnName, ArrayList<ASTExp<AIRExp>> nArgs,
		      ArrayList<ASTExp<PIRExp>> pArgs) {
        funName = fnName;
        nArgExps = nArgs;
        pArgExps = pArgs;
    }

    /**
     *
     * @return The name of the function in this function application expression
     */
    public String getFunName() {
        return funName;
    }

    /**
     *
     * @return The list of numerical argument expressions in this call expression.
     */
    public ArrayList<ASTExp<AIRExp>> getNumericalArgExps() {
        return nArgExps;
    }

    /**
     *
     * @return The list of painter argument expressions in this call expression
     */
    public ArrayList<ASTExp<PIRExp>> getPainterArgExps() {
        return pArgExps;
    }
    
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitPIRFunCall(this, state);
    }
}
