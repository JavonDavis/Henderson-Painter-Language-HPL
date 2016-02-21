package hpl.lang;

import hpl.sys.*;

public class AIRExpFrac extends AIRExp {

    double value;

    public AIRExpFrac(double v) {
	value = v;
    }

    public AIRExpFrac(Double v) {
	value = v.doubleValue();
    }

    public double getVal() {
	return value;
    }

    /**
     * Call the visitAIRExpFrac method within <code>v</code> on this
     * arithmetic fraction representation and the given argument.
     *
     * @param <S> The type of the state used by the visitor
     * @param <T> The return type of the visitor
     * @param v the visitor visiting this node
     * @param arg the data to be passed to the components of this
     * fraction expression
     * @return the result of visiting this fraction expression
     * @throws hpl.sys.HPLException
     */
    @Override
    public <S, T> T visit(AIRVisitor<S, T> v, S arg) throws HPLException {
	return v.visitAIRExpFrac(this, arg);
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
