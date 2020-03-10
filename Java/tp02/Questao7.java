import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;
import java.time.LocalDate;

public class Questao7 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        Arquivo arq = new Arquivo();
        arq.lerArquivo(entrada);
        Personagem personagens = new Personagem(arq.texto);

        while(!ehFim(entrada)){

            

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
        arq = new Arquivo();
        arq.lerArquivo(entrada);
        personagens = new Personagem(arq.texto);
        
        while(!ehFim(entrada)){



            entrada = MyIO.readLine();
            arq = null;
            personagens = null;
            if(!ehFim(entrada)){
                arq = new Arquivo();
                arq.lerArquivo(entrada);
                personagens = new Personagem(arq.texto);
            }
        }

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
    protected Personagem[] pilha;
    protected int numElementos;
    protected int tamMax;

    public Ordenador(int tam){  //Instanciar Ordenardor delimitando numero de elementos na pilha
        this.pilha = new Personagem[tam];
        this.tamMax = tam;
        this.numElementos = 0;
    }

    public Ordenador(){  //Instanciar Ordenador com numero padrao de tamanho
        this.pilha = new Personagem[100];
        this.tamMax = 100;
        this.numElementos = 0;
    }

    protected void swiftEsq(int pos){  //Mover todos os elementos da pilha para a esquerda ate a pos

        if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro no swift: Posicao invalida!");
        else{
            for(int i = pos; i < this.numElementos; i++){
                this.pilha[i] = this.pilha[i+1];
            }
            
            this.numElementos--;
        }

    }

    protected void swiftDir(int pos){  //Mover todos os elementos da pilha para a direita da pos

        if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro no swift: Posicao invalida!");
        else{
            for(int i = this.numElementos-1; i >= pos; i--){
                this.pilha[i+1] = this.pilha[i]; 
            }
            
            this.numElementos++;
        }

    }
    
}


class pesquisaSequencial extends Ordenador {

    public pesquisaSequencial(){
        super();
    }

    public pesquisaSequencial(int tam){
        super(tam);
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

        return (" ## " + this.nome + " ## " + altura + " ## " + 0 + " ## " + 
        this.corDoCabelo + " ## " + this.corDaPele + " ## " + this.corDosOlhos + " ## " +
        this.anoNascimento + " ## " + this.genero + " ## " + this.homeworld + " ## ");
    }
}

class Arquivo {
    public String texto;

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

    public void criarLog(long tempoExecucao){  //Funcao para criar o log em arquivo
        
        try{
            
            FileWriter escreverArq = new FileWriter("matrícula_sequencial.txt");
            
            escreverArq.write("650888\t" + tempoExecucao + "\t");
            escreverArq.close();

        }catch(Exception error){

            MyIO.println("Erro ou criar o arquivo de log!");

        }
    }

    public long cronometro(){  //Iniciar um cronometro

        Clock clock = Clock.systemDefaultZone();
        return clock.millis();

    }

    public long calcularTempo(long clockA, long clockB){  //Calcula o tempo entre dois clocks em milisegundos

        return (clockB - clockA);

    }

}