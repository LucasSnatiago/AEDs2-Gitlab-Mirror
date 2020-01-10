//Bibliotecas para serem incluidas
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <locale.h>


//Tipo booleano
#define bool short
#define true 1
#define false 0


//Minhas bibliotecas
#include "String.h"


int main(){

    //Consertando codificador de texto
    setlocale(LC_ALL, "pt_BR.utf8");
    
    char teste[100];

    fgets(teste, 100, stdin);

    String entrada = stringBuilder(teste);

    escreverString(entrada);

    printf("O tamanho da string é %d", entrada.length);


    printf("\n");
    return 0;
}   