/*
    CRIACAO DO TIPO STRING EM C
    Criado por Lucas Santiago
    Data de criacao: 30/12/19
    Versao: 3.1.2
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define DEBUGING 1

//Debug de codigo
#if DEBUGING == 1
    #define debug printf 
#else
    #define debug //
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


String* stringBuilder(char entrada[]){  //Constroi o tipo String

    int buffer = 0;

    while(entrada[buffer] != '\0'){
        buffer++;
    }

    char* tmp = (char*) malloc(sizeof(char) * buffer); 

    int posAtual;
    String* final = (String*) malloc(sizeof(String));
    for(posAtual = 0; posAtual < buffer; posAtual++) {
        tmp[posAtual] = entrada[posAtual];
    }
    final->string = tmp;
    final->length = buffer-1;

    return final;
}


String* stringBuilderX(char entrada[], int tamanhoString){  //Constroi o tipo String, já com o tamanho definido (NÃO RECOMENDADO, USO EXPERIMENTAL)

    String* final;
    final->string = entrada;
    final->length = tamanhoString;

    return final;
}


void escreverString(String* entrada){  //Escreve uma String na tela
    
    if(entrada){  //Se a String existir em memoria escreva
        debug("escreverString: Variavel encontrada em memoria!\n");
        for(int i = 0; i < entrada->length; i++){
            printf("%c", entrada->string[i]);
        }
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


bool palindromo(String* entrada){  //Retorna 1 se for palindromo, caso contrario 0
    bool ehPalindromo = 1;

    int j = entrada->length;
    for(int i = 0; i < entrada->length; i++, j--){
        if(entrada->string[i] != entrada->string[j]){
            ehPalindromo = 0;
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

    String* cifra = stringBuilderX(mensagemCifrada, entrada->length);

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