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