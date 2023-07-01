class HashDiretoReserva{
    int[] tabela;
    int m1,m2,m,reserva;


    public HashDiretoReserva(){
        this(21,7);
    }
    public HashDiretoReserva(int m1, int m2){
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1+m2;
        this.tabela = new int[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = -1;
            
        }
        reserva = 0;
        

    }
    public int h(int elemento){
        return elemento % m1;
    }
    public boolean inserir(int elemento){
        boolean resp = false;
        int pos =h(elemento);
        if(tabela[pos]==-1){
            tabela[pos]=elemento;
            resp = true;
        }
        else{
            if(reserva < m2){
                tabela[m1+reserva]=elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;

    }
    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = h(elemento);
        if(tabela[pos]==elemento){
            resp = true;
        }
        else if(tabela[pos]!=-1){
            for (int i = 0; i < reserva; i++) {
                if(tabela[m1+i]==elemento){
                    resp=true;
                    i=reserva; // parar for
                }
                
            }
        }
        return resp;
    }
    public boolean remover(int elemento){
        boolean resp = false;
        int pos = h(elemento);

        if(tabela[pos]==elemento){
            tabela[pos] = -1;
            resp = true;
        }
        else if(tabela[pos]!=-1){
            for (int i = 0; i < reserva; i++) {
                if(tabela[m1+i]==elemento){
                    tabela[m1+1]=-1;
                    resp=true;
                    i=reserva;
                }
                
            }
        }
        return resp;
    }
    public void mostrar(){
        for (int i = 0; i < tabela.length; i++) {
            System.out.print(tabela[i] + " ");
            
        }
    }
}
class HashDiretoRehash{
    int[] tabela;
    int m1;

    HashDiretoRehash(){
        this(10);
    }
    HashDiretoRehash(int tamanho){
        this.m1 = tamanho;
        this.tabela = new int[this.m1];
        for (int i = 0; i < m1; i++) {
            tabela[i]=-1;
            
        }
    }
    public int hash(int elemento){
        return elemento % m1;
    }
    public int rehash(int elemento){
        return (hash(elemento) +1)%m1;
    }
    public boolean inserir(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(tabela[pos]==-1){
            tabela[pos]=elemento;
            resp = true;
        }
        else{
            pos = rehash(elemento);
            if(tabela[pos]==-1){
                tabela[pos]=elemento;
                resp = true;
            }
        }
        return resp;
    }
    public boolean pesquisar(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(tabela[pos]==elemento){
            resp = true;
        }
        else{
            pos = rehash(elemento);
            if(tabela[pos]==elemento){
                resp = true;
            }
        }
        return resp;
    }
    public boolean remover(int elemento){
        boolean resp = false;
        int pos = hash(elemento);
        if(tabela[pos]==elemento){
            tabela[pos] = -1;
            resp = true;
        }
        else{
            pos = rehash(elemento);
            if(tabela[pos]==elemento){
                tabela[pos] = -1;
                resp = true;
            }

        }
        return resp;
    }
    public void mostrar(){
        for (int i = 0; i < tabela.length; i++) {
            System.out.print(tabela[i] + " ");
        }
        System.out.println();
    }
}
class Celula{
    public int elemento;
    public Celula prox;

    Celula(){
        this(-1);
    }
    Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
    Celula(int elemento, Celula prox) {
		this.elemento = elemento;
		this.prox = prox;
	}
}
class Lista{
    public Celula primeiro;
    public Celula ultimo;

    Lista(){
        primeiro = new Celula(0);
        ultimo = primeiro;
    }

    public void inserirFim(int elemento){
        Celula tmp = new Celula(elemento);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        tmp = null;
    }
    public int remover(int elemento){
        Celula i;
        int resp = -1;

        for(i = primeiro.prox; i!=null;i = i.prox){
            if(i.prox.elemento==elemento){
                Celula tmp = i.prox;
                resp = tmp.elemento;
                i.prox = tmp.prox;
                tmp.prox = null;
                i = tmp = null;
                i = ultimo;
                return resp;
            }

        }
        return resp;

    }
    public boolean pesquisar(int x) {
		boolean retorno = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento == x){
            retorno = true;
            i = ultimo;
         }
		}
		return retorno;
	}public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}
}
class hashIndiretoLista{
    Lista tabela[];
    int tamanho ;

    hashIndiretoLista(){
        this(7);
    }
    hashIndiretoLista(int tamanho){
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }
    public int hash(int elemento){
        return elemento % tamanho;
    }
    public boolean pesquisar(int elemento) {
        int pos = hash(elemento);
        return tabela[pos].pesquisar(elemento);
     }
     public void inserir(int elemento){
        int pos = hash(elemento);
        tabela[pos].inserirFim(elemento);
     }
     public int remover2(int elemento) {
        int resp =  -1;
        if (pesquisar(elemento) == false) {
           System.out.println("ERRo");
        } else {
           int pos = hash(elemento);
           resp = tabela[pos].remover(elemento);
        }
        return resp;
     }

     public void mostrar(){
        for (int i = 0; i < tabela.length; i++) {
            System.out.print(i);
            tabela[i].mostrar();
        }
     }


}


class unidade9 {
    public static void main(String[] args) {
        HashDiretoReserva hashDiretoReserva = new HashDiretoReserva(10,5);
        hashDiretoReserva.inserir(1);
        hashDiretoReserva.inserir(10);
        hashDiretoReserva.inserir(21);
        hashDiretoReserva.inserir(31);
        hashDiretoReserva.inserir(41);
        hashDiretoReserva.inserir(2);
        hashDiretoReserva.inserir(3);
        if(hashDiretoReserva.pesquisar(31))System.out.println("Achei o 31");
        hashDiretoReserva.mostrar();
        hashDiretoReserva.remover(31);
        if(hashDiretoReserva.pesquisar(31))System.out.println("Achei o 31");
        System.out.println();
        hashDiretoReserva.mostrar();



        HashDiretoRehash hashDiretoRehash = new HashDiretoRehash(10);
        hashDiretoRehash.inserir(0);
        hashDiretoRehash.inserir(10);
        hashDiretoRehash.inserir(20);
        hashDiretoRehash.inserir(30);
        hashDiretoRehash.inserir(5);
        hashDiretoRehash.inserir(2);
        hashDiretoRehash.mostrar();
        if(hashDiretoRehash.pesquisar(10))System.out.println("Achei o 10");
        hashDiretoRehash.remover(10);
        if(hashDiretoRehash.pesquisar(10))System.out.println("Achei o 10");
        System.out.println();
        hashDiretoRehash.mostrar();




        hashIndiretoLista hashIndiretoLista = new hashIndiretoLista(10);
        hashIndiretoLista.inserir(10);
        hashIndiretoLista.inserir(20);
        hashIndiretoLista.inserir(30);
        hashIndiretoLista.inserir(11);
        hashIndiretoLista.inserir(21);
        hashIndiretoLista.inserir(31);
        hashIndiretoLista.inserir(41);
        hashIndiretoLista.inserir(35);
        hashIndiretoLista.inserir(45);
        hashIndiretoLista.remover2(21);
        hashIndiretoLista.mostrar();
        if(hashIndiretoLista.pesquisar(30))System.out.println("Achei o 30");


    }
}
