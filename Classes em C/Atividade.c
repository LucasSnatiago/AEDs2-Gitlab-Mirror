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
#include "bibliotecas/String.h"


int main(){

    #if DEBUGING == 1
        printf("--------------------------------\n\tDebuging ativado\n--------------------------------\n");
    #endif

    consertarCodificacaoTexto();
    
    char teste[100];

    fgets(teste, 100, stdin);

    String* entrada = stringBuilder(teste);
    String* entradaCopia = stringBuilder(teste);

    String* final1 = substituirPrimeiraOcorrencia(entrada, "oi", "NARU");
    String* final2 = substituirTexto(entradaCopia, "oi", "NARU");


    //freeString(&final1);

    if(final1){
        debug("Existe!\n");
    }



    escreverString(final1);
    escreverString(final2);

    return 0;
}   
