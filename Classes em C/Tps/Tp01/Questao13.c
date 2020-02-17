#include <stdio.h>
#include <stdlib.h>


#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

//Incluindo minhas bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


int main(void){

    setlocale(LC_ALL, "ISO-8859-1");
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

        freeString(&resp);
        
        entrada = readString();
    }
    
    return 0;
}
