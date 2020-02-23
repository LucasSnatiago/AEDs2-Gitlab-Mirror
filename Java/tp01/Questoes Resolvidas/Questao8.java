import java.io.RandomAccessFile;
import java.io.IOException;

public class Questao8{ 
	public static void main(String[] args){
        int numTestes = MyIO.readInt();
        
		escreverArquivo(numTestes); // Escrever no Arquivo
		lerArquivo(numTestes); // Lendo o arquivo
	} 
	
	public static void escreverArquivo(int numTestes){ 
		double valor;
		try{
			RandomAccessFile arq = new RandomAccessFile("Arquivo.txt","rw");
			for (int i = 1; i <= numTestes; i++){
				valor = MyIO.readDouble();
				arq.writeDouble(valor);
			}
			arq.close();
		} catch (IOException e){
            MyIO.println("Erro ao criar o arquivo ou escrever no mesmo!");
		}
	} 

	public static void lerArquivo(int numTestes){ 
		double valor;
		try{
			RandomAccessFile arq = new RandomAccessFile("Arquivo.txt","r");
			for (int i = 1 ; i <= numTestes ; i++){
				arq.seek(arq.length() - (i * 8));  //Voltando um double de cada vez
				valor = arq.readDouble();

				if(valor == (int)valor)	MyIO.println((int)valor);
				else MyIO.println(valor);
			}
			arq.close();
		} catch (IOException e){
            MyIO.println("Erro na leitura do arquivo!"); 
        }
	} 

}