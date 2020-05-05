//Metodo que transforma uma lista simples e um duplamente encadeada em uma arvore binaria
public No toArvoreBinaria(Celula i, CelulaDupla j) {
  No cabeca = new Celula(i.elemento);
  _toArvoreBinaria(cabeca, i.prox, j);
  return cabeca;
}

private void _toArvoreBinaria(No A, Celula i, CelulaDupla j) {

  if(j != null) A.inserir(j.elemento);
  if(i != null) A.inserir(i.elemento);

  if(i.prox != null || j.prox != null) _toArvoreBinaria(A, i.prox, j.prox);
}
