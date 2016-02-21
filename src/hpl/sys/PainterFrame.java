package hpl.sys;

import java.awt.geom.*;

/** A Painter Frame is the coordinate system relative to which a
 * painter is drawn.  It consists of an origin and two vectors, u and
 * v, defining the parallelogram with respect to which the image is to
 * be drawn.  A painter paints itself inside a Painter Frame so that
 * its bottom left corner is at the origin, its bottom edge has the
 * same direction and magnitude as the vector u and its left edge has
 * the same direction and magnitude as the vector v.  Note that this
 * means that the orientation and shape of the image will change
 * according to the directions and magnitudes of the vectors u and v.
 * For example, the default frame [(0,0),(1,0),(0,1)] will cause a
 * painter to draw its image to fill the screen.  When that same
 * painter renders with respect to the frame [(1,0),(-1,0),(0,1)] the
 * image will be "flipped over from left to right" (reflected through
 * the line u=.5).

 All coordinates are to be specified in user space coordinates, in
 which the origin of the Screen is at the bottom left and top right
 corner is given coordinates (1,1).

 */
public class PainterFrame {

    protected final Point2D origin; // origin coordinates
    protected final Vector2D uAxis; // u-axis vector components
    protected final Vector2D vAxis; // v-axis vector components

    /**
     * Creates a new <code>PainterFrame</code> instance with origin
     * (0,0), u = (1,0) and v = (0, 1).
     *
     */
    public PainterFrame() {
	this(1D, 1D);
    }

    /** 
     * Create a new default Painter Frame, equivalent to a unit
     * rectangle with origin at the bottom left corner and the u and v
     * axes parallel to the Cartesian x and y axes, respectively, with
     * magnitudes w and h.
     * @param w The width of the frame.
     * @param h The height of the frame.
     */
    public PainterFrame (double w, double h) {
	origin = new Point2D.Double(0D, 0D);
	uAxis = new Vector2D(w, 0D);
	vAxis = new Vector2D(0D, h);
    }

    /** 
     * Create a new Painter Frame with the given origin and bounded
     * by the given vectors. 
     * @param o
     * @param u
     * @param v
     */
    public PainterFrame (Point2D o, Vector2D u, Vector2D v) {
	origin = o;
	uAxis = u;
	vAxis = v;
    }

    /** 
     * @return  The origin of this frame.
     */
    public Point2D getO() {
	return origin;
    }

    /** 
     * @return  The u-axis of this frame.
     */
    public Vector2D getU() {
	return uAxis;
    }

    /** 
     * @return the v-axis of this frame. 
     */
    public Vector2D getV() {
	return vAxis;
    }

    /** 
     * Compute the affine transform that maps points specified with
     * respect to this frame to user space.  The user space is
     * determined by the basis w.r.t. which the vectors u, v are
     * specified.  Frame coordinates are expressed in multiples of u
     * and v.
     * @return The affine transform representing the transformation from 
     * frame coordinates to the coordinates in which the frame's vectors were 
     * specified (user space).
     */
    public AffineTransform getTransform() {
	return new AffineTransform(uAxis.getX(), uAxis.getY(),
				   vAxis.getX(), vAxis.getY(),
				   origin.getX(), origin.getY());
    }
    
    /**
     * Transform a given frame reference into the coordinate space of this frame,
     * returning a freshly created frame as the result.
     * @param frame The frame specified in the basis vectors of this frame.
     * @return The frame described by the given frame, but in the same 
     * coordinate space as the one in which this frame's basis vectors were 
     * specified.
     */
    public PainterFrame subFrame(PainterFrame frame) {
        Point2D fO = frame.getO();
        Vector2D fU = frame.getU();
        Vector2D fV = frame.getV();
        // now scale the basis vectors by frame's coordinates
        Point2D o = uAxis.scale(fO.getX()).add(vAxis.scale(fO.getY())).add(origin);
        Vector2D u = uAxis.scale(fU.getX()).add(vAxis.scale(fU.getY()));
        Vector2D v = uAxis.scale(fV.getX()).add(vAxis.scale(fV.getY()));
        return new PainterFrame(o, u, v);
    }
}
