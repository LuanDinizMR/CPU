package com.mycompany.cpu;
/**
 * Classe de Instruções, serve para criar o construtor, pode tambem ser um vetor String de 3 posições.
 * @author Luan Diniz MR
 */
public class Inst {
    private String operador, op1,op2;

    public Inst(String operador, String op1, String op2) {
        this.operador = operador;
        this.op1 = op1;
        this.op2 = op2;
    }

    public String getOperador() {
        return operador;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }
    
    @Override
    public String toString() {
        return (operador+" "+op1+" "+op2);
    }
    
}
