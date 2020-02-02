//Bibliotecas para serem incluidas
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <locale.h>


//Tipo booleano
#define bool short
#define true 1
#define false 0


//Minhas bibliotecas
#include "String.h"


#define DEBUGING 1

#if DEBUGING == 1
    #define debug printf 
#else
    #define debug //
#endif


int main(){

    #if DEBUGING == 1
        printf("--------------------------------\n\tDebuging ativado\n--------------------------------\n");
    #endif


    //Consertando codificador de texto
    setlocale(LC_ALL, "pt_BR.utf8");
    
    char teste[100];

    fgets(teste, 100, stdin);

    String entrada = stringBuilder(teste);
    String entradaCopia = stringBuilder(teste);

    String final1 = substituirPrimeiraOcorrencia(entrada, "oi", "NARU");
    String final2 = substituirTexto(entrada, "oi", "NARU");

    escreverString(final1);
    escreverString(final2);


    printf("\n");
    return 0;
}   
