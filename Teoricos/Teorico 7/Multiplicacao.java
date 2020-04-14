public Matriz multiplicacao(Matriz matriz) {
  Matriz saida;

  if(this.linha == this.coluna && matriz.linha == matriz.coluna && this.linha == matriz.linha) {
    saida = new Matriz(this.linha, this.coluna);

    Celula i = this.inicio;
    Celula j = matriz.inicio;
    Celula resp = saida.inicio;

    do {
      _multiplicar(i, j, resp);
      i = i.inf;
      j = j.inf;
      resp = resp.inf;
    } while(i != null);
  }

  return saida;
}

private void _multiplicar(Celula i, Celula j, Celula resp) {
  if(i != null){
    resp.elemento = i.elemento * j.elemento;
    _multiplicar(i.prox, j.prox, resp.prox);
  }
}
