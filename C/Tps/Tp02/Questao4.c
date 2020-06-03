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
#include "../../bibliotecas/Ordenador.h"


void printarRemocao(Personagens* personagem){  //Printar um personagem que foi removido

    printf("(R) ");
    escreverString(personagem->nome);
    freePersonagem(&personagem);

}


void executarAcoes(String** entrada, Ordenador* lista){  //inserir e remover elementos da lista
    String* inserirIn = stringBuilder((char*)"II");
    String* inserirFi = stringBuilder((char*)"IF");
    String* removerIn = stringBuilder((char*)"RI");
    String* removerFi = stringBuilder((char*)"RF");
    String* inserirPos = stringBuilder((char*)"I*");
    String* removerPos = stringBuilder((char*)"R*");
    Personagens* personagem;
    String* lerArq;
    
    if(compararStrings(entrada[0], inserirIn)){
        lerArq = lerArquivo(entrada[1]);
        personagem = carregarPersonagem(lerArq);
        inserirInicio(lista, personagem);
    }
    else if(compararStrings(entrada[0], inserirPos)){
        lerArq = lerArquivo(entrada[2]);
        personagem = carregarPersonagem(lerArq);
        inserirLista(lista, personagem, string2Int(entrada[1]));

    }
    else if(compararStrings(entrada[0], inserirFi)){
        lerArq = lerArquivo(entrada[1]);
        personagem = carregarPersonagem(lerArq);
        inserirFinal(lista, personagem);
    }
    else if(compararStrings(entrada[0], removerIn)){
        printarRemocao(removerInicio(lista));
    }
    else if(compararStrings(entrada[0], removerPos)){
        personagem = removerLista(lista, string2Int(entrada[1]));
        printarRemocao(personagem);
    }
    else if(compararStrings(entrada[0], removerFi)){
        personagem = removerFinal(lista);
        printarRemocao(personagem);
    }
}


int main(void){

    
    String* path = readString();
    String* conteudoArquivo = lerArquivo(path);
    Personagens* personagem;
    Ordenador* lista = criarLista();
    
    while(!ehFimString(path)){
    
        personagem = carregarPersonagem(conteudoArquivo);
        lista->ordem[lista->numElementos] = personagem;
        lista->numElementos++;
    
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
        executarAcoes(entradaSeparada, lista);

        freeString(&entrada);
        if(i+1 != execucoes) 
            entrada = readString();
    }

    escreverLista(lista);

    return 0;
}
