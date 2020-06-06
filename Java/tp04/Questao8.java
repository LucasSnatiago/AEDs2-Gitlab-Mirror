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

            try{
                trie.inserir(personagens.getNome().trim());
            } catch(Exception err){ System.err.println(err); }

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
        Trie trie2 = new Trie();
        
        while(!ehFim(entrada)){

            try{
                trie2.inserir(personagens.getNome().trim());
            } catch(Exception err){ System.err.print(""); }

            entrada = MyIO.readLine();
            arq = null;
            personagens = null;
            if(!ehFim(entrada)){
                arq = new Arquivo();
                arq.lerArquivo(entrada);
                personagens = new Personagem(arq.texto);
            }
        }    

        String nomes[] = trie2.mostrar();
        for(String i : nomes) {
            try {
                trie.inserir(i.trim());
            } catch(Exception err) { System.out.print(""); }
        }

        entrada = MyIO.readLine();

        while(!ehFim(entrada)) {
            ordenador(trie, entrada);
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
    public static void ordenador(Trie arvore, String nomePersonagem) {
        
        System.out.print(nomePersonagem + " ");
        
        try{
            if(arvore.pesquisar(nomePersonagem)) MyIO.println("SIM");
            else System.out.printf("N%cO\n", (char)195);
        } catch(Exception err) { System.out.print(""); }

    }
}

//Classe Arvore trie
class Trie {
   private No raiz;

   public Trie(){
       raiz = new No();
   }

   public void inserir(String s) throws Exception {
       inserir(s, raiz, 0);
   }

   private void inserir(String s, No no, int i) throws Exception {
       //System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
       if(no.prox[s.charAt(i)] == null){
           //System.out.print("--> criando filho(" + s.charAt(i) + ")");
           no.prox[s.charAt(i)] = new No(s.charAt(i));

           if(i == s.length() - 1){
               //System.out.print("(folha)");
               no.prox[s.charAt(i)].folha = true;
           }else{
               inserir(s, no.prox[s.charAt(i)], i + 1);
           }

       } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
           inserir(s, no.prox[s.charAt(i)], i + 1);

       } else {
           throw new Exception("Erro ao inserir!");
       } 
   }


   public boolean pesquisar(String s) throws Exception {
       return pesquisar(s, raiz, 0);
   }

   public boolean pesquisar(String s, No no, int i) throws Exception {
       boolean resp;
       if(no.prox[s.charAt(i)] == null){
           resp = false;
       } else if(i == s.length() - 1){
           resp = (no.prox[s.charAt(i)].folha == true);
       } else if(i < s.length() - 1 ){
           resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
       } else {
           throw new Exception("Erro ao pesquisar!");
       }
       return resp;
   }


   public String[] mostrar(){
       String[] nomes = new String[100];
       mostrar("", raiz, 0, nomes);
       return nomes;
   }

   public void mostrar(String s, No no, int pos, String[] nomes) {
       if(no.folha == true){
           nomes[pos] = (s + no.elemento);
       } else {
           for(int i = 0; i < no.prox.length; i++){
               if(no.prox[i] != null){
                   mostrar(s + no.elemento, no.prox[i], pos+1, nomes);
               }
           }
       }
   }

   public int contarAs(){
       int resp = 0;
       if(raiz != null){
           resp = contarAs(raiz);
       }
       return resp;
   }

   public int contarAs(No no) {
       int resp = (no.elemento == 'A') ? 1 : 0;

       if(no.folha == false){
           for(int i = 0; i < no.prox.length; i++){
               if(no.prox[i] != null){
                   resp += contarAs(no.prox[i]);
               }
           }
       }
       return resp;
   }
}

//Classe No arvore trie

class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;
    
    public No (){
       this(' ');
    }
 
    public No (char elemento){
       this.elemento = elemento;
       prox = new No [tamanho];
       for (int i = 0; i < tamanho; i++) prox[i] = null;
       folha = false;
    }
 
    public static int hash (char x){
       return (int)x;
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