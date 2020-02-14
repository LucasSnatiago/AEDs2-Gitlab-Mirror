/*
    Funcoes padroes nos codigos
    Criado por Lucas Santiago
    Data de criacao: 03/02/2020
    Versao: 1.1.0 - 13/02/2020
*/

//Bibliotecas usadas
#include <stdio.h>

//Tipo booleano
#define bool short
#define true 1
#define false 0


bool ehFim(char entrada[]){  //Retorna se a linha atual é fim
    bool fim = true;
    if(entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M') fim = false;
    return fim;
}


bool ehFimString(String* entrada){  //Retorna se a linha atual é fim 
    bool fim = true;
    if(entrada->string[0] != 'F' || entrada->string[1] != 'I' || entrada->string[2] != 'M') fim = false;
    return fim;
}


void escreverSim(){  //Funcao para escrever SIM na saida
    printf("SIM\n");
}


void escreverNao(){  //Funcao para escrever NAO na saida
    printf("NAO\n");
}