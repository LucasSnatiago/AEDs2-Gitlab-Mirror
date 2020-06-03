#include <stdio.h>
#include <stdlib.h>

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
#include "../../bibliotecas/Celula.h"
#include "../../bibliotecas/PilhaFlexivel.h"

//Criar um personagem a partir de uma path
Personagens* personagemCarregado(String* entrada) {
    return carregarPersonagem(lerArquivo(entrada));
}

//Executar
void ordenar(PilhaFlexivel* pilha, String** texto) {
  if(strcmp(texto[0]->string, "I") == 0){
    empilhar(pilha, personagemCarregado(texto[1]));
  } else if(strcmp(texto[0]->string, "R") == 0) {
    desempilhar(pilha);
  }
}

int main(void){

    PilhaFlexivel* pilha = new_pilhaFlexivel();
    String *entrada = readString();

    while(!ehFimString(entrada)){

        empilhar(pilha, personagemCarregado(entrada));
        entrada = readString();

    }

    int repeticoes = string2Int(readString());
    for(int i = 0; i < repeticoes; i++) {
      ordenar(pilha, splitString(readString(), ' '));
    }

    escreverPilhaFlexivelInvertida(pilha);

    return 0;
}
