/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

/**
 * An interface to capture the behaviour of a generic binary operator.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <E> The operand type
 * @param <T> The return type
 */
public interface BinaryOp<E, T> {
    public String getSymbol();
    public T apply(E leftArg, E rightArg);
}
