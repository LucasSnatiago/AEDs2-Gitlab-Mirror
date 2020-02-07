import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import jdk.nashorn.internal.runtime.Debug;


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


//class Serie{
    /*int A, E, I, O, U;
    int acentoA, acentoE, acentoI, acentoO, acentoU;
    int acentoAatras, acentoEatras, acentoIatras, acentoOatras, acentoUatras;
    int tilA, tilO;
    int chapeuA, chapeuE, chapeuI, chapeuO, chapeuU;
    int consoante;
    int br, table;*/

    /*public Serie(String html, String nome){
        

    }*/

    /*public String toString(){
        String resp = ("a("+A+") " + "e("+E+") " + "i("+I+") " + "o("+O+") " + "u("+U+") ");
        resp += ("á("+acentoA+") " + "é("+acentoE+") " + "í("+acentoI+") " + "ó("+acentoO+") " + "ú("+acentoU+") ");
        resp += ("à("+acentoAatras+") " + "è("+acentoEatras+") " + "ì("+acentoIatras+") " + "ò("+acentoOatras+") " + "ù("+acentoUatras+") ");
        resp += ("ã("+tilA+") " + "õ("+tilO+") ");
        resp += ("â("+chapeuA+") " + "ê("+chapeuE+") " + "î("+chapeuI+") " + "ô("+chapeuO+") " + "û("+chapeuU+") ");
        resp += ("consoante("+consoante+") " + "<br>("+br+") " + "<table>("+table+") ");

        return resp;
    }

    private void vogais(String html){
        for(int i = 0; i < html.length(); i++){
            if(html.charAt(i) == 'a'){
                A++;
            }
            if(html.charAt(i) == 'e'){
                E++;
            }
            if(html.charAt(i) == 'i'){
                I++;
            }
            if(html.charAt(i) == 'o'){
                O++;
            }
            if(html.charAt(i) == 'u'){
                U++;
            }
        }
    }

    private void acentos(String html){
        String letra = "";
        for(int i = 0; i < html.length(); i++){
            letra = "";
            letra += html.charAt(i);
            if(letra == "á"){
                acentoA++;
            }
            letra = "";
            letra += html.charAt(i);
            if(letra == "é"){
                acentoE++;
            }
            letra = "";
            letra += html.charAt(i);
            if(letra == "í"){
                acentoI++;
            }
            letra = "";
            letra += html.charAt(i);
            if(letra == "ó"){
                acentoO++;
            }
            letra = "";
            letra += html.charAt(i);
            if(letra == "ú"){
                acentoU++;
            }
        }
    }

    private void acentosTras(String html){
        for(int i = 0; i < html.length(); i++){
            if(html.charAt(i)+"" == "à"){
                acentoAatras++;
            }
            if(html.charAt(i)+"" == "è"){
                acentoEatras++;
            }
            if(html.charAt(i)+"" == "ì"){
                acentoIatras++;
            }
            if(html.charAt(i)+"" == "ò"){
                acentoOatras++;
            }
            if(html.charAt(i)+"" == "ù"){
                acentoUatras++;
            }
        }
    }

    private void til(String html){
        for(int i = 0; i < html.length(); i++){
            if(html.charAt(i)+"" == "ã"){
                tilA++;
            }
            if(html.charAt(i)+"" == "õ"){
                tilO++;
            }
        }
    }

    private void chapeu(String html){
        for(int i = 0; i < html.length(); i++){
            if(html.charAt(i)+"" == "â"){
                chapeuA++;
            }
            if(html.charAt(i)+"" == "ê"){
                chapeuE++;
            }
            if(html.charAt(i)+"" == "î"){
                chapeuI++;
            }
            if(html.charAt(i)+"" == "ô"){
                chapeuO++;
            }
            if(html.charAt(i)+"" == "û"){
                chapeuU++;
            }
        }
    }

    private void consoante(String html){
        char letra;
        for(int i = 0; i < html.length(); i++){
            letra = html.charAt(i);
            if(letra >= 'a' && letra <= 'z' && (letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u')){
                consoante++;
            }
        }
    }

    private void chaves(String html){
        for(int i = 0; i < html.length(); i++){
            if(html.charAt(i) == '<'){
            
                if(html.charAt(i+1) == 'b' && html.charAt(i+2) == 'r' && html.charAt(i+3) == '>'){
                    br++;
                }else if(html.charAt(i+1) == 't' && html.charAt(i+2) == 'a' && html.charAt(i+3) == 'b' && html.charAt(i+4) == 'l' && html.charAt(i+5) == 'e' && html.charAt(i+6) == '>'){
                    table++;
                }

            }
        }
    }*/
//}