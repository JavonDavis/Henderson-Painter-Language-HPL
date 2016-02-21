/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

import hpl.sys.HPLEnvironment;
import hpl.sys.HPLException;
import java.util.HashMap;

/**
 * An evaluator for arithmetic subexpressions of HPL programs.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 24-Oct-2015
 */
public class ArithEvaluator implements AIRVisitor<HPLEnvironment<Double>, Double> {
    
    HashMap<String, UnOpArith> unOpsMap;
    HashMap<String, BinOpArith> binOpsMap;
    
    public ArithEvaluator() {
        init();
    }
    
    private void init() {
        unOpsMap = new HashMap<>();
        for (UnOpArith op : UnOpArith.values()) {
            unOpsMap.put(op.getSymbol(), op);
        }
        
        binOpsMap = new HashMap<>();
        for (BinOpArith op: BinOpArith.values()) {
            binOpsMap.put(op.getSymbol(), op);
        }
    }
    
    /* Methods specific to Arithmetic expressions */
    @Override
    public Double visitAIRExpInt(AIRExpInt exp, HPLEnvironment<Double> env) throws HPLException {
	return new Double(exp.getVal());
    }

    @Override
    public Double visitAIRExpFrac(AIRExpFrac exp,
				  HPLEnvironment<Double> arg) throws HPLException {
	return new Double(exp.getVal());
    }
    
    /* Methods from the generic ASTVisitor interface */

    @Override
    public Double visitVar(ASTVar<AIRExp> var, HPLEnvironment<Double> state) throws HPLException {
        return state.get(var.getId());        
    }

    @Override
    public Double visitUnaryExp(ASTUnaryExp<AIRExp> exp, HPLEnvironment<Double> env) throws HPLException {
        String opName = exp.getOperator();
        UnOpArith op = unOpsMap.get(opName);
        ASTExp<AIRExp> argExp = exp.getExp();
        double arg = argExp.visit(this, env);
        return op.apply(arg);
    }

    @Override
    public Double visitBinaryExp(ASTBinaryExp<AIRExp> exp, HPLEnvironment<Double> env) throws HPLException {
        String opName = exp.getOperator();
        BinOpArith op = binOpsMap.get(opName);
        ASTExp<AIRExp> leftExp = exp.getExp1();
        ASTExp<AIRExp> rightExp = exp.getExp2();
        double leftArg = leftExp.visit(this, env);
        double rightArg = rightExp.visit(this, env);
        return op.apply(leftArg, rightArg);
    }
    
}
