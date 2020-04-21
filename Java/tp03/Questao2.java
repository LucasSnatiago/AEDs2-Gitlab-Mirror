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
        Pilha ps = new Pilha();

        while(!ehFim(entrada)){

            ps.empilhar(personagens);

            entrada = MyIO.readLine();
            arq = null;
            personagens = null;
            if(!ehFim(entrada)){
                arq = new Arquivo();
                arq.lerArquivo(entrada);
                personagens = new Personagem(arq.texto);
            }
        }        

        int repeticoes = Integer.parseInt(MyIO.readLine());
        for(int i = 0; i < repeticoes; i++) {
            ps.orquestrar(splitString(MyIO.readLine(), " "));
        }
    
        ps.mostrar();
    }

    public static String[] splitString(String entrada, String corte){  //Separar uma String em varias
        return entrada.split(corte);
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M')
            fim = false;
        return fim;
    } 
}

//Classe Lista dinamica
class Lista extends Celula {
    public Celula inicio;
    public int numElementos;

    public Lista() {
        this.inicio = new Celula();
        this.numElementos = 0;
    }

    //Orquestrar execucoes
    public void ordenador(String[] texto, Lista lista) {

        if (texto[0].equals("II")){

            try{ lista.inserir(new Personagem(new Arquivo(texto[1]).texto), 0);
            } catch (Exception err) {}

        } else if (texto[0].equals("IF")) {

            try{ lista.inserir(new Personagem(new Arquivo(texto[1]).texto), lista.numElementos);
            } catch (Exception err) {}

        } else if (texto[0].equals("I*")) {

            try{ lista.inserir(new Personagem(new Arquivo(texto[2]).texto), Integer.parseInt(texto[1]));
            } catch (Exception err) {}

        } else if (texto[0].equals("RI")) {

            lista.escreverRemocao(0);

        } else if (texto[0].equals("RF")) {

            lista.escreverRemocao(lista.numElementos);

        } else if (texto[0].equals("R*")) {

            lista.escreverRemocao(Integer.parseInt(texto[1]));

        }
    }

    //Inserir elemento na ultima posicao
    public void inserir(Personagem personagem) {
        try {
            inserir(personagem, this.numElementos);
        }catch (Exception err) {
            MyIO.println("Não foi possível inserir no final");
        }
    }

    //Inserir um elemento na lista
    public void inserir(Personagem personagem, int pos) throws Exception {
        if (pos < 0 || pos > this.numElementos)
            throw new Exception("Erro: inserção em posição inválida!");

        if(pos == 0) _inserirInicio(personagem);
        else if (pos == this.numElementos) _inserirFinal(personagem);
        else _inserirPos(personagem, pos);
        
        this.numElementos++;
    }

    //Remover um elemento da lista
    public Celula remover(int pos) throws Exception {
        if (this.numElementos == 0)
           throw new Exception ("Erro: remoção de elemento em lista vazia!");
        if (pos < 0 || pos > this.numElementos)
            throw new Exception ("Erro: posição inválida!");
        
        Celula removida = null;
        
        if (pos == 0) removida = _removerInicio();
        else if (pos == this.numElementos) removida = _removerFinal();
        else removida = _removerPos(pos);
        this.numElementos--;

        return removida;
    }

    //Mostrar elementos da lista
    public void mostrar() {
        Celula i = this.inicio.prox;
        int contador = 0;

        while(i != null) {
            MyIO.println("[" + contador + "] " + i.personagem.toString());
            contador++;
            i = i.prox;
        }

    }

    //Remover um elemento da lista e escrever na saida padrao
    public void escreverRemocao(int pos) {
        try{
            MyIO.println("(R) " + remover(pos).personagem.getNome());
        }catch (Exception err){
            MyIO.println("Não foi possível realizar a remoção e escreve-la!");
        }
    }

    //Encontrar ultima celula
    private Celula _encontrarUltimaCelula() {
        Celula tmp = this.inicio;
        while(tmp.prox != null) tmp = tmp.prox; //Deslocar ate a ultima celula
        return tmp;
    }    

    //Inserir inicio
    private void _inserirInicio(Personagem personagem) {
        Celula tmp = new Celula();

        tmp.prox = this.inicio;
        this.inicio.personagem = personagem;
        this.inicio = tmp;
        tmp = null;
    }

    //Inserir pos
    private void _inserirPos(Personagem personagem, int pos) {
        Celula tmp = this.inicio;
        Celula inserir = new Celula(personagem);

        for (int i = 0; i < pos; i++) tmp = tmp.prox;
        inserir.prox = tmp.prox;
        tmp.prox = inserir;

        inserir = null;
        tmp = null;
    }

    //Inserir um elemento na lista
    private void _inserirFinal(Personagem personagem) {
        Celula tmp = _encontrarUltimaCelula();

        tmp.prox = new Celula(personagem);
    }

    //Remover um Celula do inicio
    private Celula _removerInicio() {
        Celula removido = null;
        Celula tmp = this.inicio;

        this.inicio = this.inicio.prox;
        removido = this.inicio;
        tmp.prox = null;
        tmp = null;

        return removido;
    }

    //Remover uma celula de alguma posicao
    private Celula _removerPos(int pos) {
        Celula tmp = this.inicio;
        Celula removida = null;

        for (int i = 0; i < pos; i++) tmp = tmp.prox;  //Ir ate uma celula antes da celula para remover
        removida = tmp.prox;
        tmp.prox = tmp.prox.prox;
        tmp = null;   

        return removida;
    }

    //Remover Celula na posicao 'pos'
    private Celula _removerFinal() {
        Celula removida = null;
        Celula tmp = this.inicio;
        
        while (tmp.prox.prox != null) tmp = tmp.prox; //Andar ate a penultima celula
        removida = tmp.prox;
        tmp.prox = null;

        return removida;
    }
}


//Classe pilha dinamica
class Pilha extends Celula {
    public Celula topo;
    public int numElementos;

    public Pilha() {
        this.topo = null;
        this.numElementos = 0;
    }

    public void orquestrar(String[] execucoes) {

        if(execucoes[0].equals("I")) {

            empilhar(new Personagem(new Arquivo(execucoes[1]).texto));

        } else if (execucoes[0].equals("R")) {

            try{
                String nome = desempilhar().personagem.getNome();
                MyIO.println("(R) " + nome);
            } catch (Exception err) {
                MyIO.println("Não foi possivel desempilhar!");
            }

        }

    }

    //Mostrar elementos da pilha em ordem
    public void mostrar() {
        Celula i = this.topo;
        int num = this.numElementos-1;
        _mostrar(i, num);
    }

    private void _mostrar(Celula i, int posAtual) {
        if(i.prox != null)
            _mostrar(i.prox, posAtual-1);

        MyIO.println("[" + posAtual + "] " + i.personagem.toString());        
    }

    //Inserir um elemento na pilha
    public void empilhar(Personagem personagem) {
        Celula tmp = new Celula(personagem);
        tmp.prox = this.topo;
        this.topo = tmp;
        tmp = null;
        this.numElementos++;
    }

    //Remover um elemento da pilha
    public Celula desempilhar() throws Exception {
        if(this.topo == null)
            throw new Exception ("Impossivel remover um elemento de uma pilha vazia!");

        Celula removida = this.topo;
        this.topo = this.topo.prox;
        removida.prox = null;
        this.numElementos--;
        return removida;
    }
}

/*
//Classe fila dinamica
class Fila extends Celula {
    public Celula inicio, fim;
    public int numElementos;

    public Fila() {
        this.inicio = this.fim = null;
        this.numElementos = 0;
    }

    public void orquestrar(String[] execucoes) {

        if(execucoes[0].equals("I")) {

            empilhar(new Personagem(new Arquivo(execucoes[1]).texto));

        } else if (execucoes[0].equals("R")) {

            try{
                String nome = desempilhar().personagem.getNome();
                MyIO.println("(R) " + nome);
            } catch (Exception err) {
                MyIO.println("Não foi possivel desempilhar!");
            }

        }

    }

    //Inserir elementos na fila
    public void inserir(Personagem personagem) {

    }

    //Remover elementos da fila
    public Celula remover() {

    }

    //Mostrar elementos da pilha em ordem
    public void mostrar() {
        Celula i = this.inicio;
        int num = 0;
        while(i != null) {
            MyIO.println("[" + num + "] " + i.personagem.toString());
            i = i.prox;
        }
    }
*/

}
//Celula de personagens
class Celula {
    protected Celula prox;
    protected Personagem personagem;

    public Celula() {
        this(null);
    }

    public Celula(Personagem personagem) {
        this.personagem = personagem;
        this.prox = null;
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