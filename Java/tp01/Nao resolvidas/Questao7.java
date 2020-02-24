import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Questao7{
    public static void main(String[] args){

        //System.setProperty("file.encoding", "UTF-8");

        String nomeSerie = MyIO.readLine();
        String urlSite = MyIO.readLine();
        String html = url2Texto(urlSite);

        Print(html, nomeSerie);

        while(!ehFim(nomeSerie)){


            nomeSerie = MyIO.readLine();
            if(!ehFim(nomeSerie)) {
                urlSite = MyIO.readLine();
                html = url2Texto(urlSite);
                Print(html, nomeSerie);
            }

        }
        

    }

    private static void Print(String html, String nome) {

        String[] letras = {"a", "e", "i", "o", "u", "á", "é", "í", "ó", "ú", "à", "è", "ì", "ò", "ù", "ã", "õ", "â", "ê", "î", "ô", "û"};
        String[] consoantes = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        String[] palavras = {"<br>", "<table>"};


        for (int i = 0; i < letras.length; i++ ) {

            System.out.print(letras[i] + "("+countString(html, letras[i])+") ");
        }

        
        int sum = 0;
        for (int i = 0; i < consoantes.length; i++) {
            sum += countString(html, consoantes[i]);
        }
        System.out.print("consoante" + "("+sum+") ");
        
        for (int i = 0; i < palavras.length; i++) {

            System.out.print(palavras[i] + "("+countString(html, palavras[i])+") ");

        }


        System.out.println(nome);


    }


    private static int countString (String text, String key) {
        int count = 0;
        for (int j = 0; j < text.length()-key.length()+1; j++) {
        
          for (int k = 0; k < key.length(); k++) {
      
              if (text.charAt(j+k) != key.charAt(k)) {
      
                  k = key.length();
      
              } else if (k == key.length()-1) {

                  count++;
      
              }
        
          }
      
        }

        return count;
      }


    public static String url2Texto(String entrada){  //Funcao que retorna uma String contendo o html da pagina
        String saida = "";

        try {
            URL url = new URL(entrada);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s;
            while ((s = br.readLine()) != null) {
                saida += s;
            }
            br.close();
        } catch (Exception excecao) {
            System.out.println("Erro de link!");
        }

        return saida;
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 
}