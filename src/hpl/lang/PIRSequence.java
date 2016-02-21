package hpl.lang;

import java.util.*;
import hpl.sys.*;

/**
 * An instance of the <code>PIRSequence</code> class is a sequence of
 * <code>PIRExp</code> objects.  Each expression representation is
 * treated as if it were a statement -- even if it yields a result.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class PIRSequence extends PIRExp {

    protected ArrayList<PIRStatement> sequence;

    /**
     * Create an empty sequence of statements
     *
     */
    public PIRSequence() {
	sequence = new ArrayList<>();
    }

    /**
     * Creates a new <code>PIRSequence</code> instance.
     *
     * @param seq an <code>ArrayList</code> value
     */
    public PIRSequence(ArrayList<PIRStatement> seq) {
	sequence = seq;
    }

    /**
     * Add an HPL statement to the end of this sequence.
     *
     * @param stmt the statement to be added.
     */
    public void addStatement(PIRStatement stmt) {
	sequence.add(stmt);
    }

    public final ArrayList<PIRStatement> getStatements() {
	return sequence;
    }

    /**
     * Call the visitPIRSequence method within <code>v</code> on this
     * sequence representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param state the data to be passed to this sequence's components
     * @return the result of visiting this sequence
     * @throws hpl.sys.HPLException
     */
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
	return v.visitPIRSequence(this, state);
    }
}

