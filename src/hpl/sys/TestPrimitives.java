package hpl.sys;

import hpl.values.Painter;
import hpl.values.PrimitivePainter;
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;

public class TestPrimitives {

    static Screen screen;	// display area
    static PainterFrame topLevelFrame;	// top level topLevelFrame
    static Painter result;	// painter to be drawn

    public static void main(String[] args) {
	JFrame display = new JFrame("Painter Tester");
	//display.setSize(400, 400);
	display.getContentPane().setLayout(new GridLayout(1,1));
	display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	topLevelFrame = new PainterFrame(1, 1);
	System.out.println("Created Frame...");

	screen = new Screen(topLevelFrame, 400);
	System.out.println("Created Screen...");
	
	display.getContentPane().add(screen);
	display.setSize(screen.getPreferredSize());
	display.setVisible(true);
	
	try {
	    Painter p =
		new PrimitivePainter("../images/uwicrest.jpg");
	    System.out.println("Created Image...");

            PainterFrame f;
	    // cycle through a number of demo combinations
	    while (true) {
                for (float t = 0.0F; t < 1.0F; t = t + 0.1F) {
                    Point2D origin = new Point2D.Float(t, 0F);
                    Vector2D u = new Vector2D(1- t, t);
                    Vector2D v = new Vector2D(-t, 1-t);
                    f = new PainterFrame(origin, u, v);
                    showPainter(p, f);
                    screen.clear();
                }
                
//		showPainter(CompoundPainter.rotate90(p, 1));
//		showPainter(CompoundPainter.rotate90(p, 2));
//		showPainter(CompoundPainter.rotate90(p, 3));
//		showPainter(CompoundPainter.rotate(p, 30));
//		showPainter(CompoundPainter.rotate(p, 60));
//		showPainter(CompoundPainter.beside(p, p, .5));
//		showPainter(CompoundPainter.above(p, p, .5)); 
//
//		CompoundPainter t1 = CompoundPainter.beside(p, p, .5);
//		showPainter(CompoundPainter.above(t1, t1, .5));
	    }
	} catch (HPLException hple){
	    System.out.println("Error:" + hple.getMessage());
	} // end of try-catch
    }

    private static void showPainter(Painter result, PainterFrame f) {
	try {
	    Thread.sleep(1500);
	    result.render(screen, f);
	} catch(InterruptedException ie) {
	}
    }
    
}
