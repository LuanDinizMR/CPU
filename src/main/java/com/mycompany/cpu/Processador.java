package com.mycompany.cpu;

import java.util.ArrayList;
import java.util.List;
/**
 * Basicamente onde tudo ocorre, é a parte onde a intrução é adicionada, onde ocorre o ciclo de busca de decodificação e execução.
 * @author Luan Diniz MR
 */
public class Processador {
        private List<Inst> instrucoes = new ArrayList<>();//Lista que será executada;
        int[] memoriaPrincipal = new int[16];//Memoria principal do computador, naturalmente ela não fica dentro do processsador, está aqui apenas para facilitar.
        boolean[] bMP = {Boolean.TRUE, Boolean.FALSE};
        String saida="";//irá receber a saída de cada instrução e enviará para o arquivo
        String endSaida = "‪Saida.txt";//local para onde será enviado as saídas do processador
        //registradores
        int h=0;
        int l=0;
        int c=0;
        int ac=0;//acumulador
        int pc=0;//contador de programa
        int rem = 0;//MAR guarda o endereço do pc e da comunicação com a memória que está sendo acessada
        String[] rdm;//mbr, está em string para pegar dados de tipo Inst e dados do tipo int
        Inst[] ri = new Inst[1];//ir Registrador de instrução
        //Registradores de Status
        int bHL=0; //irá apontar para um espaço de memória em bMP
        boolean bR,z, fAC, fCY;//bR registrador R para booleano, Z flag de zero, fAC=Flag AC e fCY=Flag CY
        
    public void adcInst(Inst a){
        instrucoes.add(a);//adiciona a fila de execução de instrução
        System.out.println("Adicionada");
    }
    public void cicloBusca(Inst a){
        rdm = a.toString().toLowerCase().trim().split(" ");
        ri[0] = a;   
    }
    private void decodifica(){
        //Aritiméticas
            //ADD
            if(rdm[0].contains("add")){
                int x=0;
                 if (rdm[1].contains("h")){
                    int acI = ac;
                    x= this.h;
                    ac += x;
                    saida += "Adicionando o valor "+Integer.toHexString(c)+" do registrador H ao Acumulador: "+Integer.toHexString(h)+"+"+Integer.toHexString(acI)+"="+Integer.toHexString(ac)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("l")){
                    int acI = ac;
                    x= this.l;
                    ac += x;
                    saida += "Adicionando o valor "+Integer.toHexString(c)+" do registrador L ao Acumulador: "+Integer.toHexString(l)+"+"+Integer.toHexString(acI)+"="+Integer.toHexString(ac)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("c")){
                    int acI = ac;
                    x= this.c;
                    ac += x;
                    saida += "Adicionando o valor "+Integer.toHexString(c)+" do registrador C ao Acumulador: "+Integer.toHexString(c)+"+"+Integer.toHexString(acI)+"="+Integer.toHexString(ac)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else{
                     saida += "Registrador não encontrado.\n";
                     Arquivo.Write(endSaida, saida);}
                }
            //ADC
            else if (rdm[0].contains("adc")){
                int acI= ac;
                ac += ac;
                saida += "Adicionando o valor "+Integer.toHexString(acI)+" do acumulador a ele mesmo: "+Integer.toHexString(acI)+"+"+Integer.toHexString(acI)+"="+Integer.toHexString(ac)+"\n";
                 Arquivo.Write(endSaida, saida);
        }
            //ADI
            else if (rdm[0].contains("adi") || rdm[0].contains("aci")){
                int x = Integer.parseInt(rdm[1]);
                int acI=ac;
                 ac += x;
                 saida += "Adicionando "+Integer.toHexString(x)+" ao acumulador. "+Integer.toHexString(x)+"+"+acI+"="+Integer.toHexString(ac)+"\n";
                 Arquivo.Write(endSaida, saida);
            }
            //Transferencia de dados:
            //MVI
            else if (rdm[0].contains("mvi")){
                 if (rdm[1].contains("h")){
                    h = Integer.parseInt(rdm[2]);
                    saida+= "Registrador H carregado com o valor "+Integer.toHexString(h)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("l")){
                    l = Integer.parseInt(rdm[2]);
                    saida+= "Registrador L carregado com o valor "+Integer.toHexString(l)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("c")){
                    c = Integer.parseInt(rdm[2]);
                    saida+= "Registrador C carregado com o valor "+Integer.toHexString(c)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("a")){
                    ac = Integer.parseInt(rdm[2]);
                    saida+= "Acumulador carregado com o valor "+Integer.toHexString(ac)+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if (rdm[1].contains("m")){
                    rem = 10;
                    memoriaPrincipal[rem] = Integer.parseInt(rdm[2]);
                    saida+= "Espaço de memória carregado com o valor "+Integer.toHexString(memoriaPrincipal[rem])+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else{
                    saida += "Registrador não ou lugar na memória não encontrado.\n";
                     Arquivo.Write(endSaida, saida);}
                }
            //MOV
            else if (rdm[0].contains("mov")){
                //H
                if (rdm[1].contains("h")){
                    if (rdm[2].contains("l")){
                        l = h;
                        saida += "Conteudo do Registrador L movido para o Registrador C\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("c")){
                        c = h;
                        saida += "Conteudo do Registrador H movido para o Registrador C\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("a")){
                        ac = h;
                        saida += "Conteudo do Registrador H movido para o Acumulador\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("m")){
                           rem = 10;
                           memoriaPrincipal[rem] = h; 
                           saida += "Conteudo do Registrador H movido para a posição de memoria 10\n";
                        Arquivo.Write(endSaida, saida);
                    }
                }
                //L
                else if (rdm[1].contains("l")){
                   if (rdm[2].contains("h")){
                        h=l;
                        saida += "Conteudo do Registrador L movido para o Registrador H\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("c")){
                        c = l;
                        saida += "Conteudo do Registrador L movido para o Registrador C\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("a")){
                        ac = l;
                        saida += "Conteudo do Registrador L movido para o Acumulador\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("m")){
                        rem = 10;
                        memoriaPrincipal[rem] = l; 
                        saida += "Conteudo do Registrador L movido para a posição de memoria 10\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    }
                //C
                else if (rdm[1].contains("c")){
                    if (rdm[2].contains("l")){
                        l = c;
                        saida += "Conteudo do Registrador C movido para o Registrador L\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("h")){
                        h = c;
                        saida += "Conteudo do Registrador C movido para o Registrador H\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("a")){
                        ac = c;
                        saida += "Conteudo do Registrador C movido para o Acumulador\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("m")){
                         rem = 10;
                         memoriaPrincipal[rem] = c;
                         saida += "Conteudo do Registrador C movido para a posição de memoria 10\n";
                        Arquivo.Write(endSaida, saida);
                    }
                }
                //Acumulador
                else if (rdm[1].contains("a")){
                    if (rdm[2].contains("l")){
                        l = ac;
                        saida += "Conteudo do Acumulador movido parao registrador L\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("c")){
                        c = ac;
                        saida += "Conteudo do Acumulador movido parao registrador C\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("h")){
                        h = ac; 
                        saida += "Conteudo do Acumulador movido parao registrador H\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("m")){
                        rem = 10;
                        memoriaPrincipal[rem] = ac; 
                        saida += "Conteudo do Acumulador movido para a posição de memoria 10\n";
                        Arquivo.Write(endSaida, saida);
                    }
                }
                //Posição 1000h da memória
                else if (rdm[1].contains("m")){
                    rem = 10;
                    if (rdm[2].contains("l")){
                        l = memoriaPrincipal[rem];
                        saida += "Conteudo da posição 10 movido para o registrador L\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("c")){
                        c = memoriaPrincipal[rem];
                        saida += "Conteudo da posição 10 movido para o registrador C\n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("a")){
                        ac = memoriaPrincipal[rem];
                        saida += "Conteudo da posição 10 movido para o Acumulador \n";
                        Arquivo.Write(endSaida, saida);
                    }
                    else if (rdm[2].contains("h")){
                        h = memoriaPrincipal[rem];
                        saida += "Conteudo da posição 10 movido para o registrador H\n";
                        Arquivo.Write(endSaida, saida);
                    }
                }
                else{
                    saida += "Registrador não ou lugar na memória não encontrado.\n";
                     Arquivo.Write(endSaida, saida);}
                }
            //Logica
            //ANA
            else if (rdm[0].contains("ana")){
                if (rdm[1].contains("r")){
                    fAC=fAC && bR;
                    fCY=false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if(rdm[1].contains("m")){
                    bHL = Integer.parseInt(rdm[2]);
                    fAC=fAC && bMP[bHL];
                    fCY=false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
            }
            //ANI
            else if (rdm[0].contains("ani")){
                boolean byte2=Boolean.parseBoolean(rdm[2]);
                    fAC=fAC && byte2;
                    fCY=false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
            }
            //XRA
            else if (rdm[0].contains("xra")){
                if(rdm[1].contains("r")){
                    fAC= (fAC && !bR)|| (!fAC && bR) ;
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if(rdm[1].contains("m")){
                    bHL = Integer.parseInt(rdm[2]);
                    fAC= (fAC && !bMP[bHL])|| (!fAC && bMP[bHL]) ;
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
            }
            //XRI
            else if (rdm[0].contains("xri")){
                boolean byte2=Boolean.parseBoolean(rdm[2]);
                    fAC= (fAC && !byte2)|| (!fAC && byte2) ;
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
            }
            //ORA
            else if (rdm[0].contains("ora")){
                if (rdm[1].contains("r")){
                    fAC=fAC || bR;
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if(rdm[1].contains("m")){
                    bHL = Integer.parseInt(rdm[2]);
                    fAC=fAC || bMP[bHL];
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
                }
            }
            //ORI
            else if (rdm[0].contains("ori")){
                boolean byte2=Boolean.parseBoolean(rdm[2]);
                    fAC=fAC || byte2;
                    fCY=false;
                    saida += "resultado da operação "+fAC;
                    fAC = false;
                    saida += "Flag CY= "+fCY+" Flag AC = "+fAC+"\n";
                    Arquivo.Write(endSaida, saida);
            }
            //CMP
            else if (rdm[0].contains("cmp")){
                if (rdm[1].contains("r")){
                    int r = Integer.parseInt(rdm[2]);
                    int result;
                    result = ac- r;
                    if (result == 0){
                        z=Boolean.TRUE;
                        if (ac<r){
                            fCY = true;}
                        else{
                            fCY = false;}
                    }
                    else{
                        z = Boolean.FALSE;
                        if (ac<r){
                            fCY = true;}
                        else{
                            fCY = false; }
                    }
                    saida += "Flag CY= "+fCY+" Flag Z = "+z+"\n";
                    Arquivo.Write(endSaida, saida);
                }
                else if(rdm[1].contains("m")){
                    int op = memoriaPrincipal[10];
                    int result = ac - op;
                    if (result == 0){
                        z=Boolean.TRUE;
                        if (ac<op){
                            fCY = true;}
                        else{
                            fCY = false;}
                    }
                    else{
                        z = Boolean.FALSE;
                        if (ac<op){
                            fCY = true;}
                        else{
                            fCY = false;} 
                    }
                    saida += "Flag CY= "+fCY+" Flag Z = "+z+"\n";
                    Arquivo.Write(endSaida, saida);
                }
            }
            //FIM
            else{
                saida += "Comando "+rdm[0]+" não existe.\n";
                Arquivo.Write(endSaida, saida);
            }
    }
       
   public void cicloInst(){
       Processador exe = new Processador();
       if(instrucoes.isEmpty()){
           System.out.println("Não há instruções para serem executadas\n");
           saida += "Não há instruções para serem executadas\n";
           Arquivo.Write(endSaida, saida);
       }
       else{
            while (this.pc < instrucoes.size()) {
                //Real inicio do ciclo de busca
                rem = pc;
                exe.cicloBusca(instrucoes.get(rem));
                pc++;
                //fim do ciclo de busca
                exe.decodifica();
            }
       }
   }    

}
