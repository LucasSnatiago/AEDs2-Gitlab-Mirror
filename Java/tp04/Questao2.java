import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;

public class Questao2 {
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
        //arvore.mostrar();
        if(arvore.pesquisar(nomePersonagem)) MyIO.println("SIM");
        else System.out.printf("N%cO\n", (char)195);

    }
}


// Classe de arvore binaria
class Arvore { 
    // Classe no de Arvore Binaria
    static class No {
        protected String nomePersonagem;
        protected No ESQ;
        protected No DIR;
        
        public No() {
            this.ESQ = this.DIR = null;
            this.nomePersonagem = "";
        }
    };
    
    protected No raiz;
    
    public Arvore() {
        this.raiz = null;
    }

    // Inserir personagem
    public void inserir(String nome) {
        if(this.raiz == null) {
            this.raiz = new No();
            this.raiz.nomePersonagem = nome;

        } else _inserir(this.raiz, nome);
    }

    // Inserir personagem
    private void _inserir(No i, String nome) {
        if(nome.compareTo(i.nomePersonagem) < 0) {
            if(i.ESQ == null) {
                i.ESQ = new No();
                i.ESQ.nomePersonagem = nome;
            
            } else _inserir(i.ESQ, nome);
        
        } else if(nome.compareTo(i.nomePersonagem) > 0) {
            if(i.DIR == null) {
                i.DIR = new No();
                i.DIR.nomePersonagem = nome;

            } else _inserir(i.DIR, nome);

        }
    }
    
    // Pesquisar elemento na Arvore
    public boolean pesquisar(String nome) {
        return _pesquisar(this.raiz, nome);
    }

    // Pesquisar elemento na Arvore
    private boolean _pesquisar(No i, String nome) {
        boolean achou = true;

        if(i == null) achou = false;

        else if(nome.compareTo(i.nomePersonagem) < 0) {
            MyIO.print("ESQ ");
            achou = _pesquisar(i.ESQ, nome);

        } else if (nome.compareTo(i.nomePersonagem) > 0) {
            MyIO.print("DIR ");
            achou = _pesquisar(i.DIR, nome);

        }
        
        return achou;
    }
}

// Classe Arvore de Arvores
class Arvores {
    // Classe no da Arvore
    static class NoArvores {
        protected Arvore raiz;
        protected NoArvores esq;
        protected NoArvores dir;
        protected int semente;

        // Construtor de No de Arvores
        public NoArvores() {
            this(0);
        }

         // Construtor de No de Arvores
         public NoArvores(int seed) {
            this.raiz = null;
            this.esq = this.dir = null;
            this.semente = seed;
        }
    };

    protected NoArvores raiz;

    public Arvores() {
        this.raiz = null;
    }

    // Criando uma arvore binaria usando uma seed
    public Arvores(int[] seed) {
        this.raiz = new NoArvores(seed[0]);
        for(int i : seed) _seed(this.raiz, i);
    }

    // Criando uma arvore a partir de uma seed
    private void _seed(NoArvores i, int seed) {
        if(seed < i.semente) {
            if(i.esq == null) i.esq = new NoArvores(seed);
            else _seed(i.esq, seed);

        } else if(seed > i.semente) {
            if(i.dir == null) i.dir = new NoArvores(seed);
            else _seed(i.dir, seed);

        }
    }

    // Inserir elemento na arvore
    public void inserir(Personagem personagem) {
        _inserir(this.raiz, personagem.getNome(), (int) personagem.getAltura() % 15);    
    }

    private void _inserir(NoArvores i, String nome, int pos) {

        if(i.esq != null) _inserir(i.esq, nome, pos);
        if(i.dir != null) _inserir(i.dir, nome, pos);

        if(i != null && i.semente == pos) {
            if(i.raiz == null) i.raiz = new Arvore();
            i.raiz.inserir(nome);
        
        }
    }

    // Pesquisar elemento na arvore
    public boolean pesquisar(String nome) {
        MyIO.print("raiz ");
        return _pesquisar(this.raiz, nome);

    }

    // Pesquisar um elemento nas Arvores
    private boolean _pesquisar(NoArvores i, String nome) {
        boolean achar = false;

        if(i.esq != null) {
            MyIO.print("esq ");
            if(i.raiz != null && i.raiz.pesquisar(nome)) achar = true;
            else achar = _pesquisar(i.esq, nome);

        }
        
        if(i.dir != null) {
            MyIO.print("dir ");
            if(i.raiz != null && i.raiz.pesquisar(nome)) achar = true;
            else achar = _pesquisar(i.dir, nome);

        }

        return achar;
    }

    // Mostrar elementos da Arvore
    public void mostrar() {
        MyIO.print("raiz ");
        _mostrar(this.raiz);
    }

    private void _mostrar(NoArvores i) {
        if(i.esq != null) {
            MyIO.print("esq ");
            if(i.raiz != null) i.raiz.mostrar();
            _mostrar(i.esq);

        }
        
        if(i.dir != null) {
            MyIO.print("dir ");
            if(i.raiz != null) i.raiz.mostrar();
            _mostrar(i.dir);

        }
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