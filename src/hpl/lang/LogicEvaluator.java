package hpl.lang;

import hpl.sys.*;
import hpl.values.HPLFunction;
import java.util.*;

public class LogicEvaluator implements LOGVisitor<HPLContext, Boolean> {

    private final ArithEvaluator arithEval;
    public LogicEvaluator() {
        this.arithEval = new ArithEvaluator();
    }

    public Boolean visitLogicExpCmp(LogicExpCmpLog logic, HPLContext context) throws HPLException
    {
        ASTExp<LogicExp> logic1 = logic.getLogic1();
        ASTExp<LogicExp> logic2 = logic.getLogic2();

        String operation = logic.getOperator();

        Boolean val1 = logic1.visit(this, context);
        
        Boolean val2 = logic2 == null ? null:logic2.visit(this, context);


        return compareLOG(val1,val2,operation);
    }

    private Boolean compareLOG(boolean arg1,boolean arg2, String operation)
    {
        switch(operation)
        {
            case "&":
                return arg1 && arg2;
            case "|":
                return arg1 || arg2;
            case "!":
                return !arg1;
            default:
                throw new UnsupportedOperationException(operation+" not recognized");
        }
    }

    private Boolean compareAIR(Double arg1,Double arg2, String operation)
    {
        switch(operation)
        {
            case ">":
                return arg1 > arg2;
            case "==":
                return arg1.equals(arg2);
            case "<":
                return arg1 < arg2;
            case ">=":
                return arg1 >= arg2;
            case "<=":
                return arg1 <= arg2;
            case "!=":
                return !arg1.equals(arg2);
            default:
                throw new UnsupportedOperationException(operation+" not recognized");
        }
    }

    public Boolean visitLogicExpCmpAIR(LogicExpCmpAIR logic, HPLContext context) throws HPLException
    {
        ASTExp<AIRExp> operand1 = logic.getOperand1();
        ASTExp<AIRExp> operand2 = logic.getOperand2();
        String operator = logic.getOperator();

        Double val1 = operand1.visit(arithEval, context.getNumEnv());
        Double val2 = operand2.visit(arithEval, context.getNumEnv());

        return compareAIR(val1,val2,operator);
    }

    /**
     * Evaluate a boolean variable reference.
     * @param var The variable referencing a painter
     * @param context The context containing the environment in which to look
     * up the variable.
     * @return The painter object bound to the given variable.
     * @throws HPLException if there is no painter bound to the given variable.
     */
    @Override
    public Boolean visitVar(ASTVar<LogicExp> var, HPLContext context)
    throws HPLException {
        //cannot assign booleans
    throw new HPLException("Cannot assign booleans: ");
    }

    @Override
    public Boolean visitUnaryExp(ASTUnaryExp<LogicExp> exp, HPLContext state)
    throws HPLException  {
    // should never get here unless language changes
    throw new HPLException("Unknown unary operation applied to painter: " +
                   exp);
    }
    
    @Override
    public Boolean visitBinaryExp(ASTBinaryExp<LogicExp> exp, HPLContext state)
    throws HPLException {
    // should never get here unless language changes
    throw new HPLException("Unknown binary operation applied to painters: "+
                   exp);
    }
}
