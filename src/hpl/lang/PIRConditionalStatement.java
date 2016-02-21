package hpl.lang;

import hpl.sys.HPLException;
import java.util.*;

/**
 *
 * @author Javon Davis <javon.davis@mymona.uwi.edu>
 * Created on 09-Nov-2015
 */
 
public class PIRConditionalStatement extends PIRStatement {

	protected ASTExp<LogicExp> condition;
	protected PIRSequence body1;
	protected PIRSequence body2;

	public PIRConditionalStatement(ASTExp<LogicExp> c, PIRSequence b1)
	{
		condition = c;
		body1 = b1;
	}

	public PIRConditionalStatement(ASTExp<LogicExp> c, PIRSequence b1, PIRSequence b2)
	{
		condition = c;
		body1 = b1;
		body2 = b2;
	}

	public ASTExp<LogicExp> getCondition()
	{
		return condition;
	}

	public PIRSequence getBody1()
	{
		return body1;
	}

	public PIRSequence getBody2()
	{
		return body2;
	}


	@Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitPIRConditionStmt(this, state);
    }

}