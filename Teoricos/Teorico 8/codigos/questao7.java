//Funcao para remover um elemento da arvore
public void remover(int x) throws Exception {
  No raiz = this.raiz;

  if(raiz == null) throw new Exception("Impossivel remover de arvore vazia!");
  else if(x < raiz.elemento) remover(x, raiz, raiz.esq);
  else if(x > raiz.elemento) remover(x, raiz, raiz.dir);
  else if(x == raiz.elemento){
    if(raiz.esq != null) raiz = raiz.esq;
    else if(raiz.dir != null) raiz = raiz.dir;
  } else raiz.esq = antecessor(raiz, raiz.esq);

}

//Funcao privada para remover elementos
private void remover(int x, No pai, No filho) {
  if(filho != null) {
    if(x < filho.elemento) remover(x, filho, filho.esq);
    else if(x > filho.elemento) remover(x, filho, filho.dir);
    else{
      if(filho.esq != null) pai.esq = filho.esq;
      else if(filho.dir != null) pai.dir = filho.dir;
      else filho.esq = antecessor(filho, filho.esq);
    }
  } else {
    System.err.println("Elemento nao encontrado na arvore!");
  }
}
