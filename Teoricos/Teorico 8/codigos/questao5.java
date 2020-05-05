//Metodo que transforma uma lista simples e um duplamente encadeada em uma arvore binaria
public No toArvoreBinaria(Celula i, CelulaDupla j) {
  No cabeca = new Celula(i);
  _toArvoreBinaria(cabeca, i.prox, j);
  return cabeca;
}

private void _toArvoreBinaria(No A, Celula i, CelulaDupla j) {

}
