/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.values;

import hpl.lang.HPLEvaluator;
import hpl.lang.PIRSequence;
import hpl.sys.HPLContext;
import hpl.sys.HPLException;
import hpl.sys.PainterFrame;
import hpl.sys.Screen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 26-Oct-2015
 */
public class CompoundPainter extends Painter {
    HPLEvaluator eval;
    PIRSequence body;
    HPLContext context;

    public CompoundPainter(HPLEvaluator eval, PIRSequence body, 
            HPLContext context) {
        this.eval = eval;
        this.body = body;
        this.context = context;
    }

    @Override
    public void render(Screen screen, PainterFrame frame) {
        // compute relative frame for context
        HPLContext newContext = context.composeFrame(frame);
        try {
            body.visit(eval, newContext);
        } catch (HPLException ex) {
            Logger.getLogger(CompoundPainter.class.getName()).log(Level.SEVERE,
                    "Error while evaluating compound painter.", ex);
        }
    }

}
