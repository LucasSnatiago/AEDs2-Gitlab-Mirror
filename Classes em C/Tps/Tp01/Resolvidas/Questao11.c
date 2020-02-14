#include <stdio.h>
#include <stdlib.h>

//Criacao do tipo boolean
#define bool short
#define true 1
#define false 0

//Incluindo minhas bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/padroes.h"


bool ehPalindromoRecursivo(String* entrada, int pos1, int pos2){  //Verificar se uma String é palindromo
    bool palindromo = true;

    if(pos1 < pos2) palindromo = ehPalindromoRecursivo(entrada, pos1+1, pos2-1);
    if(entrada->string[pos1] != entrada->string[pos2]) palindromo = false;

    return palindromo;
}


int main(void){

    String* entrada = readString();

    while(!ehFimString(entrada)){

        if(ehPalindromoRecursivo(entrada, 0, entrada->length-1)){
            escreverSim();
        }else{
            escreverNao();
        }

        entrada = readString();
    }


    return 0;
}