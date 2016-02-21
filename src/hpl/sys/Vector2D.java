package hpl.sys;

import java.awt.geom.*;

/** 
 * Column vector representation of a 2D vector. 
 */
public class Vector2D extends Point2D.Double {
    private static final long serialVersionUID = 1L;

    public Vector2D(double x, double y) {
	super(x, y);
    }
    
    public Vector2D(Point2D pt) {
        this(pt.getX(), pt.getY());
    }

    /** 
     * Return the vector sum of this column vector and the given one. 
     * @param v The point to be added to this vector
     * @return The resultant vector
     */
    public Vector2D add(Point2D v) {
	return new Vector2D(getX() + v.getX(),
			    getY() + v.getY());
    }

    /**
     * Compute the vector difference of this vector and the given one. 
     * @param v The point to be subtracted from this vector.
     * @return the resultant vector
     */
    public Vector2D sub(Point2D v) {
	return new Vector2D(getX() - v.getX(),
			    getY() - v.getY());
    }

    /** 
     * Compute the negative of this column vector. 
     * @return the resultant vector
     */
    public Vector2D negate() {
	return new Vector2D(- getX(), - getY());
    }

    /**
     * Return the result of scaling this vector by the given scalar s.
     *
     * @param s a <code>double</code> value
     * @return a <code>Vector2D</code> value
     */
    public Vector2D scale(double s) {
	return new Vector2D(s * getX(), s * getY());
    }

    
    /**
     * Return the dot product of this vector and the given vector v.
     *
     * @param v a <code>Vector2D</code> value
     * @return a <code>double</code> value
     */
    public double dot(Point2D v) {
	return (getX() * v.getX() + getY() * v.getY());
    }
}
