package hpl.lang;

import hpl.values.CompoundPainter;
import hpl.values.Painter;
import hpl.values.PrimitivePainter;
import hpl.sys.*;
import hpl.values.HPLFunction;
import java.util.*;

public class HPLEvaluator implements HPLVisitor<HPLContext, Painter> {

    private final ArithEvaluator arithEval;
    private final LogicEvaluator logicEval;
    private final PainterFrame screenFrame = new PainterFrame();
    private final Screen screen = new Screen(screenFrame, 400);
    Painter lastResult;
    private HPLContextManager manager;

    public HPLEvaluator() {
        this.arithEval = new ArithEvaluator();
        this.logicEval = new LogicEvaluator();
	lastResult = Painter.DEFAULT;
    }

    public Painter getResult() {
	return lastResult;
    }

    /**
     *
     * @return the painter screen associated with this interpreter
     */
    public Screen getScreen() {
	return screen;
    }

    /**
     * Evaluate a program, returning the lastResulting frame.
     * @param program The program to be evaluated
     * @param env The top level environment providing bindings to the program
     * @return The frame that results from evaluating the program.  This is 
 not usually a useful value, because the objective of a program is usually
 to display a Painter (ie a side effect).
     * @throws HPLException if any semantic errors are encountered during 
     * evaluation
     */
    @Override
    public Painter visitPIRProgram(PIRProgram program, HPLContext env) 
            throws HPLException {
        
	// traverse the program with the given environment
	// to obtain the resulting painter object.
        PIRSequence stmts = program.getSeq();
	Painter tmp = stmts.visit(this, env);
	// don't record null results, so that image on screen will persist
	if (tmp != Painter.DEFAULT)
	    lastResult = tmp;
	return lastResult;
    }

    /* Painter special forms */

    /**
     * Evaluate an assignment statement (creating a binding for the variable on
     * the LHS of the assignment to the value obtained from evaluating the RHS
     * of the assignment).
     * @param assignment The assignment statement
     * @param context The context in which the assignment should be evaluated
     * @return The painter value yielded by the right hand side of the assignment
     * @throws HPLException
     */
    @Override
    public Painter visitPIRAssignment(PIRAssignment assignment,
                                      HPLContext context) throws HPLException {
	Painter result = assignment.getExp().visit(this, context);
	context.putP(assignment.getVar(), result);
	return result;
    }

    /**
     * Evaluate a sequence of statements
     * @param seq The statement sequence to be evaluated.
     * @param env The environment w.r.t which the sequence is to be evaluated
     * @return The result of the last statement in the sequence.
     * @throws HPLException if an error was encountered in the sequence.
     */
    @Override
    public Painter visitPIRSequence(PIRSequence seq, HPLContext env)
	throws HPLException
    {
	ArrayList<PIRStatement> stmts = seq.getStatements();
	Painter result = Painter.DEFAULT;

	
        for (PIRStatement stmt : stmts) {
            result = stmt.visit(this, env);
        }

	return result;
    }

    /**
     * Evaluate an IMG-PAINTER statement (one that reads an image from a file)
     * @param exp An expression using the img-painter special form
     * @param env The context for evaluating the read from file
     * @return The painter object denoted by the image encoded in the file
     * @throws HPLException If the file does not exist or could not be read.
     */
    @Override
    public Painter visitPIRImagePainter(PIRImagePainter exp, HPLContext env)
	throws HPLException {
	return new PrimitivePainter(exp.getFile());
    }

    /* ---------------  Edit here for Problem 5  -------------------------  */

    /**
     * @return a freshly created global context suitable for visiting top level
     * expressions.
     */
    public HPLContext mkInitialContext() {
        manager = new HPLContextManager(screenFrame, new HPLEnvironment<HPLFunction>(),
         new HPLEnvironment<Double>(), new HPLEnvironment<Painter>());
	    return manager;
    }

    // *** Implement a method for function definition (according to your
    //     modifications to HPLVisitor interface)

    @Override
    public Painter visitPIRFunDefinition(PIRFunDefinition funDefinition, HPLContext context) throws HPLException {
        String funName = funDefinition.getFunName();
        ArrayList<String> nArithParams = funDefinition.getParams(0);
        ArrayList<String> painterParams = funDefinition.getParams(1);
        PIRSequence body = funDefinition.getBody();

        HPLFunction function = new HPLFunction(funName, nArithParams, painterParams, body, context);
        context.putF(funName, function);
    
        return new CompoundPainter(this, body, context); 
    }

    /**
     * Evaluate a function call.
     * @param funCall The function call expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return The (painter) object that arises from applying the function.
     * @throws hpl.sys.HPLException if something goes wrong while invoking the 
     * function call.
     */
    @Override
    public Painter visitPIRFunCall(PIRFunCall funCall, HPLContext context) throws HPLException {
        String funName = funCall.getFunName();
        ArrayList<ASTExp<AIRExp>> nArgExps = funCall.getNumericalArgExps();
        ArrayList<ASTExp<PIRExp>> pArgExps = funCall.getPainterArgExps();

        HPLFunction function = context.getF(funName);

        assert function != null;

        ArrayList<Double> args = new ArrayList<>();
        ArrayList<Painter> pArgs = new ArrayList<>();

        PIRSequence body = function.getBody();

	// ** Incomplete implementation **
        // evaluate the argument parameters ...
        for(ASTExp<AIRExp> arg: nArgExps)
        {
            args.add(arg.visit(arithEval, context.getNumEnv()));
        }

        for(ASTExp<PIRExp> pArg: pArgExps)
        {
            pArgs.add(pArg.visit(this,context));
        }
        
        // extend the closing environment with bindings for painter parameters
        context = context.extendP(function.getPainterParams(), pArgs);

        // also extend with bindings for numerical parameters
        context = context.extendN(function.getNumericalParams(), args);

        // and we extend with empty function frame to keep local functions local
        context = context.extendF(new ArrayList<String>(), new ArrayList<HPLFunction>());

        // now return a painter that will execute the body when rendered.
        return new CompoundPainter(this, body, context); // (** fix this **)
    }


    public Painter visitPIRConditionStmt(PIRConditionalStatement conditionStmt, HPLContext context) throws HPLException
    {
        ASTExp<LogicExp> logic = conditionStmt.getCondition();
        PIRSequence body1 = conditionStmt.getBody1();
        PIRSequence body2 = conditionStmt.getBody2();

        boolean result = logic.visit(logicEval,context);
        if(result)
        {
            return visitPIRSequence(body1,context);
        }
        else
        {
            if(body2 != null)
            {
                return visitPIRSequence(body2,context);
            }
            return new CompoundPainter(this, null, null);
        }
    }

    /* ----------------- End of Section for Problem 5 ----------------- */

    @Override
    public Painter visitPIRPaintStmt(PIRPaintStmt paintStmt, HPLContext env)
	throws HPLException {
        PIRFrameExp frameExp = paintStmt.getFrameExp();
        ASTExp<PIRExp> painterExp = paintStmt.getPainterExp();
        Painter p = painterExp.visit(this, env);
	// We cheat a little to evaluate frames by having a dedicated eval
	// method.  If there were more frame special forms, it would be better
	// to create a Frame Visitor interface, and handle it like AIRVisitor
        PainterFrame frame = frameExp.eval(env);
        p.render(screen, frame);
        return p;
    }

    @Override
    public Painter visitPIRWaitStmt(PIRWaitStmt waitStmt, HPLContext state) throws HPLException {
        ASTExp<AIRExp> durationExp = waitStmt.getDuration();
        double duration = durationExp.visit(arithEval, state.getNumEnv());
        try {
            Thread.sleep((long) duration);
            return Painter.DEFAULT;
        } catch (InterruptedException ex) {
            throw new HPLException("Interrupted while waiting");
        }
    }

    /**
     * Evaluate a painter variable reference.
     * @param var The variable referencing a painter
     * @param context The context containing the environment in which to look
     * up the variable.
     * @return The painter object bound to the given variable.
     * @throws HPLException if there is no painter bound to the given variable.
     */
    @Override
    public Painter visitVar(ASTVar<PIRExp> var, HPLContext context)
	throws HPLException {
	return context.getP(var.getId());
    }

    @Override
    public Painter visitUnaryExp(ASTUnaryExp<PIRExp> exp, HPLContext state)
	throws HPLException  {
	// should never get here unless language changes
	throw new HPLException("Unknown unary operation applied to painter: " +
			       exp);
    }
    
    @Override
    public Painter visitBinaryExp(ASTBinaryExp<PIRExp> exp, HPLContext state)
	throws HPLException {
	// should never get here unless language changes
	throw new HPLException("Unknown binary operation applied to painters: "+
			       exp);
    }
}
