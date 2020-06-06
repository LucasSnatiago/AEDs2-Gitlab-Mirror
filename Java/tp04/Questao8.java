import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;

public class Questao8 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        Arquivo arq = new Arquivo();
        arq.lerArquivo(entrada);
        Personagem personagens = new Personagem(arq.texto);
        Trie trie = new Trie();

        while(!ehFim(entrada)){

            trie.insert(personagens);

            entrada = MyIO.readLine();
            arq = null;
            personagens = null;
            if(!ehFim(entrada)){
                arq = new Arquivo();
                arq.lerArquivo(entrada);
                personagens = new Personagem(arq.texto);
            }
        }        

        
        entrada = MyIO.readLine();
        while(!ehFim(entrada)) {
            ordenador(arvores, entrada);
            entrada = MyIO.readLine();
        }    
    }

    //Recortar uma string
    public static String[] splitString(String entrada, String corte){  //Separar uma String em varias
        return entrada.split(corte);
    }

    //Verificar se eh hora de finalizar o programa
    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        return entrada.trim().equals("FIM");
    } 

    //Ordenar execucoes do programa
    public static void ordenador(Arvores arvore, String nomePersonagem) {
        
        System.out.print(nomePersonagem + " ");
        if(arvore.pesquisar(nomePersonagem)) MyIO.println("SIM");
        else MyIO.println("NÃO");

    }
}

//Classe Arvore trie
// Java implementation of search and insert operations 
// on Trie 
class Trie { 
	
	// Alphabet size (# of symbols) 
	static final int ALPHABET_SIZE = 26; 
	
	// trie node 
	static class TrieNode 
	{ 
		TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
	
		// isEndOfWord is true if the node represents 
		// end of a word 
		boolean isEndOfWord; 
		
		TrieNode(){ 
			isEndOfWord = false; 
			for (int i = 0; i < ALPHABET_SIZE; i++) 
				children[i] = null; 
		} 
	}; 
	
	static TrieNode root; 
	
	// If not present, inserts key into trie 
	// If the key is prefix of trie node, 
	// just marks leaf node 
	static void insert(Personagem key) 
	{ 
		int level; 
		int length = key.length(); 
		int index; 
	
		TrieNode pCrawl = root; 
	
		for (level = 0; level < length; level++) 
		{ 
			index = key.charAt(level) - 'a'; 
			if (pCrawl.children[index] == null) 
				pCrawl.children[index] = new TrieNode(); 
	
			pCrawl = pCrawl.children[index]; 
		} 
	
		// mark last node as leaf 
		pCrawl.isEndOfWord = true; 
	} 
	
	// Returns true if key presents in trie, else false 
	static boolean search(String key) 
	{ 
		int level; 
		int length = key.length(); 
		int index; 
		TrieNode pCrawl = root; 
	
		for (level = 0; level < length; level++) 
		{ 
			index = key.charAt(level) - 'a'; 
	
			if (pCrawl.children[index] == null) 
				return false; 
	
			pCrawl = pCrawl.children[index]; 
		} 
	
		return (pCrawl != null && pCrawl.isEndOfWord); 
	} 
	
	// Driver 
	public static void main(String args[]) 
	{ 
		// Input keys (use only 'a' through 'z' and lower case) 
		String keys[] = {"the", "a", "there", "answer", "any", 
						"by", "bye", "their"}; 
	
		String output[] = {"Not present in trie", "Present in trie"}; 
	
	
		root = new TrieNode(); 
	
		// Construct trie 
		int i; 
		for (i = 0; i < keys.length ; i++) 
			insert(keys[i]); 
	
		// Search for different keys 
		if(search("the") == true) 
			System.out.println("the --- " + output[1]); 
		else System.out.println("the --- " + output[0]); 
		
		if(search("these") == true) 
			System.out.println("these --- " + output[1]); 
		else System.out.println("these --- " + output[0]); 
		
		if(search("their") == true) 
			System.out.println("their --- " + output[1]); 
		else System.out.println("their --- " + output[0]); 
		
		if(search("thaw") == true) 
			System.out.println("thaw --- " + output[1]); 
		else System.out.println("thaw --- " + output[0]); 
		
	} 
} 
// This code is contributed by Sumit Ghosh 


//Classe de Personagens do Star Wars
class Personagem {
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    public Personagem(){  //Construtor da classe
        this.nome = null;
        this.altura = -1;
        this.peso = -1;
        this.corDoCabelo = null;
        this.corDaPele = null;
        this.corDosOlhos = null;
        this.anoNascimento = null;
        this.genero = null;
        this.homeworld = null;
    }

    public Personagem(String entrada){  //Construtor da classe com Json
        extrairElementos(entrada);
    }

    private void extrairElementos(String entrada){  //Extrair elementos importantes do Json
        String tmp = "";
        int tamString = entrada.length();

        int posI = 1;
        int posF = 1;
        boolean aspas = false;
        for(int i = 0; i < tamString; i++){

            if(entrada.charAt(i) == '\'') aspas = !aspas;

            if(entrada.charAt(i) == ',' && !aspas){
                posF = i;
                //DEBUG: MyIO.print(posI + " | " + posF  + " | " + i + "     ");
                
                for(int j = posI; j < posF; j++)
                    tmp += entrada.charAt(j);
                
                posI = i+2;
                
                proxElemento(tmp);
            }

            tmp = "";
        }

    }

    private void proxElemento(String entrada){  //Dividindo a String em chave / valor 
        int tamEntrada = entrada.length();
        String elemento = "";
        String valor = "";
        int parte = 1;
        boolean aspas = false;

        for(int i = 0; i < tamEntrada; i++){

            if(entrada.charAt(i) == ':'){ 
                parte = 2;
                continue;
            }
            if(entrada.charAt(i) == '\''){
                aspas = !aspas;
                continue;
            }
            if(parte == 1 && aspas) elemento += entrada.charAt(i);
            if(parte == 2 && aspas) valor += entrada.charAt(i);

        }
        //DEBUG: MyIO.print(elemento + " | " + valor + "\n");

        loadPersonagem(elemento, valor);
    }

    private void loadPersonagem(String elemento, String valor){  //Setando as chaves / valores
        String tmp = "";

        //DEBUG: MyIO.println(elemento + " | " + valor);
        if(compararStrings(elemento, "height") && compararStrings(valor, "unknown")) this.altura = 0;
        else if(compararStrings(elemento, "mass") && compararStrings(valor, "unknown")) this.peso = 0;
        else if(compararStrings(elemento, "name")) setNome(valor);
        else if(compararStrings(elemento, "height")) setAltura(Integer.parseInt(valor));
        else if(compararStrings(elemento, "hair_color")) setCorDoCabelo(valor);
        else if(compararStrings(elemento, "skin_color")) setCorDaPele(valor);
        else if(compararStrings(elemento, "eye_color")) setCorDosOlhos(valor);
        else if(compararStrings(elemento, "birth_year")) setAnoNascimento(valor);
        else if(compararStrings(elemento, "gender")) setGenero(valor);
        else if(compararStrings(elemento, "homeworld")) setHomeworld(valor);
        else if(compararStrings(elemento, "mass")){
            for(int i = 0; i < valor.length(); i++){
                if(valor.charAt(i) != ',') 
                    tmp += valor.charAt(i);
            }
            setPeso(Double.parseDouble(tmp));
        }
    }

    private boolean compararStrings(String A, String B){  //Comparar duas Strings: false -> diferentes / true -> iguais 
        boolean iguais = true;

        int tamA = A.length();
        int tamB = B.length();

        if(tamA != tamB) iguais = false;
        else{

            for(int i = 0; i < tamA; i++){
                if(A.charAt(i) != B.charAt(i)){
                    iguais = false;
                    i = tamA;
                }
            }

        }


        return iguais;
    }

    private void setNome(String entrada){
        this.nome = entrada;
    }

    private void setAltura(int entrada){
        this.altura = entrada;
    }

    private void setPeso(double entrada){
        this.peso = entrada;
    }

    private void setCorDoCabelo(String entrada){
        this.corDoCabelo = entrada;
    }

    private void setCorDaPele(String entrada){
        this.corDaPele = entrada;
    }

    private void setCorDosOlhos(String entrada){
        this.corDosOlhos = entrada;
    }

    private void setAnoNascimento(String entrada){
        this.anoNascimento = entrada;
    }

    private void setGenero(String entrada){
        this.genero = entrada;
    }

    private void setHomeworld(String entrada){
        this.homeworld = entrada;
    }

    public String getNome(){
        return this.nome;
    }

    public int getAltura(){
        return this.altura;
    }

    public double getPeso(){
        return this.peso;
    }

    public String getCorDoCabelo(){
        return this.corDoCabelo;
    }

    public String getCorDaPele(){
        return this.corDaPele;
    }

    public String getCorDosOlhos(){
        return this.corDosOlhos;
    }

    public String getAnoNascimento(){
        return this.anoNascimento;
    }

    public String getGenero(){
        return this.genero;
    }

    public String getHomeworld(){
        return this.homeworld;
    }

    public String toString(){  //Escrever os elementos na tela
        String altura = this.altura + "";
        String peso = this.peso + "";
        if(this.peso == (int)this.peso) peso = (int)this.peso + "";

        if(this.altura == -1) altura = "0";
        if(this.peso == -1) peso = "0";

        return (" ## " + this.nome + " ## " + altura + " ## " + peso + " ## " + 
        this.corDoCabelo + " ## " + this.corDaPele + " ## " + this.corDosOlhos + " ## " +
        this.anoNascimento + " ## " + this.genero + " ## " + this.homeworld + " ## ");
    }
}

class Arquivo {
    public String texto;
    public int numMovimentacoes;
    public int numComparacoes;
    public long startClock;
    public long stopClock;

    public Arquivo(){
        this.numComparacoes = 0;
        this.numMovimentacoes = 0;
    }

    //Criar o objeto arquivo salvando o texto de um arquivo
    public Arquivo(String entrada) {
        this.numComparacoes = 0;
        this.numMovimentacoes = 0;
        this.texto = lerArquivo(entrada);
    }

    public String lerArquivo(String entrada){  //Ler conteudo dentro de um arquivo
        try{
            BufferedReader arqTexto = new BufferedReader(new FileReader(entrada));
            String texto = "";
            String linha = arqTexto.readLine();
            
            while(linha != null){
                texto += linha;
                linha = arqTexto.readLine();
            }

            this.texto = texto;
            arqTexto.close();
        }catch(Exception erro){
            MyIO.println("Houve um erro na leitura do arquivo!");
        }

        return this.texto;
    }

    public void criarLog(String nomeArquivo){  //Funcao para criar o log em arquivo
        
        try{
            
            FileWriter escreverArq = new FileWriter(nomeArquivo);
            
            escreverArq.write("650888\t" + calcularTempo() + "\t" + this.numComparacoes + "\t" + this.numMovimentacoes);
            escreverArq.close();

        }catch(Exception error){
            MyIO.println("Erro ou criar o arquivo de log!");
        }
    }

    public void cronometroStart(){  //Iniciar um cronometro
        Clock clock = Clock.systemDefaultZone();
        this.startClock = clock.millis();
    }

    public void cronometroStop(){  //Finalizar um cronometro
        Clock clock = Clock.systemDefaultZone();
        this.stopClock = clock.millis();
    }

    public long calcularTempo(){  //Calcula o tempo entre dois clocks em milisegundos
        return (this.stopClock - this.startClock);
    }

}