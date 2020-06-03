#ifndef TabelaHash_h
#define TabelaHash_h

#include <stdlib.h>
#include "ListaTabelaHash.h"
#include "Personagens.h"

// Estrutura de Tabela Hash
typedef struct TabelaHash {
    ListaHash **tabela;
    int tamTabela;

}TabelaHash;

// Criar tabela Hash 
TabelaHash* new_TabelaHash(int tamTabela) {
    TabelaHash *saida = (TabelaHash*) malloc(sizeof(TabelaHash));
    saida->tabela = (ListaHash**) malloc(sizeof(ListaHash) * tamTabela);

    for(int i = 0; i < tamTabela; i++)
        saida->tabela[i] = NULL;

    saida->tamTabela = tamTabela;

    return saida;
}

// Inserir em uma tabela Hash
void inserirTabelaHash(TabelaHash *tabela, Personagens *personagem) {
    int posTabela = personagem->altura % tabela->tamTabela;

    if(tabela->tabela[posTabela] == NULL) {
        tabela->tabela[posTabela] = new_ListaHash();
        inserirListaHash(tabela->tabela[posTabela], personagem);
        
    } else {
        inserirListaHash(tabela->tabela[posTabela], personagem);

    }
}

// Pesquisar um elemento na tabela hash
bool pesquisarTabelaHash(TabelaHash *tabela, String *nome) {
    bool achou = false;

    for(int i = 0; i < tabela->tamTabela; i++) {
        if(tabela->tabela[i] != NULL && pesquisarListaHash(tabela->tabela[i], nome)) {
            achou = true;
            i = tabela->tamTabela;

        }
    }

    return achou;
}

#endif