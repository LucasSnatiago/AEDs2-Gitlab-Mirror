/*
    CRIACAO DO TIPO Fila flexivel EM C
    Criado por Lucas Santiago
    Data de criacao: 21/04/2020
    Versao: 1.0.0 - 121/04/2020
    Changelog:
*/

#include <stdio.h>
#include <stdlib.h>
#include "Celula.h"

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#if DEBUGGING == 1
    #define printf debug
#else
    #define debug //
#endif

typedef struct filaflexivel {
    Celula* inicio;
    Celula* fim;
    int numElementos;
}FilaFlexivel;

FilaFlexivel* _startFila() {
    FilaFlexivel* filflex = (FilaFlexivel*) malloc(sizeof(FilaFlexivel));
    filflex->inicio = NULL;
    filflex->fim = NULL;
    filflex->numElementos = 0;

    return filflex;
}

void inserirFila(FilaFlexivel* fila, Personagens* person) {
    if(fila->inicio == NULL && fila->fim == NULL) {

        Celula* cel = new_Celula(person);
        fila->inicio = cel;
        fila->fim = cel;

    } else {

        Celula* cel = new_Celula(person);
        fila->fim->prox = cel;
        fila->fim = fila->fim->prox;

    }

    fila->numElementos++;
}

Celula* removerFila(FilaFlexivel* fila, Personagens* person) {
    Celula* removida = NULL;;
    if(fila->inicio == NULL && fila->fim == NULL){
        debug("removerFila: Erro ao remover um elemento em uma fila vazia!\n");
    } else {
        removida = fila->inicio;
        fila->inicio = fila->inicio->prox;
    }

    return removida;
}

//Escrever na saida padrao o conteudo da fila
void escreverFila(FilaFlexivel* fila) {
    Celula* tmp = fila->inicio;

    while(tmp != NULL){
        escreverPersonagens(tmp->personagem);
        tmp = tmp->prox;
    }
}