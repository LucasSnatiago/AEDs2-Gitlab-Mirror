import re
import sys
import os


nomeArquivoEntrada = sys.argv[1]
if not os.path.exists(nomeArquivoEntrada):
    raise FileNotFoundError("Arquivo inexistente!")


nomeArquivo = '.'.join(nomeArquivoEntrada.split('.')[:-1])
extensaoArquivo = nomeArquivoEntrada.split(".")[-1]

print(nomeArquivo)
print(extensaoArquivo)

nomeArquivoSaida = str(nomeArquivo) + str(".cpp")

arquivoSaida = open(nomeArquivoSaida, 'w')


