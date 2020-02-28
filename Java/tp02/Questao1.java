import java.io.BufferedReader;
import java.io.FileReader;

public class Questao1 {
    public static void main(String[] args){
        String entrada = MyIO.readLine();
        LerArquivo lerArq = new LerArquivo(entrada);
        Personagem personagens = new Personagem(lerArq.texto);

        while(!ehFim(entrada)){

            MyIO.println(personagens.toString());

            entrada = MyIO.readLine();
            lerArq = null;
            personagens = null;
            if(!ehFim(entrada)){
                lerArq = new LerArquivo(entrada);
                personagens = new Personagem(lerArq.texto);
            }
        }
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M')
            fim = false;
        return fim;
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

    public Personagem(){
        this.nome = "";
        this.altura = -1;
        this.peso = -1;
        this.corDoCabelo = "";
        this.corDaPele = "";
        this.corDosOlhos = "";
        this.anoNascimento = "";
        this.genero = "";
        this.homeworld = "";
    }

    public Personagem(String entrada){
        this.nome = entrada;
    }

    private void setNome(String entrada){
        this.nome = entrada;
    }

    private void setAltura(int entrada){
        this.altura = entrada;
    }

    private void setPeso(int entrada){
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

    public String toString(){
        return ("## " + this.nome + " ##");
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