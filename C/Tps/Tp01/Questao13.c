#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0


bool ehFim(char entrada[]){  //Retorna se a linha atual é fim
    bool fim = true;
    if(entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M') fim = false;
    return fim;
}


void _alteracaoAleatoria(char* entrada, int letra1, int letra2, int pos){ //Alterar recursivamente a frase

    if(entrada[pos] == letra1) entrada[pos] = letra2;
    if(pos > -1) _alteracaoAleatoria(entrada, letra1, letra2, pos-1);

}


void alteracaoAleatoria(char* entrada, int letra1, int letra2){  //Alterar todas as ocorrencias de uma letra1 pela letra2
    int tam = 0;
    while(entrada[tam] != '\0') tam++;
    _alteracaoAleatoria(entrada, letra1, letra2, tam);
}


int main(int argc, char** argv){

    setlocale(LC_ALL, "pt_BR.UTF-8");
    srand(4);
    char entrada[1000];
    fgets(entrada, 1000, stdin);
    
    char letra1;
    char letra2;
    while (!ehFim(entrada)){

        letra1 = (char) ((int) 'a' + (rand() % 26)); 
        letra2 = (char) ((int) 'a' + (rand() % 26));

        alteracaoAleatoria(entrada, letra1, letra2);
        
        printf("%s", entrada);

        fgets(entrada, 1000, stdin);
    }
    
    return 0;
}
