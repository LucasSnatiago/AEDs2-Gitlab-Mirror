//Funcao para inserir na arvore como void
public void inserir(int x) {
  if(this.raiz == null) {
    this.raiz = new No(x);
  } else _inserir(x, this.raiz, this.raiz);
}

//Funcao para percorrer a arvore e inserir o elemento
private void _inserir(int x, No i, No pai) {

  if(i == null) {
    if(x < i.elemento) pai.esq = new No(i);
    else pai.dir = new No(i);
  } else if(x < i.elemento) _inserir(x, i.esq, i);
  else if(x > i.elemento) _inserir(x, i.dir, i);
  else if(x == i.elemento) System.err.println("Elemento não inserido! Elemento já existente na arvore!");
}
