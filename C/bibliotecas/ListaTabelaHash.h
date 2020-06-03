#ifndef ListaTabelaHash_h
#define ListaTabelaHash_h

#include <string.h>
#include "Celula.h"
#include "Personagens.h"

// Lista para a tabela hash
typedef struct ListaHash {
    Celula *inicio;
    Celula *fim;

}ListaHash;

// Criar uma lista Hash na memoria
ListaHash* new_ListaHash() {
    ListaHash *saida = (ListaHash*) malloc(sizeof(ListaHash));

    saida->inicio = NULL;
    saida->fim = NULL;

    return saida; 
}

// Inserir na lista hash
void inserirListaHash(ListaHash *lista, Personagens *personagem) {
    if(lista->inicio == NULL) {
        lista->inicio = new_Celula(personagem);
        lista->fim = lista->inicio;
    
    } else {
        lista->fim->prox = new_Celula(personagem);
        lista->fim = lista->fim->prox;

    }
}

// Pesquisar em uma lista hash
bool pesquisarListaHash(ListaHash *lista, String *nome) {
    bool achou = false;
    Celula *tmp = lista->inicio;

    while(tmp != NULL) {
        if(strcmp(tmp->personagem->nome->string, nome->string) == 0)
            achou = true;

        tmp = tmp->prox;
    }

    return achou;
}

#endif