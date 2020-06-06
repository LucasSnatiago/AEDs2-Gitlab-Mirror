import java.io.BufferedReader;
import java.io.FileReader;

public class Questao3 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        LerArquivo lerArq = new LerArquivo(entrada);
        Personagem personagens = new Personagem(lerArq.texto);
        Lista lista = new Lista(100);

        while(!ehFim(entrada)){

            lista.inserirFinal(personagens);

            entrada = MyIO.readLine();
            lerArq = null;
            personagens = null;
            if(!ehFim(entrada)){
                lerArq = new LerArquivo(entrada);
                personagens = new Personagem(lerArq.texto);
            }
        }

        //Esta funcionando ate aqui

        int numExecucoes = MyIO.readInt();
        String execucoesLista = MyIO.readLine();

        for(int i = 0 ; i < numExecucoes; i++){

            String[] textoCortado = splitString(execucoesLista, ' ');
            ordenarExecucoes(textoCortado, lista);

            if(i+1 != numExecucoes) execucoesLista = MyIO.readLine();
        }

        MyIO.println(lista.toString());

    }

    public static void printarRemovido(Personagem personagem){  //Escrever na tela personagem removido

        MyIO.print("(R) ");
        MyIO.println(personagem.getNome());

    }

    public static void ordenarExecucoes(String[] entrada, Lista lista){  //Funcao que ordena qual sera a proxima execucao para ser feita na lista
        Personagem personagem;
        int pos;

        if(entrada[0].trim().equals("II")){
            personagem = new Personagem(new LerArquivo(entrada[1].trim()).texto);
            lista.inserirInicio(personagem);
        }
        if(entrada[0].trim().equals("I*")){
            pos = Integer.parseInt(entrada[1].trim());
            personagem = new Personagem(new LerArquivo(entrada[2].trim()).texto);
            lista.inserirLista(personagem, pos);
        }
        if(entrada[0].trim().equals("IF")){
            personagem = new Personagem(new LerArquivo(entrada[1].trim()).texto);
            lista.inserirFinal(personagem);
        }
        if(entrada[0].trim().equals("RI")){
            personagem = lista.removerInicio();
            printarRemovido(personagem);
        }
        if(entrada[0].trim().equals("R*")){
            pos = Integer.parseInt(entrada[1].trim());
            personagem = lista.removerLista(pos);
            printarRemovido(personagem);
        }
        if(entrada[0].trim().equals("RF")){
            personagem = lista.removerFinal();
            printarRemovido(personagem);
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
    
}

class Lista extends Ordenador{


    public Lista(int tam){  //Instanciar um objeto do tipo lista
        super(tam);
    }

    public Lista(){  //Instanciar objeto com numero padrao de espacos
        super();
    }

    public void inserirInicio(Personagem personagem){  //Inserir um personagem no inicio da Lista

       inserirLista(personagem, 0);

    }

    public void inserirLista(Personagem personagem, int pos){  //Inserir um personagem em qualquer posicao da Lista

        if(this.numElementos == this.tamMax) MyIO.println("Erro na insercao: lista cheia!");
        else if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro na insercao: posicao invalida!");
        else{
            swiftDir(pos);
            this.lista[pos] = personagem;
        }

    }

    public void inserirFinal(Personagem personagem){  //Inserir um personagem no final da Lista

        if(this.numElementos == this.tamMax) MyIO.println("Erro na insercao: lista cheia!");
        else{
            this.lista[this.numElementos] = personagem;
            this.numElementos++;
        }

    }

    public Personagem removerInicio(){   //Remover um personagem no inicio da Lista
        Personagem saida = null;

        saida = removerLista(0);

        return saida;
    }

    public Personagem removerLista(int pos){  //Remover um personagem em qualquer posicao da Lista
        Personagem saida = null;

        if(this.numElementos == 0) MyIO.println("Erro na remocao: Nao ha elementos na lista");
        else if(pos < 0 || pos+1 > this.numElementos) MyIO.println("Erro na remocao: Posicao invalida!");
        else{
            saida = this.lista[pos];
            swiftEsq(pos);
        }

        return saida;
    }

    public Personagem removerFinal(){  //Remover um personagem no final da Lista
        Personagem saida = null;

        saida = removerLista(this.numElementos-1);

        return saida;
    }
    

    public String toString(){
        String saida = "";

        for(int i = 0; i < this.numElementos; i++){
            saida += ("[" + i + "] " + this.lista[i].toString());
            if(i+1 != this.numElementos) saida += '\n';
        }
        
        return saida;
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

    /*  Elementos da classe:
        this.nome;
        this.altura;
        this.peso;
        this.corDoCabelo;
        this.corDaPele;
        this.corDosOlhos;
        this.anoNascimento;
        this.genero;
        this.homeworld;
    */

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

class LerArquivo {
    public String texto;

    public LerArquivo(String entrada){
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
    }
}