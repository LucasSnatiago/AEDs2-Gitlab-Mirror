#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

//Minhas Bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/lerArquivos.h"
#include "../../bibliotecas/padroes.h"
#include "../../bibliotecas/Personagens.h"
#include "../../bibliotecas/AlocacaoSequencial.h"


int main(void){

    
    String* path = readString();
    String* conteudoArquivo = lerArquivo(path);
    Personagens* personagem;
    Ordenador* elementos = criarLista();
    Ordenador* listaOrdenada = criarLista();
    
    int numElementos = 0;
    while(!ehFimString(path)){
    
        personagem = carregarPersonagem(conteudoArquivo);
        elementos->ordem[numElementos] = personagem;
        numElementos++;

        freeString(&path);
        freeString(&conteudoArquivo);
        path = readString();
        if(!ehFimString(path)) conteudoArquivo = lerArquivo(path);
    }
    
    for(int i = 0; i < numElementos; i++)
    escreverPersonagens(elementos->ordem[i]);

    return 0;
}
