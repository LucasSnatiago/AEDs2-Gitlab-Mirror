/*
    CRIACAO DE UM ORDENADOR POR SELECAO EM C
    Criado por Lucas Santiago
    Data de criacao: 15/03/20
    Versao: 1.0.0
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

//Trocar dois itens de posicao
void _swapSelecao(Ordenador* lista, int pos1, int pos2){
    Personagens* tmp = lista->ordem[pos1];
    lista->ordem[pos1] = lista->ordem[pos2];
    lista->ordem[pos2] = tmp;
}


//Encontrar o menor termo
int minIndex(Ordenador* lista, int i, int j) 
{ 
    if (i == j) 
        return i; 
 
    int k = minIndex(lista, i + 1, j); 

    return (lista -> ordem[i] -> nome -> string < lista -> ordem[k] -> nome -> string)? i : k; 
} 


//Ordenar por selecao recursiva
void selectionSortRecur(Ordenador* lista, int index) 
{  
    if (index == lista -> numElementos) 
       return; 

    int k = minIndex(lista, index, lista -> numElementos - 1); 

    if (k != index) 
       _swapSelecao(lista,k,index); 

    selectionSortRecur(lista, index + 1); 
}