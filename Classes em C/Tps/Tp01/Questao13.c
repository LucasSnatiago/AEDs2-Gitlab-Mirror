#include <stdio.h>
#include <stdlib.h>


#define DEBUGGING 1
#define DEBUGGING_COMPLETO 1

//Incluindo minhas bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


int main(void){

    srand(4);
    String* entrada = readString();
    
    char letra1;
    char letra2;
    while (!ehFimString(entrada)){

        letra1 = (char) ((int) 'a' + (rand() % 26)); 
        letra2 = (char) ((int) 'a' + (rand() % 26));

        debug("main: %c %c\n", letra1, letra2);
        String* resp = trocarLetras(entrada, letra1, letra2);
        
        escreverString(resp);
        
        entrada = readString();
    }
    
    return 0;
}
