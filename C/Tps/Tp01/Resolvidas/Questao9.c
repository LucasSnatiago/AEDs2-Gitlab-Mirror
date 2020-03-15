#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool short
#define true 1
#define false 0

#define TAM 100
#define debug //printf


int tamanhoString(char entrada[]){  //Descobrir tamanho da entrada
    int buffer = 0;
    while(entrada[buffer] != '\n'){
        buffer++;
    }
    return buffer+1;
}

void escreverValores(char entrada[]){  //Escrever valores como double
    float valor = strtof(entrada, NULL);

    if(valor == (int)valor) printf("%d\n", (int) valor);
    else printf("%g\n", valor);
}

int main(void){

    int numTestes;
    scanf("%d", &numTestes);

    char valores[TAM];
    FILE* arquivoEscrita = fopen("Arquivo.txt", "w");

    for(int i = 0; i < numTestes; i++){

        scanf("\n%s", valores);
        fprintf(arquivoEscrita, "%s\n", valores);

    }

    fclose(arquivoEscrita);

    FILE* lerArquivo = fopen("Arquivo.txt", "r");

    fseek(lerArquivo, 0, SEEK_END);

    int tamAndar = 0;
    for(int i = 0; i < numTestes-1; i++){
        int cont = 0;
        long pos;
        char posicao;
        tamAndar = 0;
        while(cont < 1) { //Andar para tras no arquivo
            fseek(lerArquivo, -sizeof(char)*2, SEEK_CUR);
            fscanf(lerArquivo, "%c", &posicao);
            if(posicao == '\n') cont++;
            pos = ftell(lerArquivo);
            tamAndar++;
            debug("%ld\t", pos);
        }
        fscanf(lerArquivo, "%s", valores);
        escreverValores(valores);
        fseek(lerArquivo, -tamAndar, SEEK_CUR);
    }

    fseek(lerArquivo, 0, SEEK_SET);
    fscanf(lerArquivo, "%s", &valores);
    escreverValores(valores);

    fclose(lerArquivo);

    return 0;
}
