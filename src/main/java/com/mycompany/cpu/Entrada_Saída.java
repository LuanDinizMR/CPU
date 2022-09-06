package com.mycompany.cpu;

import java.util.Scanner;

/**
 * Classe que adicionará as intruções ao ciclo e que dará o comando para o ciclo ser executado.
 * @author Luan Diniz MR
 */
public class Entrada_Saída {
    //entrada de informações
    public static void main(String[] args) {
        Processador cpu = new Processador();
        Scanner ent = new Scanner(System.in);
        String operador, op1, op2;
        int esc;
        while (true){
            System.out.println("[1]Inserir Instrução:\n[2]Executar Ciclo de Instrução e Sair.");
            esc = ent.nextInt();
            if (esc==1){
                System.out.println("Operador: ");
                operador = ent.next();
               System.out.println("Operando1:");
                op1 = ent.next();
                System.out.println("Operando2(Caso não haja digite 0):");
                op2 = ent.next();
                Inst inst = new Inst(operador,op1,op2);
                cpu.adcInst(inst);
            }
            else if (esc==2){
                cpu.cicloInst();
                System.out.println("Saindo...");
                break;
            }
            else{
                System.out.println("Digite[1] ou [2]");
            }
        }
    }
}
