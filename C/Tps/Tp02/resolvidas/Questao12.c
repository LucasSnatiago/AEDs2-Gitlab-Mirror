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
#include "../../bibliotecas/Shellsort.h"


int main(void){

    Ordenador *lista = criarLista();
    String *entrada = readString();
    Personagens* personagem;
    FILE *log = criarLog("matrícula_shellsort.txt");
    String *conteudoArquivo;

    while(!ehFimString(entrada)){

        conteudoArquivo = lerArquivo(entrada);
        personagem = carregarPersonagem(conteudoArquivo);
        inserirFinal(lista, personagem);

        entrada = readString();
    }

    int numComparacoes = 0;
    int numMovimentacoes = 0;

    clock_t start = clock();
    shellsort(lista, &numComparacoes, &numMovimentacoes);
    clock_t stop = clock();
    insertionSort(lista, lista->numElementos-1);

    long tempoExecucao = 0;
    tempoExecucao += calcularTempo(start, stop);

    escreverLista(lista);
    
    inserirTempoExecucao(log, tempoExecucao, numComparacoes, numMovimentacoes);


    fclose(log);
    return 0;
}

