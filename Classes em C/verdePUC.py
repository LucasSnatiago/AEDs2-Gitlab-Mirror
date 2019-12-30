import re
import sys
import os


nomeArquivoEntrada = sys.argv[1]
if not os.path.exists(nomeArquivoEntrada):
    raise FileNotFoundError("Arquivo inexistente!")


nomeArquivo = nomeArquivoEntrada.split('.')
extensaoArquivo = nomeArquivo.pop()
nomeArquivo = '.'.join(nomeArquivo)

nomeArquivoSaida = str(nomeArquivo) + str(".cpp")

arquivoEntrada = open(nomeArquivoEntrada, 'r')
arquivoSaida = open(nomeArquivoSaida, 'w')


for linha in arquivoEntrada:
    

    arquivoSaida.write(linha) 

arquivoEntrada.close()
arquivoSaida.close()
