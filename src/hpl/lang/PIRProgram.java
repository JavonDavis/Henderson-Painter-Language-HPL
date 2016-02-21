package hpl.lang;

import hpl.values.Painter;
import hpl.sys.*;

public class PIRProgram extends PIRStatement {

    protected PIRSequence statements;

    /**
     * Creates a new <code>PIRProgram</code> instance, the Painter
     * Intermediate Representation of a program.  A program is a sequence
     * of statements.
     *
     * @param stmts the list of statements making up the program.
     */
    public PIRProgram (PIRSequence stmts) {
	statements = stmts;
    }

    public PIRSequence getSeq() {
	return statements;
    }

    /**
     * Call the visitPIRProgram method within <code>v</code> on this
     * program representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param state the data to be passed to this program's components
     * @return the result of visiting this program
     * @throws hpl.sys.HPLException if an error was encountered while visiting 
     * the statements and sub-expressions of this program
     */
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
	return v.visitPIRProgram(this, state);
    }

    /**
     * Execute the instructions in this program with respect to a
     * fresh environment.
     *
     * @param interpreter The interpreter to be used to run this program
     * @return the <code>Painter</code> that results from evaluating
     * the last statement in the sequence of instructions in this
     * program.
     */
    public Painter run(HPLEvaluator interpreter) {
	try {
	    HPLContext state = interpreter.mkInitialContext();
	    visit(interpreter, state);
	    return interpreter.getResult();
	} catch (HPLException hple) {
	    System.out.println("Error encountered: " + hple.report());
	    return null;
	}
    }
}
