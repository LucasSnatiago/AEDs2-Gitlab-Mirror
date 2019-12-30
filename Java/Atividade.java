import java.util.Scanner;
import java.io.File;

class Atividade{
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        File arq = new File("entrada.txt");
        String entrada = leitor.nextLine();
        System.out.println(entrada);
        
        Run a = new Run();

        long tamArquivo = a.tamArquivo(arq);
        System.out.println(tamArquivo);
        

        //Desligar o programa
        leitor.close();
    }
}


class Run{
    public long tamArquivo(File arq){
        return arq.length();
    }
}