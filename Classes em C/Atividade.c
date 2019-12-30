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

/*
    CRIACAO DO TIPO STRING EM C
    Criado por Lucas Santiago
    Data: 30/12/19
    Versao: 2.0.0
*/

typedef struct string{
    char* string;
    int length;
}String;

String stringBuilder(char teste[]){

    long long int buffer = 0;

    while(teste[buffer] != '\0'){
        buffer++;
    }

    char* tmp = (char*) malloc(sizeof(char) * buffer); 

    int posAtual;
    String final;
    for(posAtual = 0; posAtual < buffer; posAtual++) {
        tmp[posAtual] = teste[posAtual];
    }
    final.string = tmp;
    final.length = buffer-1;

    return final;
}


void escreverString(String entrada){
    
    for(int i = 0; i < entrada.length; i++){
        printf("%c", entrada.string[i]);
    }
    printf("\n");

}

////////////////////    FIM DO TIPO STRING   ////////////////////

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