//Bibliotecas para serem incluidas
#include <stdio.h>
#include <stdlib.h>


//Tipo booleano
#define bool short
#define true 1
#define false 0


bool ehFim(char entrada[]){  //Retorna se a linha atual é fim
    bool fim = true;
    if(entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M') fim = false;
    return fim;
}


int tamanhoChar(char entrada[]){  //Descobre o tamanho de um texto
    int tam = 0;

    while(entrada[tam] != '\0'){
        tam++;
    }

    return tam-1;
}


bool ehPalindromo(char entrada[]){  //Retorna se uma entrada eh palindromo
    bool palindromo = true;
    int tamanhoEntrada = tamanhoChar(entrada);

    for(int i = 0, j = tamanhoEntrada-1; i < tamanhoEntrada / 2 + 1; i++, j--){
        if(entrada[i] != entrada[j]){
            palindromo = false;
        }
    }

    return palindromo;
}


void consertarFgets(char* entrada){
    int tamanho = tamanhoChar(entrada);
    entrada[tamanho-1] == '\n';
}


int main(){

    char entrada[1000];   

    fgets(entrada, 1000, stdin);
    consertarFgets(entrada);

    while(!ehFim(entrada)){

        if(ehPalindromo(entrada)){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }

        fgets(entrada, 1000, stdin);
        consertarFgets(entrada);
    }   
   
    return 0;
}   