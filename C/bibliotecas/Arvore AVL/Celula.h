#ifndef CELULA_H
#define CELULA_H
/*
    CRIACAO DO TIPO CELULA EM C
    Criado por Lucas Santiago
    Data de criacao: 21/04/2020
    Versao: 1.0.0 - 121/04/2020
    Changelog:
*/

#include <stdio.h>
#include <stdlib.h>
#include "../Personagens.h"

//Criacao do tipo Celula
typedef struct Celula {
    Celula* dir;
    Celula* esq;
    Personagens* personagem;
    int fator;

}Celula;

//Criar uma nova Celula de Personagem
Celula* new_Celula(Personagens* pers) {
    Celula* celula = (Celula*) malloc(sizeof(Celula));
    
    celula->dir = NULL;
    celula->esq = NULL;
    celula->personagem = pers;
    celula->fator = 0;

    return celula;
    
}

//Limpar uma celula da memoria
void freeCelula(Celula* cel) {
    freePersonagem(&cel->personagem);
    free(cel);

}

#endif
