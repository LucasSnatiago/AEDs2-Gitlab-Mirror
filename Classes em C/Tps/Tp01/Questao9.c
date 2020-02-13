#include <stdio.h>
#include <stdlib.h>

#define bool short
#define true 1
#define false 0

bool ehFim(char entrada[]){  //Retorna se a linha atual é fim
    bool fim = true;
    if(entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M') fim = false;
    return fim;
}


void lerInvertido(FILE* arq){  //Funcao para escrever o texto de um arquivo começando pelo final

    fseek(arq, -sizeof(double), SEEK_END);

    double num;
    long int pos = ftell(arq);
    //while(pos > 1){
    for(int i = 0; i < 80; i++){
        pos = ftell(arq);
        fscanf(arq, "%s\n", &num);
        //fgets(num, 100, arq);
        fseek(arq, -2*sizeof(double)-1, SEEK_CUR);
        printf("%ld\t", pos);
        printf("%lf\n", num);
    } 

}


int main(void){
    int numTestes;
    scanf("%d", &numTestes);
 
    float valores[numTestes];

    for(int i = 0; i < numTestes; i++){
        scanf("%f", &valores[i]);
    }

    FILE* arq = fopen("Arquivo.txt", "w");

    for(int i = 0; i < numTestes; i++){  //Escrever valores no arquivo
        fprintf(arq, "%f\n", valores[i]);
    }

    fclose(arq);

    FILE* arqLeitura = fopen("Arquivo.txt", "r");

    lerInvertido(arq);

    fclose(arq);
}
