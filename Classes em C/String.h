/*
    CRIACAO DO TIPO STRING EM C
    Criado por Lucas Santiago
    Data: 30/12/19
    Versao: 2.1.1
*/

typedef struct string{  //Criacao da estrutura de uma String
    char* string;
    int length;
}String;


String stringBuilder(char entrada[]){  //Constroi o tipo String

    long long int buffer = 0;

    while(entrada[buffer] != '\0'){
        buffer++;
    }

    char* tmp = (char*) malloc(sizeof(char) * buffer); 

    int posAtual;
    String final;
    for(posAtual = 0; posAtual < buffer; posAtual++) {
        tmp[posAtual] = entrada[posAtual];
    }
    final.string = tmp;
    final.length = buffer-1;

    return final;
}


String stringBuilderX(char entrada[], int tamanhoString){  //Constroi o tipo String, já com o tamanho definido (NÃO RECOMENDADO, USO EXPERIMENTAL)

    String final;
    final.string = entrada;
    final.length = tamanhoString;

    return final;
}


void escreverString(String entrada){  //Escreve uma String na tela
    
    for(int i = 0; i < entrada.length; i++){
        printf("%c", entrada.string[i]);
    }
    printf("\n");

}


int letrasMaiusculas(String entrada){  //Retorna o numero de letras maiusculas de uma String
    int quantMaiusculas = 0;

    for(int i = 0; i < entrada.length; i++){
        if(entrada.string[i] >= 'A' && entrada.string[i] <= 'Z'){
            quantMaiusculas++;
        }
    }

    return quantMaiusculas;
}


int letrasMinusculas(String entrada){  //Retorna o numero de letras minusculas de uma String
    int quantMinusculas = 0;

    for(int i = 0; i < entrada.length; i++){
        if(entrada.string[i] >= 'a' && entrada.string <= 'z'){
            quantMinusculas++;
        }
    }

    return quantMinusculas;
}


short palindromo(String entrada){  //Retorna 1 se for palindromo, caso contrario 0
    short ehPalindromo = 1;

    int j = entrada.length;
    for(int i = 0; i < entrada.length; i++, j--){
        if(entrada.string[i] != entrada.string[j]){
            ehPalindromo = 0;
            i = entrada.length;
        }
    }

    return ehPalindromo;
} 


String cifraCesar(String entrada, int chave){  //Cifra as mensagens recebidas com a cifra de Cesar
    char* mensagemCifrada = (char*) malloc(sizeof(char) * entrada.length);

    char letra;
    for(int i = 0; i < entrada.length; i++){
        letra = (char) ((int) entrada.string[i] + chave);
        mensagemCifrada[i] = letra;
    } 

    String cifra = stringBuilderX(mensagemCifrada, entrada.length);

    return cifra;
}


////////////////////    FIM DO TIPO STRING   ////////////////////