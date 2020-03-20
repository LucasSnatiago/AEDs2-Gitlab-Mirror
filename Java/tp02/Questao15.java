import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;

public class Questao13 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        Arquivo arq = new Arquivo();
        arq.lerArquivo(entrada);
        Personagem personagens = new Personagem(arq.texto);
        AlgoritmoOrdenacao ps = new AlgoritmoOrdenacao(100);


        while(!ehFim(entrada)){

            ps.inserir(personagens);

            entrada = MyIO.readLine();
            arq = null;
            personagens = null;
            if(!ehFim(entrada)){
                arq = new Arquivo();
                arq.lerArquivo(entrada);
                personagens = new Personagem(arq.texto);
            }
        }

        Arquivo log = new Arquivo();
        log.cronometroStart();
        ps.ordenar(log);
        log.cronometroStop();

        log.criarLog("matrícula_countingsort.txt");
        ps.consertarNomes();
        ps.escreverLista();
        
    
    }

    public static String[] splitString(String entrada, char corte){  //Separar uma String em varias
        String[] saida = new String[entrada.length()];
        int posSaida = 0;
        char[] tmp = new char[entrada.length()];
        int posTmp = 0;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == corte){
                saida[posSaida] = new String(tmp);
                posTmp = 0;
                posSaida++;
            }
            else{
                tmp[posTmp] = entrada.charAt(i);
                posTmp++;
            }
            saida[posSaida] = new String(tmp);
        }

        return saida;
    }


    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M')
            fim = false;
        return fim;
    } 
}


class Ordenador{
    protected Personagem[] lista;
    protected int numElementos;
    protected int tamMax;

    public Ordenador(int tam){  //Instanciar Ordenardor delimitando numero de elementos na lista
        this.lista = new Personagem[tam];
        this.tamMax = tam;
        this.numElementos = 0;
    }

    public Ordenador(){  //Instanciar Ordenador com numero padrao de tamanho
        this.lista = new Personagem[100];
        this.tamMax = 100;
        this.numElementos = 0;
    }

    protected void swiftEsq(int pos){  //Mover todos os elementos da lista para a esquerda ate a pos

        if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro no swift: Posicao invalida!");
        else{
            for(int i = pos; i < this.numElementos; i++){
                this.lista[i] = this.lista[i+1];
            }
            
            this.numElementos--;
        }

    }

    protected void swiftDir(int pos){  //Mover todos os elementos da lista para a direita da pos

        if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro no swift: Posicao invalida!");
        else{
            for(int i = this.numElementos-1; i >= pos; i--){
                this.lista[i+1] = this.lista[i]; 
            }
            
            this.numElementos++;
        }

    }

    public void inserir(Personagem personagem){  //Funcao para inserir um Personagem no final da lista
        this.lista[this.numElementos] = personagem;
        this.numElementos++;
    }  

    public boolean pesquisa(String nomePesquisa, Arquivo log){  //Pesquisar se existe um personagem na lista com o nome igual a nomePesquisa
        boolean saida = false;

        for(int i = 0; i < this.numElementos; i++){
            log.numMovimentacoes++;
            if(this.lista[i].getNome().equals(nomePesquisa)){ 
                saida = true;
                i = this.numElementos;
            }
        }

        return saida;
    }

    //Verificar qual String vem primeiro no alfabeto
    // 0 -> As duas Strings sao iguais
    // 1 -> A primeira String vem primeiro
    // 2 -> A segunda String vem primeiro
    public int verificarOrdemAlfabetica(String A, String B){
        int alfabeto = 0;
        int tamA = A.length();
        int tamB = B.length();

        int menor = tamA;
        if(menor > tamB) menor = tamB;

        for(int i = 0; i < menor; i++){
            
            if(A.charAt(i) < B.charAt(i)){
                alfabeto = 1;
                i = menor;
            }

            else if(A.charAt(i) > B.charAt(i)){
                alfabeto = 2;
                i = menor;
            }

        }
    

        return alfabeto;
    }

    //Verificar se os nomes estao ordenados
    public boolean verficarOrdenadoNome(){
        boolean ordenado = true;

        for(int i = 0; i < this.numElementos-1; i++){
            if(verificarOrdemAlfabetica(this.lista[i].getNome(), this.lista[i + 1].getNome()) == 2){
                i = this.numElementos-1;
                ordenado = false;
            }
        }

        return ordenado;
    }
    
    //Trocar a posicao de dois personagens da lista
    protected void swap(Personagem A, Personagem B){
        Personagem tmp = A;
        A = B;
        B = tmp;
    }

    //Escrever a ordem da lista
    public void escreverLista(){
        for(int i = 0; i < this.numElementos; i++)
            MyIO.println(this.lista[i].toString());
    }

}


class AlgoritmoOrdenacao extends Ordenador {

    public AlgoritmoOrdenacao(){
        super();
    }

    public AlgoritmoOrdenacao(int tam){
        super(tam);
    }

    public int getMaior() {
        double maior = this.lista[0].getPeso();
 
        for (int i = 0; i < this.numElementos; i++) {
            if(maior < this.lista[i].getPeso()){
                maior = this.lista[i].getPeso();
            }
        }

        return maior;	
     }
 
     //Ordenar usando countingSort
    public void ordenar(Arquivo log) {
        //Array paralelo para contar os elementos
        Personagem[] count = new Personagem[(int)getMaior() + 2];
        Personagem[] ordenado = new Personagem[this.numElementos];
 
        //Inicializar cada posicao do array de contagem 
        for (int i = 0; i < count.length; count[i] = 0, i++);
 
        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < this.numElementos; count[this.lista[i]]++, i++);
 
        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for(int i = 1; i < count.length; count[i].peso(count[i].getPeso() + count[i-1].getPeso()), i++);
 
        //Ordenando
        for(int i = this.numElementos-1; i >= 0; ordenado[count[array[i]]-1] = array[i], count[array[i]]--, i--);
 
        //Copiando para o array original
        for(int i = 0; i < n; array[i] = ordenado[i], i++);
    }

    public void consertarNomes(){

        for (int i = 1; i < this.numElementos; i++) {
            Personagem tmp = this.lista[i];
            int j = i - 1;

            while ((j >= 0) && verificarOrdemAlfabetica(this.lista[j].getNome(), tmp.getNome()) == 2 && (this.lista[j].getPeso() == tmp.getPeso())) {
                this.lista[j + 1] = this.lista[j];
                j--;
            }

            this.lista[j + 1] = tmp;
        }

    }

}

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
        if(compararStrings(elemento, "height") && compararStrings(valor, "unknown")) this.altura = -1;
        else if(compararStrings(elemento, "mass") && compararStrings(valor, "unknown")) this.peso = -1;
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
        this.startClock = clock.millis()*1000;
    }

    public void cronometroStop(){  //Finalizar um cronometro
        Clock clock = Clock.systemDefaultZone();
        this.stopClock = clock.millis()*1000;
    }

    public long calcularTempo(){  //Calcula o tempo entre dois clocks em milisegundos
        return (this.stopClock - this.startClock);
    }

}