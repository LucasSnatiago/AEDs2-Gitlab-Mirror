import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;

public class questao2 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        Arquivo arq = new Arquivo();
        arq.lerArquivo(entrada);
        Personagem personagens = new Personagem(arq.texto);
        //Arvore ps = new Arvore();

        int[] seed = {7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14};
        Arvores arvores = new Arvores(seed);

        while(!ehFim(entrada)){

            arvores.inserir(personagens);

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

//Classe Arvore de Arvores
class Arvores extends NoArvore {
    public NoArvore raiz;

    //Contrutor de uma Arvore de Arvores
    public Arvores() {
        this.raiz = null;
    }

    //Seed de criacao de arvore de arvores
    public Arvores(int[] seed) {
        for(int i : seed) _seed(i);
    }

    //Inserir um personagem na arvore de arvores
    public void inserir(Personagem personagem) {
        int num = (int) personagem.getAltura() % 15;

        NoArvore i = _pesquisarArvore(num);
        System.err.println(i);

        if(i.arvore == null){
            i.arvore = new Arvore();
            i.arvore.inserir(personagem);

        } else i.arvore.inserir(personagem);

    }

    //Pesquisar se um elemento existe em um arvore
    public boolean pesquisar(String nome) {
        return _pesquisar(this.raiz, nome);
    }

    //Pesquisando um elemento nas arvores
    private boolean _pesquisar(NoArvore i, String nome) {
        boolean achar = false;

        

        return achar;
    }

    //Pesquisar uma seed dentro de uma arvore
    private NoArvore _pesquisarArvore(int pesquisa) {
        return _pesquisando(this.raiz, pesquisa);
    }

    //Percorrer a arvore 
    private NoArvore _pesquisando(NoArvore i, int num) {
        NoArvore resp = null;

        if(i.esq != null) resp = _pesquisando(i.esq, num);
        if(i.dir != null) resp = _pesquisando(i.dir, num);
        
        if(i.seed == num) resp = i;

        return resp;
    }

    //Criar um galho na arvore
    private void _seed(int num) {
        if(this.raiz == null){
            this.raiz = new NoArvore(num);
            this.raiz.seed = num;
        } else if(num < this.raiz.seed) {
            this.raiz = _inserirSeed(this.raiz.esq, num);
        } else if(num > this.raiz.seed) {
            this.raiz = _inserirSeed(this.raiz.dir, num);
        }
    }

    //Percorrer arvore
    private NoArvore _inserirSeed(NoArvore i, int num) {
        NoArvore tmp = null;

        if (i == null) tmp = new NoArvore(num);
        else if (num < i.seed) tmp = _inserirSeed(i.esq, num);
        else if (num > i.seed) tmp = _inserirSeed(i.dir, num);

        return tmp;
    }

}

//Classe No Arvore
class NoArvore {
    protected Arvore arvore;
    protected NoArvore esq, dir;
    protected int seed;
    
    //Construtor vazio no de Arvore
    public NoArvore() {
        this(null, 0);
    }

    //Construtor vazio no de Arvore usando seed
    public NoArvore(int x) {
        this(null, x);
    }

    //Criador do objeto no de Arvore
    public NoArvore(Arvore arvore, int x) {
        this.arvore = arvore;
        this.esq = this.dir = null;
        this.seed = x;
    }
}

//Classe Arvore Binaria
class Arvore extends No {
    public No raiz;

    //Construtor de uma arvore binaria
    public Arvore() {
        this.raiz = null;
    }

    //Insere elementos na ArvoreBinaria
    public void inserir(Personagem person) {
        this.raiz = _inserir(person, this.raiz);
    }

    //Metodo privado de insercao recursiva
    private No _inserir(Personagem person, No i) {
        if (person == null); //System.err.println("Personagem null");
        else if (i == null) i = new No(person);
        else if (_compararElementoPersonagem(person, i.personagem) < 0) i.dir = _inserir(person,i.dir);
        else if (_compararElementoPersonagem(person, i.personagem) > 0) i.esq = _inserir(person,i.esq);
        return i;
    }

    //Funcao para pesquisar um personagem na arvore
    public boolean pesquisar(String nomePersonagem) {
        System.out.print("raiz ");
        return _pesquisar(this.raiz, nomePersonagem);
    }

    //Deslocar na arvore procurando se o elemento existe
    private boolean _pesquisar(No i, String nomePersonagem) {
        boolean encontrar = false;

        if(i == null) encontrar = false;
        else if(_compararElementoPersonagem(i.personagem, nomePersonagem) < 0){ 
            System.out.print("dir ");
            encontrar = _pesquisar(i.esq, nomePersonagem);
        } else if(_compararElementoPersonagem(i.personagem, nomePersonagem) > 0){
            System.out.print("esq ");
            encontrar = _pesquisar(i.dir, nomePersonagem);
        } else encontrar = true;

        return encontrar;
    }

    //Compara elemento de personagem
    private int _compararElementoPersonagem(Personagem A, Personagem B) {
        //if(A == null) System.err.println("A é null!");
        //if(B == null) System.err.println("B é null!");
        //System.err.println(A.getNome() + "|" + B.getNome());
        return A.getNome().compareTo(B.getNome());
    }

    //Compara elemento de personagem
    private int _compararElementoPersonagem(Personagem A, String B) {
        return A.getNome().compareTo(B);
    }
}

//Classe No de arvore binaria
class No {
    protected Personagem personagem;
    protected No esq, dir;

    public No() {
        this(null);
    }

    public No(Personagem person) {
        this.personagem = person;
        this.esq = this.dir = null;
    }
}

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