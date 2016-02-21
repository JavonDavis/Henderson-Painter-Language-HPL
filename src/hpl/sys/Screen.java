package hpl.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Screen extends JPanel implements ComponentListener {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    private static final long serialVersionUID = 1L;

    protected int width;
    protected int height;

    /* The display cache stores the result of each draw (painter rendering) */
    private BufferedImage cache; // display cache
    private PainterFrame parentFrame; // top level frame for this screen.
    private AffineTransform user2cache; // xform from cache to device coords

    /**
     * Creates a new <code>Screen</code> instance with default width
     * and height and based on a default painter frame
     *
     */
    public Screen() {
	this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Creates a new <code>Screen</code> instance with the given
     * dimensions, and a default parent frame that has its origin at
     * the bottom left corner and its u and v vectors aligned with the
     * display's x and y axes, with magnitudes w and h respectively.
     *
     * @param w the width of the screen
     * @param h the height of the screen
     */
    public Screen(int w, int h) {
	this(new PainterFrame(1, ((double) h)/w), w);
    }

    /** Create a new Screen with the given dimensions to contain the
     * given top level frame. 
     * @param parent
     * @param magnification
     */
    public Screen(PainterFrame parent, double magnification) {
	super();
	// see code/comments in updateCacheTransform for details of following 
	Vector2D u = parent.getU();
	Vector2D v = parent.getV();
	double w = u.getX() + v.getX();
	double h = u.getY() + v.getY();
	width = (int) (w * magnification);
	height = (int) (h * magnification);

	setPreferredSize(new Dimension(width, height));
	clear();
	parentFrame = parent;
	updateCacheTransform();
    }

    /**
     *
     * @return the top level frame of this screen.
     */
    public PainterFrame getFrame() {
	return parentFrame;
    }

    /**
     * Set the parent level frame of this screen.
     *
     * @param frame the frame to be assigned to the screen.
     */
    public void setFrame(PainterFrame frame) {
	parentFrame = frame;
	updateImageCache();
    }


    /** 
     * Draw the given image (from a painter) in the given frame on
     * this screen. 
     * @param img The image to be drawn
     * @param frame The frame w.r.t. which it should be drawn
     */
    public void draw(BufferedImage img, PainterFrame frame) {
	Graphics2D g2 = cache.createGraphics();
	/* frame's transform takes (u,v) coordinates and produces user
	 * coordinates */

	/* create transform that maps image to frame, then frame to
	 * user space, then finally to the cache image device
	 * coordinates */
	AffineTransform img2cache;
	// img --> frame : scale image coordinates to range [0,1] x [0,1]
	img2cache = AffineTransform.getScaleInstance(1D/img.getWidth(),
						     1D/img.getHeight());
	// frame --> user: map by frame's transformation
	img2cache.preConcatenate(frame.getTransform()); // frame --> user
	// user --> cache: user coordinates --> image device coordinates
	img2cache.preConcatenate(user2cache);

	// render img to screen cache
	g2.drawImage(img, img2cache, null);
	paintComponent(getGraphics());
	//setIcon(new ImageIcon(cache));
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(cache, null, null);
    }

    /**
     * Clear this screen.
     */
    public void clear() {
	cache = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /* Methods to support in the ComponentListener Interface. */

    @Override
    public void componentResized(ComponentEvent ce){
	width = getWidth();
	height = getHeight();
	updateImageCache();
    }

    @Override
    public void componentShown(ComponentEvent ce) {
	width = getWidth();
	height = getHeight();
	if (width != cache.getWidth() || height != cache.getHeight()) {
	    updateImageCache();
	}
    }
    
    @Override
    public void componentMoved(ComponentEvent ce) {}
    @Override
    public void componentHidden(ComponentEvent ce) {}

    /**
     * Update the cached image to be the current size of the screen.
     * Automatically called whenever the size of the screen changes.
     *
     */
    public void updateImageCache() {
	if (width == 0 || height == 0) return;

	Image newImg = cache.getScaledInstance(width,
					       height,
					       Image.SCALE_FAST);
	clear();		// reset cache to empty image
	Graphics2D g = cache.createGraphics();
	g.drawImage(newImg, null, null);
	updateCacheTransform();
    }

    /**
     * Update the cache transformation to map to the current size of
     * the screen.  Automatically called whenever the size of the
     * screen changes.
     */
    public void updateCacheTransform() {
	Vector2D u = parentFrame.getU();
	Vector2D v = parentFrame.getV();
	/* Setup transform for cache graphics context:
	   make (0,0) in frame --> bottom left corner of cache image.
	   make (1,1) in frame --> top right corner of cache image. 
	   Note that (1,1) in frame --> (ux + vx, uy + vy) in user space

	   User space has its coordinate frame as it is in the
	   first quadrant of the Cartesian coordinate system.  The
	   cache device space, though, has its origin at the top left
	   corner, and its increasing y-axis pointing downwards.  We
	   can think of the device as performing a reflection through
	   a horizontal line the centre of the bounding rectangle.  So
	   we must transform our coordinates in user space so that
	   such a reflection yields our original coordinates.  It
	   happens that a reflection through the horizontal centre
	   line is its own inverse, so we can simply specify that
	   transformation to occur first (in user space).
	*/
	double h = (u.getY() + v.getY());
	user2cache =
	    AffineTransform.getScaleInstance(width/(u.getX() + v.getX()),
					     height/h);
	// now concatenate (to the right) a reflection through the centre line
	// reflection through the centre = reflect thru y=0 + translate (0,-1);
	user2cache.translate(0D, h); // translation by (0, 1)
	user2cache.scale(1D, -1D); // reflection through y =0
    }

}
