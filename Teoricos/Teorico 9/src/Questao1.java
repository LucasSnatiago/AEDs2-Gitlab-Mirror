/**
* Metodo para retornar elemento removido de uma arvore
* @param x valor para ser removido da arvore
* @return Elemento presente na arvore
*/
public int remover3(int x) throws Exception {
  int saida = -1;
  Celula tmp = this.raiz;

  if(tmp == null) throw new Exception("Erro ao remover de arvore vazia!");
  else if(x < tmp.elemento) saida = _remover3(x, tmp.esq, tmp);
  else if(x > tmp.elemento) saida = _remover3(x, tmp.dir, tmp);
  else if(tmp.dir == null) {
    saida = tmp.elemento;
    this.raiz = this.raiz.esq;

  } else if(tmp.esq == null) {
    saida = tmp.elemento;
    this.raiz = this.raiz.dir;

  } else {
    saida = tmp.elemento;
    this.raiz.esq = antecessor(tmp, tmp.esq);

  }
  return saida;
}

/**
* @param x Elemento para ser removido da arvore
* @param i No que estao sendo pesquisado no momento
* @param pai No pai do No i
* @return Elemento a ser removido
*/
private int _remover3(int x, No i, No pai) throws Exception {
  int saida = 0;

  if(i == null) throw new Exception("Elemento nao encontrado na arvore!");
  else if(x < i.elemento) saida = _remover3(x, i.esq, i);
  else if(x > i.elemento) saida = _remover3(x, i.dir, i);
  else if(i.dir == null) {
    saida = i.elemento;

    if(pai.esq == i) {
      pai.esq = i.esq;

    } else {
      pai.dir = i.esq;

    }
  } else if(i.esq == null) {
    saida = i.elemento;

    if(pai.esq == i) {
      pai.esq = i.dir;

    } else {
      pai.dir = i.dir;

    }
  } else {
    resp = i.elemento;
    i.esq = antecessor(i, i.esq);

  }
  
  return saida;
}
