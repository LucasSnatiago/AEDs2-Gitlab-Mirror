/*
    CRIACAO DA ESTRUTURA DE PERSONAGENS EM C
    Criado por Lucas Santiago
    Data de criacao: 01/03/20
    Versao: 1.0.0
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define TAM 10000

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

//Estrutura de armazenamento de Personagens
typedef struct personagens{
    String* nome;
    int altura;
    double peso;
    String* corDoCabelo;
    String* corDaPele;
    String* corDosOlhos;
    String* anoNascimento;
    String* genero;
    String* homeworld;
}Personagens;

    void _setNome(String* entrada, Personagens* personagem){
        personagem->nome = entrada;
    }

    void _setAltura(int entrada, Personagens* personagem){
        personagem->altura = entrada;
    }

    void _setPeso(double entrada, Personagens* personagem){
        personagem->peso = entrada;
    }

    void _setCorDoCabelo(String* entrada, Personagens* personagem){
        personagem->corDoCabelo = entrada;
    }

    void _setCorDaPele(String* entrada, Personagens* personagem){
        personagem->corDaPele = entrada;
    }

    void _setCorDosOlhos(String* entrada, Personagens* personagem){
        personagem->corDosOlhos = entrada;
    }

    void _setAnoNascimento(String* entrada, Personagens* personagem){
        personagem->anoNascimento = entrada;
    }

    void _setGenero(String* entrada, Personagens* personagem){
        personagem->genero = entrada;
    }

    void _setHomeworld(String* entrada, Personagens* personagem){
        personagem->homeworld = entrada;
    }

    String* getNome(Personagens* personagem){
        return personagem->nome;
    }

    int getAltura(Personagens* personagem){
        return personagem->altura;
    }

    double getPeso(Personagens* personagem){
        return personagem->peso;
    }

    String* getCorDoCabelo(Personagens* personagem){
        return personagem->corDoCabelo;
    }

    String* getCorDaPele(Personagens* personagem){
        return personagem->corDaPele;
    }

    String* getCorDosOlhos(Personagens* personagem){
        return personagem->corDosOlhos;
    }

    String* getAnoNascimento(Personagens* personagem){
        return personagem->anoNascimento;
    }

    String* getGenero(Personagens* personagem){
        return personagem->genero;
    }

    String* getHomeworld(Personagens* personagem){
        return personagem->homeworld;
    }

void _loadPersonagem(char elemento[], char valor[], Personagens* personagem){  //Funcao para direcionar a escrita da chave / valor
    debugcompleto("Elemento: %s    Valor: %s\n", elemento, valor);

    char tmp[_bufferSize(valor)];
    int posTmp = 0;
    String* elementoS = stringBuilder(elemento);
    String* valorS = stringBuilder(valor);

    String* name = stringBuilder((char*)"name");
    String* mass = stringBuilder((char*)"mass");
    String* height = stringBuilder((char*)"height");
    String* hair_color = stringBuilder((char*)"hair_color");
    String* skin_color = stringBuilder((char*)"skin_color");
    String* eye_color = stringBuilder((char*)"eye_color");
    String* birth_year = stringBuilder((char*)"birth_year");
    String* gender = stringBuilder((char*)"gender");
    String* homeworld = stringBuilder((char*)"homeworld");
    String* unknown = stringBuilder((char*)"unknown");

    if(compararStrings(elementoS, height) && compararStrings(valorS, unknown)) personagem->altura = -1;
    else if(compararStrings(elementoS, mass) && compararStrings(valorS, unknown)) personagem->peso = -1;
    else if(compararStrings(elementoS, name)) _setNome(valorS, personagem);
    else if(compararStrings(elementoS, height)) _setAltura(atoi(valorS->string), personagem);
    else if(compararStrings(elementoS, hair_color)) _setCorDoCabelo(valorS, personagem);
    else if(compararStrings(elementoS, skin_color)) _setCorDaPele(valorS, personagem);
    else if(compararStrings(elementoS, eye_color)) _setCorDosOlhos(valorS, personagem);
    else if(compararStrings(elementoS, birth_year)) _setAnoNascimento(valorS, personagem);
    else if(compararStrings(elementoS, gender)) _setGenero(valorS, personagem);
    else if(compararStrings(elementoS, homeworld)) _setHomeworld(valorS, personagem);
    else if(compararStrings(elementoS, mass)){
        for(int i = 0; i < valorS->length; i++){
            if(valorS->string[i] != ',') {
                tmp[posTmp] = valorS->string[i];
                posTmp++;
            }
            tmp[posTmp]= '\0';
        }
        _setPeso(atof(tmp), personagem);
    }
}

void _proxElemento(char entrada[], Personagens* personagem){  //Dividindo a entrada em chave / valor
    int tamEntrada = _bufferSize(entrada);
    char elemento[tamEntrada];
    int posElemento = 0;
    char valor[tamEntrada];
    int posValor = 0;
    int parte = 1;
    bool aspas = false;

    for(int i = 0; i < tamEntrada; i++){

        if(entrada[i] == ':'){ 
            parte = 2;
            continue;
        }
        if(entrada[i] == '\''){
            aspas = !aspas;
            continue;
        }
        if(parte == 1 && aspas){ 
            elemento[posElemento] = entrada[i];
            posElemento++;
        }
        if(parte == 2 && aspas){
            valor[posValor] = entrada[i];
            posValor++;
        }

    }

    elemento[posElemento] = '\0';
    valor[posValor] = '\0';

    _loadPersonagem(elemento, valor, personagem);
}

void _extrairInformacoes(String* entrada, Personagens* personagem){  //Funcao para retirar as informacoes necessarias do json
    int tam = entrada->length;
    char tmp[tam];
    int posTmp = 0;

    int posI = 1;
    int posF = 1;
    bool aspas = false;
    for(int i = 0; i < tam; i++){

            if(entrada->string[i] == '\'') aspas = !aspas;

            if(entrada->string[i] == ',' && !aspas){
                posF = i;

                for(int j = posI; j < posF; j++){
                    tmp[posTmp] = entrada->string[j];
                    posTmp++;
                }
                tmp[posTmp] = '\0';

                posI = i+2;
                
                _proxElemento(tmp, personagem);
            }

            posTmp = 0;
        }

}

Personagens* carregarPersonagem(String* entrada){  //Funcao para pegar todas as informacoes de um json com personagens
    Personagens* saida = (Personagens*) malloc(sizeof(Personagens));

    _extrairInformacoes(entrada, saida);

    return saida;
}

void freePersonagem(Personagens** personagem){
    free(*personagem);
    personagem = NULL;
}

void escreverPersonagens(Personagens* personagem){  //Funcao para escrever na tela os personagens
    int altura = getAltura(personagem);
    double peso = getPeso(personagem);

    if(personagem->altura == -1) altura = 0;
    if(personagem->peso == -1) peso = 0;

    printf(" ## ");
    escreverStringSemNovaLinha(getNome(personagem));
    printf(" ## ");
    printf("%d", altura);
    printf(" ## ");
    if(peso == (int)peso) printf("%d", (int)peso);
    else printf("%g", peso);
    printf(" ## ");
    escreverStringSemNovaLinha(getCorDoCabelo(personagem));
    printf(" ## ");
    escreverStringSemNovaLinha(getCorDaPele(personagem));
    printf(" ## ");
    escreverStringSemNovaLinha(getCorDosOlhos(personagem));
    printf(" ## ");
    escreverStringSemNovaLinha(getAnoNascimento(personagem));
    printf(" ## ");
    escreverStringSemNovaLinha(getGenero(personagem));
    printf(" ## ");
    escreverStringSemNovaLinha(getHomeworld(personagem));
    printf(" ## \n");
}