#include <stdio.h>
#include <stdlib.h>
#include "Selecao.h"


int main(void){

  int valores[100];

  for(int  i = 0; i< 100; i++){
    valores[i] = rand()%100;
  }

  for(int  i = 0; i< 100; i++)
    printf("%d ", numeros[i]);

  selectionSort(valores, 100);

  printf("\n");
  for(int  i = 0; i< 100; i++)
    printf("%d ", numeros[i]);

  return 0;
}
