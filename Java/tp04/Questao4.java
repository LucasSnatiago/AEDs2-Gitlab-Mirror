import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Clock;

public class Questao4 {
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String entrada = MyIO.readLine();
        Arquivo arq = new Arquivo();
        arq.lerArquivo(entrada);
        Personagem personagens = new Personagem(arq.texto);
        //Arvore ps = new Arvore();

        RBTree rbt = new RBTree(null); 

        while(!ehFim(entrada)){

            rbt.insert(personagens);

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
            ordenador(rbt, entrada);
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
    public static void ordenador(RBTree arvore, String nomePersonagem) {
        
        System.out.print(nomePersonagem + " raiz ");
        //arvore.mostrar();
        if(arvore.search(nomePersonagem)) MyIO.println("SIM");
        else System.out.printf("N%cO\n", (char)195);

    }
}

// Classe de arvore binaria Alvinegra
// Fonte: https://www.sanfoundry.com/java-program-implement-red-black-tree/

 /* Class Node */
 class RedBlackNode
 {    
     RedBlackNode left, right;
     Personagem element;
     int color;
 
     /* Constructor */
     public RedBlackNode(Personagem theElement)
     {
         this( theElement, null, null );
     } 
     /* Constructor */
     public RedBlackNode(Personagem theElement, RedBlackNode lt, RedBlackNode rt)
     {
         left = lt;
         right = rt;
         element = theElement;
         color = 1;
     }    
 }
 
 /* Class RBTree */
 class RBTree
 {
     private RedBlackNode current;
     private RedBlackNode parent;
     private RedBlackNode grand;
     private RedBlackNode great;
     private RedBlackNode header;    
     private static RedBlackNode nullNode;
     /* static initializer for nullNode */
     static 
     {
         nullNode = new RedBlackNode(null);
         nullNode.left = nullNode;
         nullNode.right = nullNode;
     }
     /* Black - 1  RED - 0 */
     static final int BLACK = 1;    
     static final int RED   = 0;
 
     /* Constructor */
     public RBTree(Personagem negInf)
     {
         header = new RedBlackNode(negInf);
         header.left = nullNode;
         header.right = nullNode;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return header.right == nullNode;
     }
     /* Make the tree logically empty */
     public void makeEmpty()
     {
         header.right = nullNode;
     }
     /* Function to insert item */
     public void insert(Personagem item )
     {
         current = parent = grand = header;
         nullNode.element = item;
         while (current.element != item)
         {            
             great = grand; 
             grand = parent; 
             parent = current;
             //current = item < current.element ? current.left : current.right;
             if(item != null && current.element != null && item.getNome().compareTo(current.element.getNome()) < 0)
                 current = current.left;

             else current = current.right;
             
             // Check if two red children and fix if so            
             if (current.left.color == RED && current.right.color == RED)
                 handleReorient( item );
         }
         // Insertion fails if already present
         if (current != nullNode)
             return;
         current = new RedBlackNode(item, nullNode, nullNode);
         // Attach to parent
         //if (item < parent.element)
         if (item != null && parent.element != null && item.getNome().compareTo(parent.element.getNome()) < 0)
             parent.left = current;
         else
             parent.right = current;        
         handleReorient( item );
     }
     private void handleReorient(Personagem item)
     {
         // Do the color flip
         current.color = RED;
         current.left.color = BLACK;
         current.right.color = BLACK;
 
         if (parent.color == RED)   
         {
             // Have to rotate
             grand.color = RED;
             //if (item < grand.element != item < parent.element)
             if ((item.getNome().compareTo(grand.element.getNome()) < 0) != (item.getNome().compareTo(parent.element.getNome()) < 0))
                 parent = rotate( item, grand );  // Start dbl rotate
             current = rotate(item, great );
             current.color = BLACK;
         }
         // Make root black
         header.right.color = BLACK; 
     }      
     private RedBlackNode rotate(Personagem item, RedBlackNode parent)
     {
         //if(item < parent.element)
         if(item != null && parent.element != null && item.getNome().compareTo(parent.element.getNome()) < 0)
             //return parent.left = item < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left);
             if(parent.left.element.getNome().compareTo(parent.left.element.getNome()) < 0)
                 return parent.left = rotateWithLeftChild(parent.left);
             else
                 return parent.right = rotateWithRightChild(parent.left);

         else
             //return parent.right = item < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right); 
             if(parent.right.element.getNome().compareTo(parent.right.element.getNome()) < 0)
                return parent.right = rotateWithLeftChild(parent.right);
             else
                return parent.right = rotateWithRightChild(parent.right);
     }
     /* Rotate binary tree node with left child */
     private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
     {
         RedBlackNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         return k1;
     }
     /* Rotate binary tree node with right child */
     private RedBlackNode rotateWithRightChild(RedBlackNode k1)
     {
         RedBlackNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         return k2;
     }
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(header.right);
     }
     private int countNodes(RedBlackNode r)
     {
         if (r == nullNode)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
     /* Functions to search for an element */
     public boolean search(String val)
     {
         return search(header.right, val);
     }
     private boolean search(RedBlackNode r, String val)
     {
            boolean found = false;

            if(r != nullNode) {
                //if (val < rval)
                if(val.compareTo(r.element.getNome()) < 0) {
                    MyIO.print("esq ");
                    //r = r.left;
                    found = search(r.left, val);
                }
                //else if (val > rval)
                else if (val.compareTo(r.element.getNome()) > 0) {
                    MyIO.print("dir ");
                    //r = r.right;
                    found = search(r.right, val);
                }
                else found = true;
            }

        return found;
     }
     /* Function for inorder traversal */ 
     public void inorder()
     {
         inorder(header.right);
     }
     private void inorder(RedBlackNode r)
     {
         if (r != nullNode)
         {
             inorder(r.left);
             char c = 'B';
             if (r.color == 0)
                 c = 'R';
             System.out.print(r.element +""+c+" ");
             inorder(r.right);
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(header.right);
     }
     private void preorder(RedBlackNode r)
     {
         if (r != nullNode)
         {
             char c = 'B';
             if (r.color == 0)
                 c = 'R';
             System.out.print(r.element +""+c+" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(header.right);
     }
     private void postorder(RedBlackNode r)
     {
         if (r != nullNode)
         {
             postorder(r.left);             
             postorder(r.right);
             char c = 'B';
             if (r.color == 0)
                 c = 'R';
             System.out.print(r.element +""+c+" ");
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