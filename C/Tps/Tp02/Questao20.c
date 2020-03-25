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
#include "../../bibliotecas/Insertion.h"




int main(void){

    Ordenador *lista = criarLista();
    String *entrada = readString();
    Personagens* personagem;
    //FILE *log = criarLog("matrícula_selecaoRecursiva.txt");
    String *conteudoArquivo;

    while(!ehFimString(entrada)){

        conteudoArquivo = lerArquivo(entrada);
        personagem = carregarPersonagem(conteudoArquivo);
        inserirFinal(lista, personagem);

        entrada = readString();
    }


    long start = clock();
    insertionSortA(lista, lista->numElementos);
    long stop = clock();



    long tempoExecucao = 0;
    tempoExecucao += calcularTempo(start, stop);

    for(int i = 0; i < 10; i++){
        escreverPersonagens(lista->ordem[i]);
    }
    
    //numExecucoes++;
    //inserirTempoExecucao(log, tempoExecucao/numExecucoes, numComparacoes);

    //fclose(log);
    return 0;
}