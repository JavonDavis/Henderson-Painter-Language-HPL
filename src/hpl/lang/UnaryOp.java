/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

/**
 * An interface to capture the behaviour of a generic unary operator.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <E> The operand type
 * @param <T> The return type
 */
public interface UnaryOp<E, T> {

    /**
     * @return The symbol representing this operator.
     */
    public String getSymbol();

    /**
     * Apply this operator to an operand.
     * @param arg The operand of the operator.
     * @return The result of the operation on the operand.
     */
    public T apply(E arg);
}
