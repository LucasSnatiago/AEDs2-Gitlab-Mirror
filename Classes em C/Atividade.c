//Bibliotecas para serem incluidas

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>


//Tipo booleano
#define bool short
#define true 1
#define false 0


//Tipo STRING

#define TAMSTRING 50000

typedef struct string{
    
    char string[TAMSTRING];
    int length;

}String;

String stringBuilder(char teste[]){

    char tmp[TAMSTRING];

    int posAtual = 0;
    bool comecouFrase = false;
    for(int i = 0; i < TAMSTRING; i++){
        if((int) teste[i] > 31 && (int) teste[i] < 127){  //32 ate 126
            tmp[posAtual] = teste[i];
        }
        posAtual++;
    }

    String final;

    for(int i = 0; i < posAtual; i++){
        final.string[i] = tmp[i];
    }    

    final.length = posAtual;

    return final;
}


void escreverString(String entrada){
    
    for(int i = 0; i < entrada.length; i++){
        printf("%c", entrada.string[i]);
    }
    printf("\n");

}


int main(){

    char teste[5];

    teste[0] = 'a';
    teste[1] = 'b';
    teste[2] = 'c';

    String entrada = stringBuilder(teste);

    //escreverString(entrada);

}