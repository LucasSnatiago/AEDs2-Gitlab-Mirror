#include <iostream>
#include <string>
#include "Erros.hpp"
#include "NoContato.hpp"

//Criacao de uma Agenda em C++
class Agenda : public NoContato {
public:
    NoContato raiz;

    //Funcao para mostrar todos os contatos da Agenda
    //void mostrar() {
        
    //}
};


int main(int argc, char *argv[]) {
    Agenda agenda;

    agenda.raiz.inserir(argv[1], argv[2], atoi(argv[3]), atoi(argv[4]));

    agenda.raiz.mostrar();
    agenda.raiz.mostrar();  
    agenda.raiz.mostrar();  
}