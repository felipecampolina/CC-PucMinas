import java.util.*;

class No{
    int elemento;
    No dir , esq;

    No(int elemento){
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
    }
}
class Arvorebinaria{
    No raiz;

    Arvorebinaria(){
        this.raiz = null;
    }

    public void inserir(int elemento){
        raiz = inserir(elemento,raiz);
    }
    public No inserir(int elemento, No i ){
        if(i==null) i = new No(elemento);
        else if (elemento > i.elemento)i.dir = inserir(elemento, i.dir);
        else if (elemento < i.elemento)i.esq = inserir(elemento, i.esq);
        else{
            System.out.println("ELEMENTO REPETIDO");
        }
        return i;
    }
    public void caminhaCentral(No i){
        if(i!=null){
        caminhaCentral(i.esq);
        System.out.print(i.elemento + " ");
        caminhaCentral(i.dir);
        }
    }
    public void caminhaPre(No i){
        if(i!=null){
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
}



class tp05q08{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qntRep = sc.nextInt();

        for (int i = 1; i <= qntRep; i++) {
            Arvorebinaria arvorebinaria = new Arvorebinaria();
            int qntNum = sc.nextInt();
            for (int j = 0; j < qntNum; j++) {
                arvorebinaria.inserir(sc.nextInt());
                
            }

            System.out.println("Case "+i+":");

            System.out.print("Pre.: ");
            arvorebinaria.caminhaPre(arvorebinaria.raiz);
            System.out.println();
            System.out.print("In..: ");
            arvorebinaria.caminhaCentral(arvorebinaria.raiz);
            System.out.println();
            System.out.print("Post: ");
            arvorebinaria.caminharPos(arvorebinaria.raiz);
            System.out.println();
            System.out.println();
            
        }


     
    }


    
}