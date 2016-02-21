package hpl.lang;

import java.util.*;
import hpl.sys.*;
import hpl.values.*;

public class PIRImagePainter extends PIRExp {

    String imageFile;

    public PIRImagePainter (String name) {
	imageFile = name;
    }

    public final String getFile() {
	return imageFile;
    }

    /**
     * Call the visitPIRPrimPainter method within <code>v</code> on this
     * read expression representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param arg the data to be passed to the components of this
     * read expression
     * @return the result of visiting this read expression
     */
    public Object visit(HPLVisitor v, Object arg) throws HPLException {
	return v.visitPIRImagePainter(this, arg);
    }
}
