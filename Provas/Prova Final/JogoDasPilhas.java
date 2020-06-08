
// Classe Pilha
class Pilha {
	// Classe de Celula pilha
    class Celula {
        public int elemento;
        public Celula prox;
    
        // Celula para a pilha
        public Celula(int elemento) {
            this.prox = null;
            this.elemento = elemento;
        }
    }

	private Celula topo;

    // Criacao de uma Pilha
	public Pilha(){
		this.topo = null;
	}

	// Metodo de inserir
	public void inserir(int num) {
		Celula tmp = new Celula(num);
		tmp.prox = this.topo;
		this.topo = tmp;
		tmp = null;
	}

	// Somar elementos da pilha
	public int soma(Celula i) {
		int somatorio = 0;
		if(i != null)
			somatorio += i.elemento + soma(i.prox);

		return somatorio;
	}

	// Jogar
    public boolean Jogar() {
		boolean resp = false;
		int resposta = soma(topo);

		if(resposta % 3 == 0) resp = true;
        else resp = false;
		
		return resp;
	}
}

public class JogoDasPilhas {
	public static void main(String[] args) {
		int quant = MyIO.readInt();
		Pilha pilha;

		while(quant != 0) {
			pilha = new Pilha();
			int count = 0;

			while(count < quant * 3) {
				pilha.inserir(MyIO.readInt());
				count++;

			}
			
			if(pilha.Jogar()) MyIO.println("1");
			else MyIO.println("0");
			pilha = null;
			quant = MyIO.readInt();
		}
	}
}