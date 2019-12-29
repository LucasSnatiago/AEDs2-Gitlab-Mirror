//Bibliotecas para serem incluidas
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <locale.h>


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

    int posAtual = 0;

    String final;

    while(teste[posAtual] != '\0'){
        final.string[posAtual] = teste[posAtual];
        posAtual++;
    }  
    final.length = posAtual-1;

    return final;
}


void escreverString(String entrada){
    
    for(int i = 0; i < entrada.length; i++){
        printf("%c", entrada.string[i]);
    }
    printf("\n");

}


int main(){

    //Consertando codificador de texto
    setlocale(LC_ALL, "pt_BR.utf8");
    
    char teste[100];

    fgets(teste, 100, stdin);

    String entrada = stringBuilder(teste);

    escreverString(entrada);

    printf("O tamanho da string é %d", entrada.length);


    printf("\n");
    return 0;
}   