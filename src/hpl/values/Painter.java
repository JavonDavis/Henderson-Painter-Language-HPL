package hpl.values;

import hpl.sys.PainterFrame;
import hpl.sys.Screen;

public abstract class Painter {

    public static final Painter DEFAULT =  new PrimitivePainter();

    public void render(Screen screen) {
	render(screen, screen.getFrame());
    }

    /** Paint the image encoded by this painter into the given
     * frame.  The picture is transformed so that its lower and left edges
     * correspond exactly to the basis vectors of the given frame.
     * @param screen The display component on which to display
     * @param frame The frame for transforming this painter.  The coordinates
     * of this frame should be interpreted in the context of the standard frame.
     */
    public abstract void render(Screen screen, PainterFrame frame);
}
