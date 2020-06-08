class JogoDasPilhas {
    public static void main(String[] args) {
        int numRepeticoes = MyIO.readInt();
        Jogo jogo;

        while(numRepeticoes != 0) {
            jogo = new Jogo(numRepeticoes);
            for(int i = 0; i < numRepeticoes*3; i++) {
                jogo.numeros[i] = MyIO.readInt();
            }

            numRepeticoes = MyIO.readInt();
            System.out.println(jogo.jogar());
            jogo = null;
        }
    }
}

class Jogo {
    public int[] numeros;
    public int numJogadas;
    
    public Jogo(int numJogadas) {
        this.numeros = new int[numJogadas*3];
        this.numJogadas = numJogadas;
    }

    // Jogar o jogo da pilha
    public int jogar() {
        int resp = 0;
        int soma = 0;

        for(int i = 0; i < numJogadas; i++) {
            for(int j = 2; j >= 0; j--) {
                
                soma += this.numeros[(i*3)+j];
                
                if(soma % 3 == 0 && soma != 0) {
                    resp = 1;
                    i = numJogadas;
                    j = -1;
                }
            }
            soma = 0;
        
        }

        return resp;
    }
}