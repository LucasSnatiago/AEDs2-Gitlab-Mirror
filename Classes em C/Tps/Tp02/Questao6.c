#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

//Minhas Bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/lerArquivos.h"
#include "../../bibliotecas/padroes.h"
#include "../../bibliotecas/Personagens.h"
#include "../../bibliotecas/FilaCircular.h"


int main(void){

    
    String* path = readString();
    String* conteudoArquivo = lerArquivo(path);
    Personagens* personagem;
    FilaCircular* fila = new_FilaCircular(5);
    
    while(!ehFimString(path)){
    
        personagem = carregarPersonagem(conteudoArquivo);
        inserirFC(fila, personagem);
    
        freeString(&path);
        freeString(&conteudoArquivo);
        path = readString();
        if(!ehFimString(path)) conteudoArquivo = lerArquivo(path);
    }
    freeString(&path);
    freeString(&conteudoArquivo);

    int execucoes = lerInt();
    
    String* entrada = readString();
    String** entradaSeparada;
    int pos;
    for(int i = 0; i < execucoes; i++){
        pos = 0;

        entradaSeparada = splitString(entrada, ' ');
        executarAcoes(entradaSeparada, fila);

        freeString(&entrada);
        if(i+1 != execucoes) 
            entrada = readString();
    }

    escreverFilaCircular(fila);

    return 0;
}
