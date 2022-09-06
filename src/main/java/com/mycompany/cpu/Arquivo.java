package com.mycompany.cpu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe para enviar informações para um arquivo
 *
 */
public class Arquivo {
    public static void Write(String Caminho,String Texto){
        try {
            FileWriter arq = new FileWriter(Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(Texto);
            gravarArq.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
