#include <iostream>
#include <err.h>

//Criacao da classe No em C++
class No {
public:
  No *esq, *dir;
  int elemento;

  //Contrutor da classe vazia de No
  No() {
    this->elemento = 0;
  }

  //Criacao de um No
  No(int elemento) {
    this->elemento = elemento;
    this->esq = this->dir = NULL;
  }
};

//Criacao do tipo Arvore binaria em C++
class Arvore {
public:
  No *raiz;

  //Criando a arvore
  Arvore() {
    this->raiz = NULL;
  }

  //Inserir elemento na Arvore
  void inserir(int x) {
    if(this->raiz == NULL) this->raiz = new No(x);
    else _inserir(this->raiz, x);

  }

  //Implementacao do treeSort em C++
  void treeSort() {
    std::cout << "[ ";
    _treeSort(this->raiz);
    std::cout << "]\n";
  }

private:
  //Percorrer a arvore para inserir um elemento
  void _inserir(No *no, int x) {
    if(x < no->elemento) {
      if(no->esq == NULL) no->esq = new No(x);
      else _inserir(no->esq, x);
    
    } else if(x > no->elemento) {
      if(no->dir == NULL) no->dir = new No(x);
      else _inserir(no->dir, x);

    } else warn("O item já está contido na Arvore, por isso, nao foi inserido!");
  }

  //Percorrendo a arvore e escrevendo ordenado
  void _treeSort(No* no) {
    if(no != NULL) {
      _treeSort(no->esq);
      std::cout << no->elemento << " ";
      _treeSort(no->dir);
    }
  }
};

int main(int argc, char **argv) {
  Arvore arvore;
  int entrada;

  //Inserir elementos na arvore para serem ordenados
  std::cout << "Digite 0 para terminar o programa!\n";
  std::cin >> entrada;
  while(entrada != 0) {
    arvore.inserir(entrada);
    std::cin >> entrada;
  }

  arvore.treeSort();
}
