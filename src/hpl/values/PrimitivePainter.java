package hpl.values;

import hpl.sys.HPLException;
import hpl.sys.PainterFrame;
import hpl.sys.Screen;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class PrimitivePainter extends Painter {

    protected BufferedImage image;

    /** Create a new Painter object that draws a blank screen.*/
    public PrimitivePainter() {
	image = new BufferedImage (Screen.DEFAULT_WIDTH,
				   Screen.DEFAULT_HEIGHT,
				   BufferedImage.TYPE_INT_ARGB);
    }

    /** Create a new painter from the image stored in the given file
     * @param imageFile The image file to be loaded and prepared as a painter.
     * @throws hpl.sys.HPLException if there was a problem reading the specified
     * file
     */
    public PrimitivePainter(String imageFile) throws HPLException {
	BufferedImage src;
	try {
	    src =ImageIO.read(new File(imageFile));
	} catch (IllegalArgumentException iae) {
	    throw new HPLException("File " + imageFile +
				   " not found");
	} catch (IOException ioe) {
	    throw new HPLException("Unable to read " + imageFile);
	}
	// now we need to invert the src image.
	/* define a transformation to reflect through y=0, then translate by
	   height.  xform = [ 1  0  0]
	                    [ 0 -1  height]
			    [ 0  0  1]
	 */
	int height = src.getHeight();
	AffineTransform xform = new AffineTransform(1, 0, 0, -1, 0, height);
	AffineTransformOp xformOp = new AffineTransformOp(xform, null);

	//image = xformOp.createCompatibleDestImage(src, src.getColorModel());
	image = xformOp.filter(src, null);
    }

    @Override
    public void render(Screen screen, PainterFrame frame) {
	/* draw the image represented by this painter in the given
	   rectangle on the screen. */
	screen.draw(image, frame);
    }
}
