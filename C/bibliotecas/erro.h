#ifndef ERRO_H
#define ERRO_H
/*
    CRIACAO DO TIPO ERRO EM C
    Criado por Lucas Santiago
    Data de criacao: 06/05/20
    Versao: 1.0.0 - 06/05/20
    Changelog:
*/

#include <err.h>
#define erro(X) errx(1, X)
#define aviso(X) warn(X)
#endif
