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
#include "../../bibliotecas/Log.h"
#include "../../bibliotecas/FilaFlexivel.h"

//Criar um personagem a partir de uma path
Personagens* personagemCarregado(String* entrada) {
    return carregarPersonagem(lerArquivo(entrada));
}

int main(void){

    FilaFlexivel* fila = _startFila();
    String *entrada = readString();
    //FILE *log = criarLog("matrícula_selecaoRecursiva.txt");

    while(!ehFimString(entrada)){

        inserirFila(fila, personagemCarregado(entrada));        
        entrada = readString();

    }


    return 0;
}