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
}Ordenador;


Ordenador* criarLista(){  //Funcao para criar uma estrutura que ordena personagens
    return (Ordenador*) malloc(sizeof(Ordenador));
}


void inserirLista(Personagens* personagem, int pos){  //Inserir personagem em alguma posicao

}


void inserirInicio(Personagens* personagem){  //Inserir personagem no inicio da lista
    inserirLista(personagem, 1);
}