#include <stdio.h>
#include <stdlib.h>

#define DEBUGGING 0

//Minhas bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"



FILE* abrirArquivo(FILE* arq){  //Funcao para abrir um arquivo
    arq = fopen("Arquivo.txt", "w");
    return arq;
}

int main(void){
    int numTestes;
    scanf("%d", &numTestes);
 
    double valores[numTestes];

    for(int i = 0; i < numTestes; i++){
        scanf("%d", valores[i]);
    }

    FILE* arq = abrirArquivo(arq);

    for(int i = 0; i < numTestes; i++){  //Escrever valores no arquivo
        fscanf(arq, texto, 12);
        fscanf(arq, "\n");
    }
}
