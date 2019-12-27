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

    char tmp[TAMSTRING];

    int posAtual = 0;
    for(int i = 0; i < TAMSTRING; i++){
        if(teste[i] == '\0'){
            i = TAMSTRING;
        }
        else if((int) teste[i] > 2){  //Remover os comandos de teclado
            tmp[posAtual] = teste[i];
            posAtual++;
        }
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

    //Consertando codificador de texto
    setlocale(LC_ALL, "pt_BR.utf8");
    
    char teste[100];

    printf("%c\n", (char) -1);

    fgets(teste, 100, stdin);

    


    String entrada = stringBuilder(teste);

    escreverString(entrada);

    printf("O tamanho da string é %d", entrada.length);


    printf("\n");
    return 0;
}   