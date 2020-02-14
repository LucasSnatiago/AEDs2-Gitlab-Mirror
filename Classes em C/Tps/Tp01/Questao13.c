#include <stdio.h>
#include <stdlib.h>


#define DEBUGGING 1

//Incluindo minhas bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


void _trocarLetras(String* entrada, char letra1, char letra2, int pos){  //PRIVATE: Funcao para trocar as letras dentro de uma frase

    if(pos >= 0) _trocarLetras(entrada, letra1, letra2, pos-1);
    if(entrada->string[pos] == letra1) entrada->string[pos] = letra2;

}


String* trocarLetras(String* entrada, char letra1, char letra2){  //Funcao para trocar as letras dentro de uma frase
    String* fraseFinal;

    fraseFinal = copiarString(entrada);
    escreverString(fraseFinal);
    
    _trocarLetras(entrada, letra1, letra2, entrada->length);
    #if DEBUGGING == 1
        for(int i = 0; i < entrada->length; i++){
            if(fraseFinal->string[i] == letra1){
                debug("trocarLetras: Houve uma falha ao subistituir a letra %c por %c! Nas posições:\t", letra1, letra2);
                debug("%d\t\n", i);
            }
        }
    #endif

    return fraseFinal;
}


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
