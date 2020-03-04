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


int main(void){

    
    String* path = readString();
    String* conteudoArquivo = lerArquivo(path);
    Personagens* personagem;
    
    while(!ehFimString(path)){
    
        personagem = carregarPersonagem(conteudoArquivo);
        escreverPersonagens(personagem);
    
        freePersonagem(&personagem);
        freeString(&path);
        freeString(&conteudoArquivo);
        path = readString();
        if(!ehFimString(path)) conteudoArquivo = lerArquivo(path);
    }
    

    return 0;
}
