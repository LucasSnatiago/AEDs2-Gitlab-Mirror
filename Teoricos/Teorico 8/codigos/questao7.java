//Funcao para remover um elemento da arvore
public void remover(int x) {
  No i = this.raiz;

  if(i == null) System.err.println("Impossivel remover de arvore vazia!");
  remover(x, i, i);


}

private void remover(int x, No pai, No filho) {
  if(filho != null) {
    if(x < filho.elemento)
  } else{
    System.err.println("Elemento não encontrado na arvore!");
  }
}
