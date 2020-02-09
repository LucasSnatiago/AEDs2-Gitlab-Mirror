import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.File;

public class Questao8{
    public static void main(String[] args){
        int numTestes = MyIO.readInt();
        FileOutputStream arq = null;

        
        float[] valores = new float[numTestes];
        for(int i = 0; i < numTestes; i++){
            valores[i] = MyIO.readFloat();
        }
        
        try{
            arq = new FileOutputStream("Arquivo.txt");
        }catch(Exception e){
            MyIO.println("Não foi possivel abrir o arquivo!");
        }
        
        PrintWriter arqEscrita = new PrintWriter(arq);
        escreverArquivo(arqEscrita , valores, numTestes);
        arqEscrita.close();
        
        
        File arquivoAleatorio;
        RandomAccessFile aleatorio = null;
        try{
            arquivoAleatorio = new File("Arquivo.txt");
            aleatorio = new RandomAccessFile(arquivoAleatorio, "r");
        }catch (Exception e){
            MyIO.println("Não foi possivel abrir o arquivo aleatoriamente");
        }
        
        try{
            aleatorio.seek(aleatorio.length());
            MyIO.println(aleatorio.getFilePointer());      
        }catch (Exception e){
            MyIO.println("Não foi possivel ler o arquivo!");
        }
        
  
    }


    public static void escreverArquivo(PrintWriter arq, float[] valores, int tamanho){  //Funcao para escrever valores float no arquivo
        String numeros = "";

        for(int i = 0; i < tamanho; i++){
            numeros += String.valueOf(valores[i]);
            numeros += '\n';
        }

        try{
            arq.write(numeros);
        }catch (Exception e){
            MyIO.println("Não foi possivel escrever no arquivo!");
        }
    }    
   
}