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
#include "../../bibliotecas/Selecao.h"


int main(void){

    Ordenador *lista = criarLista();
    String *entrada = readString();
    Personagens* personagem;
    FILE *log = criarLog("matrícula_selecaoRecursiva.txt");
    String *conteudoArquivo;

    while(!ehFimString(entrada)){

        conteudoArquivo = lerArquivo(entrada);
        personagem = carregarPersonagem(conteudoArquivo);
        inserirFinal(lista, personagem);

        entrada = readString();
    }


    long start = clock();
    selectionSortRecur(lista, 0);
    long stop = clock();

    long tempoExecucao = 0;
    tempoExecucao += calcularTempo(start, stop);

    escreverLista(lista);
    
    //numExecucoes++;
    //inserirTempoExecucao(log, tempoExecucao/numExecucoes, numComparacoes);

    fclose(log);
    return 0;
}