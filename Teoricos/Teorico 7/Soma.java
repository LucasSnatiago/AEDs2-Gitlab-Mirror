public Matriz somar(Matriz matriz) {
    Matriz saida;

    if(this.linha == this.coluna && matriz.linha == matriz.coluna && this.linha == matriz.linha) {
      saida = new Matriz(this.linha, this.coluna);

      Celula i = this.inicio;
      Celula j = matriz.inicio;
      Celula resp = saida.inicio;

      do {
        _somar(i, j, resp);
        i = i.inf;
        j = j.inf;
        resp = resp.inf;
      } while(i != null);
    }

    return saida;
}

private void _somar(Celula i, Celula j, Celula resp) {
  if(i != null){
    resp.elemento = i.elemento + j.elemento;
    _somar(i.prox, j.prox, resp.prox);
  }
}
