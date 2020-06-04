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
#include "../../bibliotecas/Erro.h"
#include "../../bibliotecas/Personagens.h"
#include "../../bibliotecas/TabelaHash.h"

//Criar um personagem a partir de uma path
Personagens* personagemCarregado(String* entrada) {
    return carregarPersonagem(lerArquivo(entrada));
}

int main(void){
    String *entrada = readString();
    TabelaHash *tabela = new_TabelaHash(25);

    while(!ehFimString(entrada)){
        inserirTabelaHash(tabela, personagemCarregado(entrada));        

        entrada = readString();
    }

    entrada = readString();

    while(!ehFimString(entrada)){
        if(pesquisarTabelaHash(tabela, entrada)) printf("%s SIM\n", entrada->string);
        else printf("%s NÃO\n", entrada->string);      

        entrada = readString();
    }

    return 0;
}
