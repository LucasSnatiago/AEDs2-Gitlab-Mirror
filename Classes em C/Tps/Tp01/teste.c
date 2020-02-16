#include <stdio.h>
#include <stdlib.h>

#define DEBUGGING 1
#define DEBUGGING_COMPLETO 0

#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


int main(void){

    
    String* entrada = readString();

    String* final = trocarLetras(entrada, 'a', 'b');

    escreverString(final);


    return 0;
}