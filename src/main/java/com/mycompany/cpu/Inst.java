package com.mycompany.cpu;

public class Inst {
    private String operador;
    private int op1,op2;

    public Inst(String operador, int op1, int op2) {
        this.operador = operador;
        this.op1 = op1;
        this.op2 = op2;
    }

    public String getOperador() {
        return operador;
    }

    public int getOp1() {
        return op1;
    }

    public int getOp2() {
        return op2;
    }
    
    @Override
    public String toString() {
        return (operador+" "+op1+" "+op2);
    }
    
}
