package com.mycompany.cpu;

import java.util.ArrayList;
import java.util.List;

public class Processador {
        private List<Inst> instrucoes = new ArrayList<>();
        int[] r1 = new int [5];
        int[] r2 = new int [5];
        int[] r3 = new int [5];
        int pc=0;
        int ac=0;
        int rem = 0;//MAR
        String[] rdm;//mbr, está em string para pegar dados de tipo Inst e dados do tipo int
        Inst[] ri = new Inst[1];//ir
    public void adcInst(Inst a){
        instrucoes.add(a);//adiciona a fila de execução de instrução
        System.out.println("Adicionada");
    }
    public void cicloBusca(Inst a){
        rem = pc;
        rdm = a.toString().toLowerCase().trim().split(" ");
        ri[0] = a;
        pc ++;
    }
    public void decodifica(){
        switch(rdm[0]){
            case "add":
                ac += Integer.parseInt(rdm[1]);
            case "mov":
                
        }
        
    }
}
