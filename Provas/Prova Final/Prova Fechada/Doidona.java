// Metodo de pesquisar um elemento dentro de uma estrutura doidona
public boolean pesquisarDoidona(int chave) {
  boolean elementoEncontrado = false;

  // Encontrar a primeira tabela
  Celula t1 = this.tabela;

  // Primeira parte da pesquisar
  // Descobrir posicao da primeira parte da pesquisa
  int pos1 = chave % t1.tamT1;

  // Pesquisando no arranjo t1
  if(t1[pos1].elemento == chave) elementoEncontrado = true;
  else {

    // Pesquisando no arranjo T2
    HashT2 t2 = t1[pos1].t2;

    // Hash na segunda Tabela
    int pos2 = chave % t2.tam2;
    // Rehash na segunda Tabela
    int pos2Reserva = ++chave % t2.tam2;
    if(t2[pos2].elemento == chave || t2[pos2Reserva].elemento == chave) elementoEncontrado = true;
    else {

      // Indo para a tabela T3
      Celula t3Lista = t2.primeiroT3;
      Celula t3Arvore = t2.raizT3;

      // Encontrando posicao na tabela T3
      int pos3 = chave % 2;

      // Verificando se sera inserido em uma lista ou uma arvore
      if(pos == 0){

        // Pesquisando em uma lista
        while(t3Lista != null) {
          if(t3Lista.elemento == chave) elementoEncontrado = true;
          t3Lista = t3Lista.prox;

        }

      // Pesquisando em uma Arvore Binaria
      } else {

        // Deslocando a arvore
        while(t3Arvore != null && !elementoEncontrado) {
          if(t3Arvore.elemento < chave) {
            t3Arvore = t3Arvore.esq;

          } else if(t3Arvore.elemento > chave) {
            t3Arvore = t3Arvore.dir;

          } else elementoEncontrado = true;

        } // Fim da pesquisa em arvore binaria
      } // Fim da entrada em uma arvore binaria
    } // Fim da pesquisa em uma Tabela Hash T2 area de reserva
  } // Fim da pesquisa em uma Tabela Hash T2

  return elementoEncontrado;
}

// A complexidade de codigo é O(1) + O(1) + O(n) ou O(1) + O(1) + O(lg(n))
// Dessa forma o pior caso = O(n)
// E o melhor caso = O(1)
