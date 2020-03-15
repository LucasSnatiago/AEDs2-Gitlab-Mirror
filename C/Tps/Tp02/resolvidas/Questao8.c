#include <stdio.h>
#include <stdlib.h>

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

//Minhas Bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/lerArquivos.h"
#include "../../bibliotecas/padroes.h"
#include "../../bibliotecas/Personagens.h"
#include "../../bibliotecas/Ordenador.h"
#include "../../bibliotecas/PesquisaBinaria.h"
#include "../../bibliotecas/Log.h"


int main(void){

    Ordenador *lista = criarLista();
    String *entrada = readString();
    Personagens* personagem;
    FILE *log = criarLog("matrícula_binaria.txt");
    String *conteudoArquivo;

    while(!ehFimString(entrada)){

        conteudoArquivo = lerArquivo(entrada);
        personagem = carregarPersonagem(conteudoArquivo);
        inserirFinal(lista, personagem);

        entrada = readString();
    }

    entrada = readString();
    int numComparacoes = 0;
    clock_t start;
    clock_t stop;
    clock_t tempoExecucao = 0;
    int numExecucoes = 0;

    while(!ehFimString(entrada)){

        start = clock();
        if(pesquisar(lista, entrada, &numComparacoes)) printf("SIM\n");
        else printf("NAO\n");
        stop = clock();

        tempoExecucao += calcularTempo(start, stop);
        numExecucoes++;

        entrada = readString();
    }
    
    inserirTempoExecucao(log, tempoExecucao/numExecucoes, numComparacoes);

    fclose(log);
    return 0;
}