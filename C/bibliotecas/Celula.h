/*
    CRIACAO DO TIPO CELULA EM C
    Criado por Lucas Santiago
    Data de criacao: 21/04/2020
    Versao: 1.0.0 - 121/04/2020
    Changelog:
*/

#include <stdio.h>
#include <stdlib.h>

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#if DEBUGGING == 1
    #define printf debug
#else
    #define debug //
#endif

typedef struct celula {
    Celula* prox;
    Personagens* personagem;
} Celula;

//Criar uma nova Celula de Personagem
Celula* new_Celula(Personagens* pers) {
    Celula* celula = (Celula*) malloc(sizeof(Celula));
    celula->prox = NULL;
    celula->personagem = pers;
    return celula;
}

//Limpar uma celula da memoria
void freeCelula(Celula* cel) {
    freePersonagem(cel->personagem);
    free(cel);
}