import java.util.*;

class No {
    char elemento;
    No dir, esq;

    No(char elemento) {
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
    }
}

class Arvorebinaria {
    No raiz;

    Arvorebinaria() {
        this.raiz = null;
    }

    public void inserir(char elemento) {
        raiz = inserir(elemento, raiz);
    }

    public No inserir(char elemento, No i) {
        if (i == null){
            i = new No(elemento);

        }
        else if (elemento > i.elemento)
            i.dir = inserir(elemento, i.dir);
        else if (elemento < i.elemento)
            i.esq = inserir(elemento, i.esq);
        else {
            System.out.println("ELEMENTO REPETIDO");
        }
        return i;
    }

    public void caminhaCentral(No i) {
        if (i != null) {
            caminhaCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminhaCentral(i.dir);
        }
    }

    public void caminhaPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " ");
            
            caminhaPre(i.esq);
            caminhaPre(i.dir);
        }

    }

    public void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}


    public boolean pesquisa(char x) {
        boolean resp;
        resp = pesquisa(x, raiz);
        return resp;
    }

    public boolean pesquisa(char x, No i) {
        boolean resp;

        if (i == null)
            resp = false;
        else if (x == i.elemento)
            resp = true;
        else if (x > i.elemento)
            resp = pesquisa(x, i.dir);
        else {
            resp = pesquisa(x, i.esq);
        }
        return resp;
    }
}

class tp05q09 {

    public static void pesquisaOuInsere(String frase, Arvorebinaria arvorebinaria) {
        if (frase.charAt(0) == 'I')
            arvorebinaria.inserir(frase.charAt(2));
        else {
            if (arvorebinaria.pesquisa(frase.charAt(2)))
                System.out.println(frase.charAt(2) + " existe");
            else
                System.out.println(frase.charAt(2) + " nao existe");
        }

    }

    public static void caminha(String frase, Arvorebinaria arvorebinaria) {
        if (frase.charAt(0) == 'I')
            arvorebinaria.caminhaCentral(arvorebinaria.raiz);
        else if (frase.charAt(0) == 'P' && frase.charAt(1) == 'R')
            arvorebinaria.caminhaPre(arvorebinaria.raiz);
        else if (frase.charAt(0) == 'P' && frase.charAt(1) == 'O'){
            arvorebinaria.caminharPos(arvorebinaria.raiz);
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvorebinaria arvorebinaria = new Arvorebinaria();

        while (sc.hasNext()) {
            String frase = sc.nextLine();
            if ((frase.charAt(0) == 'I'  || frase.charAt(0) == 'P') &&  frase.charAt(1) == ' '){ 
                pesquisaOuInsere(frase, arvorebinaria);}
            else{
                caminha(frase,arvorebinaria);
            }

        }

    }

}