package hpl.lang;

import hpl.sys.*;
import hpl.values.*;

public class LogicExpCmpLog extends LogicExp {

	private ASTExp<LogicExp> logic1, logic2;
	private String operator;

	public LogicExpCmpLog(ASTExp<LogicExp> lg1, ASTExp<LogicExp> lg2,String opt)
	{
		logic1 = lg1;
		logic2 = lg2;
		operator = opt;
	}

	public LogicExpCmpLog(ASTExp<LogicExp> lg1)
	{
		logic1 = lg1;
		operator = "!";
	}

	public ASTExp<LogicExp> getLogic1()
	{
		return logic1;
	}

	public ASTExp<LogicExp> getLogic2()
	{
		return logic2;
	}

	public String getOperator()
	{
		return operator;
	}

	@Override
    public <S,T> T visit(LOGVisitor<S, T> v, S arg) throws HPLException {
		return v.visitLogicExpCmp(this, arg);
    }
}