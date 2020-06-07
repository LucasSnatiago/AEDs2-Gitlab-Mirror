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
#include "../../bibliotecas/AVLTree.h"

//Criar um personagem a partir de uma path
Personagens* personagemCarregado(String* entrada) {
    return carregarPersonagem(lerArquivo(entrada));
}

int main(void){
    String *entrada = readString();
    struct Node *root = NULL; 

    while(!ehFimString(entrada)){
        root = insert(root, personagemCarregado(entrada));        

        entrada = readString();
    }

    entrada = readString();

    while(!ehFimString(entrada)){
        printf("%s raiz ", entrada->string);
        if(_pesquisarAVL(root, entrada)) printf("SIM\n");
        else printf("NÃO\n");      

        entrada = readString();
    }

    return 0;
}
