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
    


    return 0;
}   
