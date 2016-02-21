package hpl.lang;

import hpl.sys.HPLException;
import java.util.*;

/**
 *
 * @author Daniel Coore <javon.davis@mymona.uwi.edu>
 * Created on 08-Nov-2015
 */
 
public class PIRFunDefinition extends PIRStatement {

	protected String fn;
    protected ArrayList<String> params;
    protected ArrayList<String> painterParams;
    protected PIRSequence body;

    public PIRFunDefinition(String id, ArrayList<String> arithParams, ArrayList<String> paintParams, PIRSequence b) {
		fn = id;
		params = arithParams;
		painterParams = paintParams;
        body = b;
    }

    public String getFunName()
    {
    	return fn;
    }

    public ArrayList<String> getParams(int type)
    {
    	return type == 0 ? params:painterParams;
    }

    public PIRSequence getBody()
    {
        return body;
    }

	@Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitPIRFunDefinition(this, state);
    }

}