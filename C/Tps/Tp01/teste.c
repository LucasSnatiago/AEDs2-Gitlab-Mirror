#include <stdio.h>
#include <stdlib.h>

#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


int main(void){

    while(true){
        String* entrada = readString();

        escreverString(entrada);
    }
    return 0;
}