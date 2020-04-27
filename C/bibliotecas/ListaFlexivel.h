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

//Criação da estrutura lista flexivel
typedef struct ListaFlexivel {
    Celula* inicio;
    Celula* final;
    int numElementos;
}ListaFlexivel;

//Criar uma lista flexivel
ListaFlexivel* new_ListaFlexivel() {
  ListaFlexivel* lista = (ListaFlexivel*) malloc(sizeof(ListaFlexivel));
  lista->inicio = NULL;
  lista->final = NULL;
  lista->numElementos = 0;
  return lista;
}

//Limpar uma lista flexivel
void freeListaFlexivel(ListaFlexivel** lista) {
    free(*lista);
    lista = NULL;
}

//Inserir na lista
void inserir(ListaFlexivel* lista, Personagens* person) {
  Celula* tmp = new_Celula(person);

  if(lista->inicio == NULL && lista->final == NULL){
    lista->inicio = tmp;
    lista->final = tmp;
  } else {
    lista->final->prox = tmp;
    lista->final->prox->ant = lista->final;
    lista->final = lista->final->prox;
  }

  lista->numElementos++;
}

//Retornar celula
Celula* retornaCelulaPos(ListaFlexivel* lista, int pos) {
  Celula* tmp = lista->inicio;
  for(int i = 0; i < pos; i++, tmp = tmp->prox);
  return tmp;
}

//Mostrar elementos da lista
void mostrarListaFlexivel(ListaFlexivel* lista) {
  Celula* tmp = lista->inicio;

  while(tmp != NULL) {
    escreverPersonagens(tmp->personagem);
    tmp = tmp->prox;
  }
}

//Mostrar elementos da lista ao contrario
void mostrarListaFlexivelInvertida(ListaFlexivel* lista) {
  Celula* tmp = retornaCelulaPos(lista, lista->numElementos-1);

  while(tmp != NULL) {
    escreverPersonagens(tmp->personagem);
    tmp = tmp->ant;
  }
}

//Swap quicksort
void swapQuick(ListaFlexivel* lista, int i, int j) {
  Celula* elemento1 = retornaCelulaPos(lista, i);
  Celula* elemento2 = retornaCelulaPos(lista, j);

  Personagens* tmp = elemento1->personagem;
  elemento1->personagem = elemento2->personagem;
  elemento2->personagem = tmp;
}

//Partition quicksort lista flexivel
void _quicksort(ListaFlexivel* lista, int esq, int dir) {
  int i = esq;
  int j = dir;
  int valorPivo = (i+j)/2;

  Celula* celI = retornaCelulaPos(lista, i);
  Celula* celJ = retornaCelulaPos(lista, j);
  Celula* pivo = retornaCelulaPos(lista, valorPivo);

  while(i <= j) {
    while(strcmp(celI->personagem->corDoCabelo->string, pivo->personagem->corDoCabelo->string) < 0){
      i++;
      celI = celI->prox;
    }
    while(strcmp(celJ->personagem->corDoCabelo->string, pivo->personagem->corDoCabelo->string) > 0){
      j--;
      celJ = celJ->ant;
    }

    if(i <= j) {
      swapQuick(lista, i, j);
      i++;
      j--;
      celI = celI->prox;
      celJ = celJ->ant;
    }
  }
  if (esq < j)  _quicksort(lista, esq, j);
  if (i < dir)  _quicksort(lista, i, dir);
}

//Quicksort listaFlexivel
void quicksort(ListaFlexivel* lista) {
  _quicksort(lista, 0, lista->numElementos-1);
}

//Swap insert
void swapInsert(Celula* i, Celula* j) {
  Personagens* tmp = i->personagem;
  i->personagem = j->personagem;
  j->personagem = tmp;
}

//Insertion sort
void insertsortCordoCabelo(ListaFlexivel* lista) {
  Celula* inicio = lista->inicio;

  Celula* tmp = NULL;
  Celula* menor = NULL;
  Celula* atual = inicio;

  while(atual->prox != NULL) {
    menor = atual;
    tmp = atual;
    while(tmp->prox != NULL) {
      if(strcmp(tmp->personagem->corDoCabelo->string, menor->personagem->corDoCabelo->string) == 0 && strcmp(tmp->personagem->nome->string, menor->personagem->nome->string) < 0){
        menor = tmp;
      }
      tmp = tmp->prox;
    }
    swapInsert(menor, atual);
    atual = atual->prox;
  }
}
