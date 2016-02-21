/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLEnvironment;
import hpl.sys.HPLContext;
import hpl.sys.HPLException;
import hpl.sys.PainterFrame;
import hpl.sys.Vector2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 24-Oct-2015
 */
public class PIRFrameExp extends ASTNode {
    ASTExp<AIRExp> oxExp, oyExp;
    ASTExp<AIRExp> uxExp, uyExp;
    ASTExp<AIRExp> vxExp, vyExp;

    public PIRFrameExp(ASTExp<AIRExp> ox, ASTExp<AIRExp> oy, ASTExp<AIRExp> ux, ASTExp<AIRExp> uy, 
            ASTExp<AIRExp> vx, ASTExp<AIRExp> vy) {
        this.oxExp = ox;
        this.oyExp = oy;
        this.uxExp = ux;
        this.uyExp = uy;
        this.vxExp = vx;
        this.vyExp = vy;
    }
    
    public PainterFrame eval(HPLContext context) throws HPLException {
        ArithEvaluator aEval = new ArithEvaluator();
        HPLEnvironment<Double> env = context.getNumEnv();
        Double ox = oxExp.visit(aEval, env);
        Double oy = oyExp.visit(aEval, env);
        Double ux = uxExp.visit(aEval, env);
        Double uy = uyExp.visit(aEval, env);
        Double vx = vxExp.visit(aEval, env);
        Double vy = vyExp.visit(aEval, env);
        PainterFrame relFrame = new PainterFrame(new Point2D.Double(ox, oy), 
                                new Vector2D(ux, uy), 
                                new Vector2D(vx, vy));
        // compute the frame in absolute screen coordinates
        return context.getFrame().subFrame(relFrame);
    }
           
}
