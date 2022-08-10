package com.mycompany.cpu;

import java.util.Scanner;

/**
 *
 * @author Luan Diniz MR
 */
public class Entrada_Saída {
    //entrada de informações
    public static void main(String[] args) {
        Processador cpu = new Processador();
        Scanner ent = new Scanner(System.in);
        String operador;
        int op1, op2,esc;
        while (true){
            System.out.println("[1]Inserir Instrução:\n[2]Sair.");
            esc = ent.nextInt();
            if (esc==1){
                System.out.println("Operador: ");
                operador = ent.next();
                System.out.println("Operando1:");
                op1 = ent.nextInt();
                System.out.println("Operando2(Caso não haja digite 0):");
                op2 = ent.nextInt();
                Inst inst = new Inst(operador,op1,op2);
                cpu.adcInst(inst);
            }
            else if (esc==2){
                System.out.println("Saindo...");
                break;
            }
            else{
                System.out.println("Digite[1] ou [2]");
            }
        }
    }
}
