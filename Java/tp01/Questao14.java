public class Questao14{
    public static void main(String[] args){

        String entrada = MyIO.readLine();
        AlgebraBooleana elementos;

        while(!ehFim(entrada)){
            elementos = new AlgebraBooleana(entrada);

            MyIO.println(elementos.resposta);
            elementos = null;

            entrada = MyIO.readLine();
        }


    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != '0') fim = false;
        return fim;
    }
}


class AlgebraBooleana {
    protected int numTermos;
    protected int[] termos;
    protected String expressao;
    public String resposta;

    public AlgebraBooleana(String entrada){
        String semEspacos = removerEspaco(entrada);
        completarVariaveis(semEspacos);
        trocarLetras();
        this.resposta = resolver(this.expressao);
    }

    private void completarVariaveis(String entrada){  //Retirar os numeros antes da expressao logica
        numTermos = (int) entrada.charAt(0) - '0';
        termos = new int[numTermos];
        
        for(int i = 0; i < numTermos; i++){
            termos[i] = (int) entrada.charAt(i+1) - '0';
        }

        String saida = "";
        for(int i = 0; i < entrada.length(); i++){
            if(i > numTermos){
                saida += entrada.charAt(i);
            }
        }
        expressao = saida;
    }

    private void trocarLetras(){  //Trocar as letras pelos seus valores 
        String saida = "";

        for(int i = 0; i < this.expressao.length(); i++){

            if(this.numTermos >= 1 && this.expressao.charAt(i) == 'A') saida += this.termos[0];
            else if(this.numTermos >= 2 && this.expressao.charAt(i) == 'B') saida += this.termos[1];
            else if(this.numTermos >= 3 && this.expressao.charAt(i) == 'C') saida += this.termos[2];
            else saida += this.expressao.charAt(i);
        }

        this.expressao = saida;
    }

    private String removerEspaco(String entrada){  //Remover espacos de uma String
        String saida = "";
        int tamanhoEntrada = entrada.length();

        for(int i = 0; i < tamanhoEntrada; i++){
            if(entrada.charAt(i) != ' '){
                saida += entrada.charAt(i);
            }
        }

        return saida;
    }

    private String resolver(String expressao){  //Resolver a expressao recursivamente
        int pos = 0;
        String bitArray = "";
        char porta;
        int posFinal = 0;

       

        while (expressao.length() > 1) {
            while(expressao.charAt(posFinal) != ')') posFinal++;
            pos = posFinal;
            
            
            while(expressao.charAt(pos) != '('){
                
                if(expressao.charAt(pos) == '0') bitArray += '0';
                if(expressao.charAt(pos) == '1') bitArray += '1';
                pos--;
                
            }
            
            porta = expressao.charAt(pos-1);
            
            if(porta == 'd') pos -= 3;
            if(porta == 'r') pos -= 2;
            if(porta == 't') pos -= 3;
            
            
            
            if(posFinal != expressao.length()-1) {
                expressao = inserir(expressao, resolver(subString(expressao, pos, posFinal)), pos);
                expressao = substituir(expressao, pos+1, posFinal+1);
                bitArray = "";
                posFinal = pos;
            } else {
                
                expressao = portas(porta, bitArray);
                
            }

        }

        return expressao;
    }

    private String subString(String entrada, int posInicio, int posFinal){  //Uma funcao subString
        String saida = "";

        for(int i = posInicio; i < posFinal+1; i++) saida += entrada.charAt(i);

        return saida;
    }

    private String inserir(String entrada, String texto, int pos){  //Funcao para inserir um texto dentro de outro
        String saida = "";

        for(int i = 0; i < pos; i++) saida += entrada.charAt(i);
        for(int i = 0; i < texto.length(); i++) saida += texto.charAt(i);
        for(int i = pos; i < entrada.length(); i++) saida += entrada.charAt(i);

        return saida;
    }

    private String substituir(String expressao, int posInicio, int posFinal){  //Substituir String
        String saida = "";

        for(int i = 0; i < expressao.length(); i++) 
            if(i < posInicio || i > posFinal) 
                saida += expressao.charAt(i);

        return saida;
    }

    private boolean String2Boolean(char entrada){  //Tranformando uma String em uma boolean
        boolean saida = false;
        if(entrada == '1') saida = true;
        return saida;
    }

    private String portas(char porta, String bitArray){  //Resolver as portas logicas
        boolean resp = false;

        if (porta == 'd') resp = true;

        for(int i = 0; i < bitArray.length(); i++){

            if (porta == 'd') resp &= String2Boolean(bitArray.charAt(i));
            if (porta == 'r') resp |= String2Boolean(bitArray.charAt(i));
            if (porta == 't') resp = !String2Boolean(bitArray.charAt(i));

        }
        String saida = "";
        if(resp) saida += '1';
        else saida += '0';

        return saida;
    }

    public String toString(){  //Printar termos 
        return (this.numTermos + "\t" + this.termos[0] + "\t" + this.termos[1] + "\t" + this.expressao);
    }
}