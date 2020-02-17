/*
    CRIACAO DO TIPO LISTA EM C
    Criado por Lucas Santiago
    Data de criacao: 17/02/20
    Versao: 1.0.0 - 17/02/20
    Changelog:
*/

#include <stdio.h>
#include <stdlib.h>

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#if DEBUGGING 1
    #define printf debug
#else
    #define debug //
#endif

typedef struct Lista{
    int* elementos;
    int numElementos;
    int tamanho;
}Lista;


Lista* new_Lista(int tamLista){  //Criador de listas
    Lista* resp;

    int* valores = (int*) malloc(sizeof(int) * tamLista);
    resp->elementos = valores;
    resp->tamanho = tamLista;
    resp->numElementos = 0;

    return resp;
} 


bool inserirFinal(Lista* lista, int valor){  //Adicionar um elemento no final de uma lista
    bool conseguiu = true;

    if(lista->numElementos == lista->tamanho){ 
        debug("inserirFinal: Nao foi possivel inserir no final, lista cheia!\n");
        conseguiu = false;
    }else{
        lista->elementos[lista->numElementos] = valor;
        lista->numElementos++;
    }

    return conseguiu;
}



int removerFinal(){  //Funcao para remocer do final

}