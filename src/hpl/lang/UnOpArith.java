/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

/**
 * An enumeration of all unary arithmetic operations.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 28-Oct-2015
 */
public enum UnOpArith implements UnaryOp<Double, Double>{
    INC("++") {
        @Override
        public Double apply(Double arg) {
            return arg + 1;
        }
    },   // experimental examples

    DEC("--") {
        @Override
        public Double apply(Double arg) {
            return arg - 1;
        }
    },   // experimental

    NEG("-") {
        @Override
        public Double apply(Double arg) {
            return -arg;
        }
    }
    ;
    
    String symbol;

    private UnOpArith(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
