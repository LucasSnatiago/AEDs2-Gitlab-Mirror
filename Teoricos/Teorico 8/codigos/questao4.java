//Funcao que retorna se a arvore tem algum multiplo de 11
public boolean multiplo11(Arvore A) {
  return _multiplo11(A.raiz, false);
}

//Funcao que percorre a arvore verificando se algum elemento eh divisivel por 11
public boolean _multiplo11(No i, boolean resp) {

  if(i.elemento % 11 == 0) resp = true;

  if(!resp && i.esq != null) resp = _multiplo11(i.esq, resp);
  if(!resp && i.dir != null) resp = _multiplo11(i.dir, resp);

  return resp;
}
