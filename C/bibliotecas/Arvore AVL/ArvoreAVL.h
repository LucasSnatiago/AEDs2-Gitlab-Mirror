#ifndef ArvoreAVL_H
#define ArvoreAVL_H

#include <stdlib.h>
#include <string.h>

#include "Celula.h"
#include "../Personagens.h"
#include "../String.h"
#include "../Erro.h"

// Estrutura de Arvore AVL
typedef struct ArvoreAVL {
    Celula* raiz;

} ArvoreAVL;

// Criando uma estrutura de Arvore AVL
ArvoreAVL* new_ArvoreAVL() {
    ArvoreAVL* saida = (ArvoreAVL*) malloc(sizeof(ArvoreAVL));

    saida->raiz = NULL;

    return saida;
}

// Insercao em Arvore AVL
void inserirAVL(ArvoreAVL* arvore, Personagens* personagem) {
    if(arvore->raiz == NULL) 
        arvore->raiz = new_Celula(personagem);

    else
        _inserirAVL(arvore->raiz, personagem);

}

// Insercao recursiva em uma Arvore AVL
void _inserirAVL(Celula* i, Personagens* personagem) {
    if(strcmp(i->personagem->nome->string, personagem->nome->string) < 0) {
        if(i->esq == NULL) i->esq = new_Celula(personagem);
        else {
                i->fator++;
                _inserirAVL(i->esq, personagem);
            
            }

    } else if(strcmp(i->personagem->nome->string, personagem->nome->string) > 0) {
        if(i->dir == NULL) i->dir = new_Celula(personagem);
        else {
                i->fator--;
                _inserirAVL(i->dir, personagem);
            
            }

    } else aviso("Esse elemento ja esta inserido na arvore!");

    if(i->fator > 1) {
        

    } else if(i->fator < -1) {


    }
}

#endif