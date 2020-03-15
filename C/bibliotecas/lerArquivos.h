/*
    CRIACAO DA ESTRUTURA DE LEITURA DE ARQUIVOS EM C
    Criado por Lucas Santiago
    Data de criacao: 01/03/20
    Versao: 1.0.0 - 01/03/20
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define TAM 10000

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

String* lerArquivo(String* path){  //Funcao para extrair o texto de um arquivo

    FILE* arq = fopen(path->string, "r");
    char textoArquivo[TAM];

    fgets(textoArquivo, TAM, arq);

    debug("lerArquivo: %s\n", textoArquivo);
    String* saida = stringBuilder(textoArquivo);
    return saida;
    
}