/*
    CRIACAO DO TIPO PILHA FLEXIVEL EM C
    Criado por Lucas Santiago
    Data de criacao: 26/04/2020
    Versao: 1.0.0 - 26/04/2020
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

//Criação da estrutura pilha flexivel
typedef struct PilhaFlexivel {
    Celula* topo;
    int numElementos;
}PilhaFlexivel;

//Criar uma Pilha Flexivel
PilhaFlexivel* new_pilhaFlexivel() {
    PilhaFlexivel* pilha = (PilhaFlexivel*) malloc(sizeof(PilhaFlexivel));
    pilha->topo = NULL;
    pilha->numElementos = 0;
    return pilha;
}

//Limpar uma Pilha flexivel da memoria
void freePilhaFlexivel(PilhaFlexivel** pilha) {
    free(*pilha);
    pilha = NULL;
}

//Inserir personagem na pilha
void empilhar(PilhaFlexivel* pilha, Personagens* person) {
  if(pilha->topo == NULL) {
    pilha->topo = new_Celula(person);
    pilha->topo->prox = NULL;
  } else {
    Celula* tmp = new_Celula(person);
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
  }
  pilha->numElementos++;
}

//Remover um personagem da pilha
Celula* desempilhar(PilhaFlexivel* pilha) {
  Celula* removida = NULL;

  if(pilha->topo == NULL){
    debug("Desempilhar: Erro ao remover de pilha flexivel vazia!");
  } else {
    removida = pilha->topo;
    pilha->topo = pilha->topo->prox;
    pilha->numElementos--;
  }

  if(removida != NULL){
    printf("(R) ");
    escreverString(removida->personagem->nome);
  }

  return removida;
}

void escreverPilhaFlexivel(PilhaFlexivel* pilha) {
  Celula* tmp = pilha->topo;
  while(tmp != NULL) {
    escreverPersonagens(tmp->personagem);
    tmp = tmp->prox;
  }
}

//Escrever a partir do primeiro elemento inserido
void _pilhaFlexivelInvertida(Celula* i, int num) {
  if(i->prox != NULL) _pilhaFlexivelInvertida(i->prox, num-1);

  //printf("[%d] ", num);
  escreverPersonagens(i->personagem);
}

//Escrever a pilha a partir do topo
void escreverPilhaFlexivelInvertida(PilhaFlexivel* pilha) {
  _pilhaFlexivelInvertida(pilha->topo, pilha->numElementos-1);
}
