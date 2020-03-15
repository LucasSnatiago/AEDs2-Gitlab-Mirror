/*
    CRIACAO DA ESTRUTURA DE ALOCACAO SEQUENCIAL EM C
    Criado por Lucas Santiago
    Data de criacao: 04/03/20
    Versao: 1.0.0 - 04/03/20
*/

#include <stdio.h>
#include <stdlib.h>

#define bool short
#define true 1
#define false 0

#define TAM 10000
#define Tam 100

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#ifndef DEBUGGING_COMPLETO
    #define DEBUGGING_COMPLETO 0
#endif

//Debug de codigo
#if DEBUGGING == 1
    #define debug printf 
#else
    #define debug //
#endif

#if DEBUGGING_COMPLETO == 1
    #define debugcompleto printf
#else
    #define debugcompleto //
#endif


typedef struct alocacaoSequencial{  //Estrutura que armazena a ordem dos personagens
    Personagens* ordem[Tam];
    int numElementos;
}Ordenador;


void escreverLista(Ordenador* lista){

    for(int i = 0; i < lista->numElementos; i++){

        //printf("[%d] ", i);
        escreverPersonagens(lista->ordem[i]);

    }

}

Ordenador* criarLista(){  //Funcao para criar uma estrutura que ordena personagens
    Ordenador* saida = (Ordenador*) malloc(sizeof(Ordenador));
    saida->numElementos = 0;
    return saida;
}

void _swiftListaDir(Ordenador* lista, int pos){  //Mover todos os personagens para a direita de alguma posicao

    for(int i = lista->numElementos; i >= pos; i--)
        lista->ordem[i+1] = lista->ordem[i];

    lista->numElementos++;
}


void _swiftListaEsq(Ordenador* lista, int pos){  //Mover todos os personagens para a esquerda de alguma posicao
   
    for(int i = pos; i < lista->numElementos; i++)
        lista->ordem[i] = lista->ordem[i+1];
    
    lista->numElementos--;

}


void inserirFinal(Ordenador* lista, Personagens* personagem){  //Inserir personagem no final da lista
    lista->ordem[lista->numElementos] = personagem;
    lista->numElementos++;
}


void inserirLista(Ordenador* lista, Personagens* personagem, int pos){  //Inserir personagem em alguma posicao

    if(pos < 0 || pos > lista->numElementos) debug("inserirLista: Posicao de insercao invalida!\n");
    else if(pos+1 == lista->numElementos) inserirFinal(lista, personagem);
    else{
        _swiftListaDir(lista, pos);
        lista->ordem[pos] = personagem;
    }

}


void inserirInicio(Ordenador* lista, Personagens* personagem){  //Inserir personagem no inicio da lista
    inserirLista(lista, personagem, 0);
}


Personagens* removerFinal(Ordenador* lista){  //Remover personagem do final de uma lista
    Personagens* removido;

    removido = lista->ordem[lista->numElementos-1];
    lista->numElementos--;

    return removido;
}


Personagens* removerLista(Ordenador* lista, int pos){  //Remover personagem de alguma posicao da lista
    Personagens* removido;

    if(pos < 0 || pos > lista->numElementos) debug("removerLista: Posicao de remocao invalida!\n");
    else if(pos+1 == lista->numElementos) removido = removerFinal(lista);
    else{
        removido = lista->ordem[pos];
        _swiftListaEsq(lista, pos);
    }

    return removido;
}


Personagens* removerInicio(Ordenador* lista){  //Remover personagem do comeco da lista
    Personagens* removido;

    removido = removerLista(lista, 0);

    return removido;
}


void printarRemocao(Personagens* personagem){  //Printar um personagem que foi removido

    printf("(R) ");
    escreverString(personagem->nome);
    freePersonagem(&personagem);

}