
import java.util.Arrays;

class Lab07 {
    public static void main(String[] args) {
        int tamEntrada = MyIO.readInt();
        int n;
        String tmp;
        String[] recortado;
        Arvore arvore = new Arvore();

        for(int i = 0; i < tamEntrada; i++) {
            n = MyIO.readInt();

            tmp = MyIO.readLine();
            recortado = tmp.split(" ");

            if(recortado[0].length() <= 2) MyIO.println(recortado[1]);
            else {
                arvore.escreverPosFixo(recortado[1].toCharArray(), recortado[0].toCharArray(), n);
                MyIO.println("");
    
            }

        }
    }
}

class Arvore {
    // Procurar um elemento em um array
    public int procurar(char[] array, char x) {
        int resp = -1;

        for(int i = 0; i < array.length; i++)
            if(array[i] == x) {
                resp = i;
                i = array.length;
            }

        return resp;
    }

    // Escrever a arvore em formato polones inverso
    public void escreverPosFixo(char[] infixo, char[] prefixo, int n) {
        int posRaiz = procurar(infixo, prefixo[0]);

        if(posRaiz != 0) escreverPosFixo(infixo, Arrays.copyOfRange(prefixo, 1, n), posRaiz);
        if(posRaiz != n - 1) 
            escreverPosFixo(Arrays.copyOfRange(infixo, posRaiz+1, n) , Arrays.copyOfRange(prefixo, 1 + posRaiz, n), n - posRaiz - 1);

        MyIO.print(prefixo[0]);
    }
}