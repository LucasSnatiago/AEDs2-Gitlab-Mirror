//Funcao que verifica se duas arvores sao iguais
public static boolean arvoresIguais(Arvore A, Arvore B) {
  return _arvoresIguais(A.raiz, B.raiz);
}

//Funcao para percorrer a arvore e verificar se todos os elementos sao iguais
private static boolean _arvoresIguais(No i, No j) {
  boolean iguais = true;

  if(i.esq != null && j.esq != null) iguais = _arvoresIguais(i.esq, j.esq);
  else if(i.esq != null || j.esq != null) iguais = false;  //Se ao deloscar para a raiz de uma arvore a outra arvore ainda tiver folhas elas nao sao iguais
  if(i.dir != null && j.dir != null) iguais = _arvoresIguais(i.dir, j.dir);
  else if(i.dir != null || j.dir != null) iguais = false;

  if(j == null) iguais = false;
  else if(i.elemento != j.elemento) iguais = false;

  return iguais;
}
