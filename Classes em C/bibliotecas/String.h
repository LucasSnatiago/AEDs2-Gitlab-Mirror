/*
    CRIACAO DO TIPO STRING EM C
    Criado por Lucas Santiago
    Data de criacao: 30/12/19
    Versao: 3.6.0 - 01/03/20
    Changelog:
    + Concaternar Strings
    + String 2 Int
    + String 2 float
    + String 2 double
    + char 2 Int
    + char 2 float
    + char 2 double
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define TAM 1000000

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#ifndef DEBUGGING_COMPLETO
    #define DEBUGGING_COMPLETO 0
#endif

//Debug de codigo
#if DEBUGGING == 1
    #define debug printf 
#else
    #define debug //
#endif

#if DEBUGGING_COMPLETO == 1
    #define debugcompleto printf
#else
    #define debugcompleto //
#endif

typedef struct string{  //Criacao da estrutura de uma String
    char* string;
    int length;
}String;


void freeString(String** entrada){  //Limpar uma String da memoria
    free(*entrada);
    *entrada = NULL;
}


char* char2String(){  //Funcao para retornar a maior cadeia de char possivel para o tipo int
    return (char*) malloc(sizeof(char) * INT_MAX);
}


int _bufferSize(char entrada[]){  //Contar tamanho da entrada
    int buffer = 0;

    while(entrada[buffer] != '\0'){
        buffer++;
    }

    return buffer;
}


int _bufferSizeX(char entrada[]){  //Conta o tamanho da entrada ate encontrar um '\0' ou um '\n'
    int buffer = 0;

    while(entrada[buffer] != '\0' && entrada[buffer] != '\n'){
        buffer++;
    }

    return buffer;
}

int acharPrimeiraString (char entrada[], char key[]) {

    int pos = -1;
    int tamEntrada = strlen(entrada);
    int tamKey = strlen(key);

    debug("acharPrimeiraString: Achando string\n");

    for (int k = 0; k < tamEntrada; k ++) {
        debugcompleto("\racharPrimeiraString: Pos -> %d", k);

        for (int j = 0; j < tamKey; j++) {

            if (entrada[k+j] != key[j]) {

                j = tamKey;

            } else if (j == tamKey-1) {

                pos = k;
                k = tamEntrada;

            }


        }
    }
    debugcompleto("\n");
    return pos;
}


void removerString(char entrada[], char key[]){  //Consertar o fgets que deixa passar alguns caracteres
    
    int tamEntrada = strlen(entrada);
    int tamKey = strlen(key);
    int pos = acharPrimeiraString(entrada, key);

    if (pos != -1) {
        for (int i = 0; i < tamEntrada-tamKey-pos; i++) {
            debugcompleto("\rremoverString: Pos -> %d", i);
            entrada[i+pos] = entrada[i+tamKey+pos];
        }
        debugcompleto("\n");
        for (int i = 0; i < tamKey; i ++) {
            debugcompleto("\rremoverString: Pos -> %d", i);
            entrada[tamEntrada-tamKey+i] = '\0';

        }
        debugcompleto("\n");
        debug("removerString: String formatada -> %s\n", entrada);
    } else {
        debug("removerString: String não foi formatada -> key não encontrada\n");
    }
}

String* stringBuilder(char entrada[]){  //Constroi o tipo String

    int buffer = _bufferSizeX(entrada);
    debug("stringBuilder: Tamanho do buffer: %d\n", buffer);

    char* tmp = (char*) malloc(sizeof(char) * buffer+1); 

    int posAtual;
    String* resp = (String*) malloc(sizeof(String));
    for(posAtual = 0; posAtual < buffer; posAtual++) {
        tmp[posAtual] = entrada[posAtual];
    }
    tmp[posAtual] = '\0';
    resp->string = tmp;
    resp->length = buffer;

    return resp;
}


String* _stringBuilderX(char entrada[], int tamanhoString){  //Constroi o tipo String, já com o tamanho definido (NÃO RECOMENDADO, USO EXPERIMENTAL)

    String* final;
    final->string = entrada;
    final->length = tamanhoString;

    return final;
}


void escreverString(String* entrada){  //Escreve uma String na tela
    
    if(entrada){  //Se a String existir em memoria escreva
            #if DEBUGGING == 0
                for(int i = 0; i < entrada->length; i++){
                    printf("%c", entrada->string[i]);
                }
            #else
                printf("escreverString: Tamanho da String = %d\n", entrada->length);
                for(int i = 0; i < entrada->length; i++){ 
                    printf("%c", entrada->string[i]);
                    if(entrada->string[i] == '\n') printf("\\n");
                    if(entrada->string[i] == '\0') printf("\\0");
                }
            #endif
        printf("\n");
    }else{
        debug("escreverString: Variavel não existente em memória!\n");
    }
}


int letrasMaiusculas(String* entrada){  //Retorna o numero de letras maiusculas de uma String
    int quantMaiusculas = 0;

    for(int i = 0; i < entrada->length; i++){
        if(entrada->string[i] >= 'A' && entrada->string[i] <= 'Z'){
            quantMaiusculas++;
        }
    }

    return quantMaiusculas;
}


int letrasMinusculas(String* entrada){  //Retorna o numero de letras minusculas de uma String
    int quantMinusculas = 0;

    for(int i = 0; i < entrada->length; i++){
        if(entrada->string[i] >= 'a' && entrada->string[i] <= 'z'){
            quantMinusculas++;
        }
    }

    return quantMinusculas;
}


bool ehPalindromo(String* entrada){  //Retorna 1 se for palindromo, caso contrario 0
    bool ehPalindromo = true;

    int j = entrada->length;
    for(int i = 0; i < entrada->length/2; i++, j--){
        if(entrada->string[i] != entrada->string[j]){
            ehPalindromo = false;
            i = entrada->length;
        }
    }

    return ehPalindromo;
} 


String* cifraCesar(String* entrada, int chave){  //Cifra as mensagens recebidas com a cifra de Cesar
    char* mensagemCifrada = (char*) malloc(sizeof(char) * entrada->length);

    char letra;
    for(int i = 0; i < entrada->length; i++){
        letra = (char) ((int) entrada->string[i] + chave);
        mensagemCifrada[i] = letra;
    } 

    String* cifra = _stringBuilderX(mensagemCifrada, entrada->length);

    return cifra;
}


//Funcao para encontrar um texto em uma frase
bool _find(char texto[], int textoLength, char procura[], int *resp){
  bool encontrar = false;
  long int tam = textoLength;
  int tamProcura = strlen(procura);
  int posProcura = -1;
  bool table = true;

  for(long int i = 0; i < tam; i++){
    if(texto[i] == procura[0] && table){
      encontrar = true;
      table = false;
      for(int j = 0; j < tamProcura; j++){
        if(texto[i+j] != procura[j]){
          encontrar = false;
          j = tamProcura;
        }
        else{
          table = true;
        }
      }
    }
    if(encontrar){
      posProcura = i + tamProcura;
      i = tam;
    }
  }
  *resp = posProcura;
  return encontrar;
}


bool procurarItens(String* entrada, char procurarInicio[], char procurarFinal[], char resp[]){  //Funcao que procura um texto dentro de outro maior
  int posI;
  int posF;
  bool encontrar = false;
  encontrar = _find(entrada->string, entrada->length, procurarInicio, &posI);
  if(encontrar){
    _find(&entrada->string[posI], entrada->length,procurarFinal, &posF);
  }
  int j = 0;

    if(encontrar){
        
        for(int i = posI; i < posI+posF-strlen(procurarFinal); i++){
            resp[j] = entrada->string[i];
            j++;
        }
    
        resp[j] = '\0';
    }


  return encontrar;
}


int _posProcura(String* entrada, const char texto[]){  //Procura a posicao da primeira ocorrencia de um texto
    int pos = -1;
    bool achou = false;
    int tamanhoEntrada = entrada->length;
    int tamanhoTexto = strlen(texto);

    for(int i = 0; i < tamanhoEntrada && !achou; i++){
        
        if(entrada->string[i] == texto[0]){

            for(int j = 1; j < tamanhoTexto; j++){

                if(entrada->string[i+j] != texto[j]){
                    j = tamanhoTexto;
                    achou = false;
                }
                else{
                    achou = true;
                    pos = i;
                }
            }
        }
    }

    return pos;
}


String* substituirPrimeiraOcorrencia(String* entrada, const char texto[], const char substituirPor[]){  //Substituir a primeira ocorrencia de um texto por outro na entrada
    char* tmp = char2String();
    int tamanhoTexto = strlen(texto);
    int tamanhoSubstituicao = strlen(substituirPor);
    int tamanhoEntrada = entrada->length;
    int tamanhoFinal = tamanhoEntrada - tamanhoTexto + tamanhoSubstituicao;

    int posOcorrencia = _posProcura(entrada, texto);

    String* final;

    if(posOcorrencia != -1){
        for(int i = 0; i < posOcorrencia; i++){
            tmp[i] = entrada->string[i];
        }

        for(int i = 0; i < tamanhoSubstituicao; i++){
            tmp[i+posOcorrencia] = substituirPor[i];
        }

        for(int i = posOcorrencia; i < tamanhoEntrada - tamanhoTexto + 1; i++){
            tmp[i+tamanhoSubstituicao] = entrada->string[i+tamanhoTexto];
        }

        tmp[tamanhoFinal+1] = '\0';

        final = stringBuilder(tmp);
        freeString(&entrada);
        if(entrada){
            debug("substituirPrimeiraOcorrencia: Falha ao excluir String entrada da memoria!\n");
        }else{
            debug("substituirPrimeiraOcorrencia: String entrada foi excluida da memoria!\n");
        }
    }
    else{
        final = entrada;
        free(tmp);
        tmp = NULL;
        if(tmp){
            debug("substituirPrimeiraOcorrencia: Falha ao excluir char2String tmp da memoria!\n");
        }else{
            debug("substituirPrimeiraOcorrencia: char2String tmp foi apagado!\n");
        }
    }   
    
    return final;
}


String* substituirTexto(String* entrada, const char procurar[], const char alterarPor[]){  //Substituir todas as ocorrencias de um texto 
    String* final = entrada;
    char* enderecoFinal;

    do{
        enderecoFinal = &final->string[0];
        debug("substituirTexto: %p\t\a%p\n", enderecoFinal, &final);
        final = substituirPrimeiraOcorrencia(final, procurar, alterarPor);
    }while (&final->string[0] != enderecoFinal);  //Verifica se o endereço do texto mudou (se mudou, houve alteracao no texto)
    
    return final;
}


void consertarCodificacaoTexto(){ //Consertando codificador de texto
    setlocale(LC_ALL, "pt_BR.utf8");
}
    

void removerTudoString(char entrada[], char key[]) {

    int pos = acharPrimeiraString(entrada, key);
    
    debug("removerTudoString: Removendo todas as ocorrencias\n");

    while (pos != -1) {
        removerString(entrada, key);
        pos = acharPrimeiraString(entrada, key);

    }

    debug("removerTudoString: String formatada -> %s\n", entrada);

}

String* readString(){  //Lendo uma String da stdin
    char tmp[TAM];

    fgets(tmp, TAM, stdin);
    //removerTudoString(tmp, "\n");
    String* saida = stringBuilder(tmp);
    debug("readString: String lida do teclado:\n%s", saida->string);
    debug("\nreadString: Tamanho da String: %d\n", saida->length);

    return saida;
}


String* copiarString(String* frase){  //Funcao para copiar a primeira String na segunda String
    int tamanhoFrase = frase->length;
    char tmp[tamanhoFrase];

    for(int i = 0; i < tamanhoFrase; i++){
        tmp[i] = frase->string[i];
    }

    return stringBuilder(tmp);
}


void _trocarLetras(String* entrada, char letra1, char letra2, int pos){  //Funcao para trocar as letras dentro de uma frase

    if(pos != 0) _trocarLetras(entrada, letra1, letra2, pos-1);
    if(entrada->string[pos] == letra1){
        debugcompleto("_trocarLetras: trocar %c por %c\n", letra1, letra2);
        entrada->string[pos] = letra2;
        debugcompleto("_trocarLetras: resultado %c se tornou %c\n", letra1, entrada->string[pos]);
    }

}


String* trocarLetras(String* entrada, char letra1, char letra2){  //Funcao para trocar as letras dentro de uma frase
    String* fraseFinal;

    fraseFinal = copiarString(entrada);
    
    _trocarLetras(fraseFinal, letra1, letra2, entrada->length-1);
    #if DEBUGGING == 1
        for(int i = 0; i < entrada->length; i++){
            if(fraseFinal->string[i] == letra1){
                debug("trocarLetras: Houve uma falha ao subistituir a letra %c por %c! Nas posições:\t", letra1, letra2);
                debug("%d\t\n", i);
            }
        }
    #endif

    return fraseFinal;
}


String* concaternarString(String* entrada, String* adicionar){  //Funcao para concaternar Strings
    int tam = entrada->length + adicionar->length;
    char textos[tam];

    int pos = 0;
    for(int i = 0; i < entrada->length; i++){
        textos[pos] = entrada->string[i];
        pos++;
    }

    for(int i = 0; i < adicionar->length; i++){
        textos[pos] = adicionar->string[i];
    }

    String* final = stringBuilder(textos);
    return final;
}


bool compararStrings(String* A, String* B){  //Funcao para comparar se duas Strings sao iguais
    bool resp = true;

    if(A->length != B->length) resp = false;
    else
        for(int i = 0; i < A->length; i++){
            if(A->string[i] != B->string[i]){
                resp = false;
                i = A->length;
            }
        }

    return resp;
}


int string2Int(String* entrada){  //Transformar uma String em um numero inteiro
    int saida = 0;

    for(int i = entrada->length; i > 0; i--){
        saida += (int) entrada->string[i];
        saida *= 10;
    }
    saida /= 10;

    return saida;
}

float string2Float(String* entrada){  //Transformar uma String em um numero flutuante
    float saida = 0;

    int posVirgula;
    for(int i = 0; i < entrada->length; i++){
        if(entrada->string[i] == '.'){
            posVirgula = i;
            i = entrada->length;
        }
        if(i == entrada->length-1) posVirgula = 0;
    }

    for(int i = entrada->length; i > 0; i--){
        if(entrada->string[i] != '.') saida += (int) entrada->string[i];
        saida *= 10;
    }

    for(int i = 0; i < posVirgula+1; i++){
        saida /= 10;
    }


    return saida;
}

double string2Double(String* entrada){  //Transformar uma String em um numero flutuante de precisao
    double saida = 0;

    int posVirgula;
    for(int i = 0; i < entrada->length; i++){
        if(entrada->string[i] == '.'){
            posVirgula = i;
            i = entrada->length;
        }
        if(i == entrada->length-1) posVirgula = 0;
    }

    for(int i = entrada->length; i > 0; i--){
        if(entrada->string[i] != '.') saida += (int) entrada->string[i];
        saida *= 10;
    }

    for(int i = 0; i < posVirgula+1; i++){
        saida /= 10;
    }

    return saida;
}

int char2Int(char* entrada){  //Transformar um vetor em um numero inteiro
    int saida = 0;
    int tam = _bufferSize(entrada);

    for(int i = tam; i > 0; i--){
        saida += (int) entrada[i];
        saida *= 10;
    }
    saida /= 10;

    return saida;
}

float char2Float(char* entrada){  //Transformar um vetor em um numero flutuante
    float saida = 0;
    int tam = _bufferSize(entrada);

    int posVirgula;
    for(int i = 0; i < tam; i++){
        if(entrada[i] == '.'){
            posVirgula = i;
            i = tam;
        }
        if(i == tam-1) posVirgula = 0;
    }

    for(int i = tam; i > 0; i--){
        if(entrada[i] != '.') saida += (int) entrada[i];
        saida *= 10;
    }

    for(int i = 0; i < posVirgula+1; i++){
        saida /= 10;
    }


    return saida;
}

double char2Double(char* entrada){  //Transformar um vetor em um numero flutuante de precisao
    double saida = 0;
    int tam = _bufferSize(entrada);

    int posVirgula;
    for(int i = 0; i < tam; i++){
        if(entrada[i] == '.'){
            posVirgula = i;
            i = tam;
        }
        if(i == tam-1) posVirgula = 0;
    }

    for(int i = tam; i > 0; i--){
        if(entrada[i] != '.') saida += (int) entrada[i];
        saida *= 10;
    }

    for(int i = 0; i < posVirgula+1; i++){
        saida /= 10;
    }

    return saida;
}