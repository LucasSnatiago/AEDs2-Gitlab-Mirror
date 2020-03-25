/*
    CRIACAO DA ESTRUTURA DE LOGS EM C
    Criado por Lucas Santiago
    Data de criacao: 11/03/20
    Versao: 1.0.0
*/

#include <time.h>
#include <stdio.h>
#include <stdlib.h>

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

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


FILE* criarLog(const char* nomeArquivo){  //Criar um arquivo de log contendo logs do algoritmo
    FILE *arq = fopen(nomeArquivo, "w");

    fprintf(arq, "%s", "650888\t");

    return arq;
}

void inserirTempoExecucao(FILE *arq, clock_t tempoExecucao, int numMovimentacoes, int numComparacoes){  //Inserir tempo de execucao de um algoritmo no arquivo
    fprintf(arq, "%ld\t%d\t%d\t", tempoExecucao, numComparacoes, numMovimentacoes);
}

clock_t calcularTempo(clock_t start, clock_t fim){  //Retornando tempo de execucao do codigo
    return fim - start;
}