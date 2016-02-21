package hpl.lang;

import hpl.sys.*;
import hpl.values.*;

public class AIRExpInt extends AIRExp {

    int value;

    public AIRExpInt(int v) {
	value = v;
    }

    public AIRExpInt(Integer v) {
	value = v.intValue();
    }

    public int getVal() {
	return value;
    }

    /**
     * Call the visitAIRExpInt method within <code>v</code> on this
     * arithmetic integer representation and the given argument.
     *
     * @param <S> The type of the state needed by the visitor
     * @param <T> The return type of the visitor
     * @param v the visitor traversing this node
     * @param arg the data to be passed to the visitor as it visits the 
     * components of this integer expression
     * @return the result of visiting this integer expression
     * @throws hpl.sys.HPLException if the visitor encounters an error
     */
    @Override
    public <S,T> T visit(AIRVisitor<S, T> v, S arg) throws HPLException {
	return v.visitAIRExpInt(this, arg);
    }
}
