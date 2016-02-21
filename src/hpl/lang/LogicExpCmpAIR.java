package hpl.lang;

import hpl.sys.*;
import hpl.values.*;

public class LogicExpCmpAIR extends LogicExp {

	private ASTExp<AIRExp> operand1, operand2;
	private String operator;

	public LogicExpCmpAIR(ASTExp<AIRExp> op1, ASTExp<AIRExp> op2,String opt)
	{
		operand1 = op1;
		operand2 = op2;
		operator = opt;
	}

	public ASTExp<AIRExp> getOperand1()
	{
		return operand1;
	}

	public ASTExp<AIRExp> getOperand2()
	{
		return operand2;
	}

	public String getOperator()
	{
		return operator;
	}

	@Override
    public <S,T> T visit(LOGVisitor<S, T> v, S arg) throws HPLException {
		return v.visitLogicExpCmpAIR(this, arg);
    }
}