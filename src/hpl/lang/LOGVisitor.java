package hpl.lang;

import hpl.sys.HPLException;

/**
 * An instance of <code>LOGViditor</code> will traverse an abstract
 * syntax tree build by traversing an HPL program.  Each type of AST
 * node (subclass of ASTNode) implements the <code>visit</code> method
 * by calling the specific method for its type declared below on
 * itself and whatever other argument has been passed.  The actual
 * type and meaning of this argument will depend on the function of
 * the visitor.  An interpreter will probably want to pass an
 * environment (possibly among other things), a compiler might pass a
 * symbol table as might a static checker.
 *
 * See Watt & Brown sec. 5.3.2 (pp 154--157) for a description of the
 * Visitor design patter.  This implementation deviates slightly by
 * making the method declarations non-uniform, in the interest of a
 * slight performance improvement, with little sacrifice in
 * abstraction.
 *
 * @author <a href="mailto:javon.davis@mymona.uwi.edu">Javon Davis</a>
 * @version 1.0
 * @param <S> The type of the state used by the visitor
 * @param <T> The return type of the visitor
 */
public interface LOGVisitor<S, T> extends ASTVisitor<LogicExp, S, T> {

    public T visitLogicExpCmp(LogicExpCmpLog logic, S state) throws HPLException;

    public T visitLogicExpCmpAIR(LogicExpCmpAIR logic, S state) throws HPLException;

}
