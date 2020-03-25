void insercaoPorCor(Ordenador* lista, int cor, int h, int* numComparacoes, int* numMovimentacoes){
   for (int i = (h + cor); i < lista->numElementos; i+=h) {
      Personagens* tmp = lista->ordem[i];
      int j = i - h;
      while ((j >= 0) && (lista->ordem[j]->peso > tmp->peso)) {
         lista->ordem[j + h] = lista->ordem[j];
         j-=h;
         numComparacoes+=2;
         numMovimentacoes++;
      }
      lista->ordem[j + h] = tmp;
      numMovimentacoes++;
   }
}

// Algoritmo de ordenacao
void shellsort(Ordenador* lista, int* numComparacoes, int* numMovimentacoes) {
   int h = 1;

   do { h = (h * 3) + 1; numComparacoes++; } while (h < lista->numElementos);

   do {
      h /= 3;
      for(int cor = 0; cor < h; cor++){
         insercaoPorCor(lista, cor, h, numComparacoes, numMovimentacoes);
      }
   } while (h != 1);
}
