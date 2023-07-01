import java.util.*;

class Celula {
    int elemento;
    Celula prox;

    Celula() {
        this(0);
    }

    Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }

}

class CelulaDupla {
    int elemento;
    CelulaDupla prox, ant;

    CelulaDupla() {
        this(0);
    }

    CelulaDupla(int x) {
        this.elemento = x;
        this.prox = null;
        this.ant = null;
    }

}

class ListaSimples {
    Celula primeiro, ultimo;

    ListaSimples() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo)
            ultimo = tmp;
        tmp = null;

    }

    public void mostrar() {
        System.out.print("[");
        for (Celula i = primeiro; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

class ListaDupla {
    int elemento;
    CelulaDupla primeiro, ultimo;

    ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserirInicio(int x) {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else{
            tmp.prox.ant = tmp;
        }
        tmp = null;

    }

    public void mostrar() {
        System.out.print("[");
        for (CelulaDupla i = primeiro; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

class No {
    int elemento; // elemento
    No esq, dir; // no direita e esquerda

    No(int elemento) {
        this(elemento, null, null);

    }

    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    No raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int x) throws Exception { // Retorno de referencia
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i) throws Exception { // Retorno de referencia
        if (i == null)
            i = new No(x); // se i for nulo inserir o novo novo la
        else if (x < i.elemento)
            i.esq = inserir(x, i.esq); // se i não for nulo e x menor que elemento fazer recursao mandandno o i.esq
                                       // retornara o endereco do no criado
        else if (x > i.elemento)
            i.dir = inserir(x, i.dir);// se i não for nulo e x maior que elemento fazer recursao mandandno o i.dir
                                      // retornara o endereco do no criado
        else {
            throw new Exception("ERRO!"); // erro elemento repetido
        }
        return i; // retorna no
    }

    public void inserirPai(int x) throws Exception {
        if (raiz == null)
            raiz = new No(x); // caso a raiz seja nula inserir la mesmo
        else if (x < raiz.elemento)
            inserirPai(x, raiz.esq, raiz); // se elemento é menor que raiz manda raiz.esq e pai
        else if (x > raiz.elemento)
            inserirPai(x, raiz.dir, raiz);// se elemento é maior que raiz manda raiz.dir e pai
        else {
            throw new Exception("ERRO!"); // erro elemento repetido
        }
    }

    public void inserirPai(int x, No i, No pai) throws Exception {
        if (i == null) {
            if (x < pai.elemento)
                pai.esq = new No(x); // Insere a esquerda
            else
                pai.dir = new No(x); // insere a direita
        } else if (x < raiz.elemento)
            inserirPai(x, i.esq, i);// se elemento é menor que raiz manda raiz.esq e pai
        else if (x > raiz.elemento)
            inserirPai(x, i.dir, i);// se elemento é maior que raiz manda raiz.dir e pai
        else {
            throw new Exception("ERRO!"); // erro elemento repetido
        }

    }

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz); // inicio da recursao manda elemento e raiz
    }

    public boolean pesquisar(int x, No i) {
        boolean resp; // inicializacao da resp
        if (i == null)
            resp = false; // Se i == null significa que acabou a arvore e não achou o numero
        else if (x == i.elemento)
            resp = true; // se i != e x == i.elemento significa que achou o numero e retorna true
        else if (x < i.elemento)
            resp = pesquisar(x, i.esq);// se i!= de null porem não é o numero e eh menor que o no manda x e i.esq
        else
            resp = pesquisar(x, i.dir); // se i!= de null porem não é o numero e eh maior que o no manda x e i.dir
        return resp; // retorna resp
    }

    public void caminharCentral(No i) { // Ordenado
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharPre(No i) { // Pre – raiz --> esquerda --> direita PRIMEIRO E A RAIZ
        if (i != null) {
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos(No i) { // esquerda --> direita --> raiz – ULTIMO E A RAIZ
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(No i, int altura) {
        if (i == null) {
            altura--; // diminue a altura , pois null não conta
        } else {
            int alturaEsquerda = getAltura(i.esq, altura + 1); // calcula altura esquerda
            int alturaDireita = getAltura(i.dir, altura + 1); // calcula altura direita
            altura = (alturaEsquerda > alturaDireita) ? alturaEsquerda : alturaDireita; // pega a maior
        }
        return altura;
    }

    public int getSoma() {
        return getSoma(raiz);
    }

    public int getSoma(No i) {
        int resp = 0;
        if (i != null) {
            resp = i.elemento + getSoma(i.esq) + getSoma(i.dir);

        }
        return resp;
    }

    public int getPares() {
        return getPares(raiz);
    }

    public int getPares(No i) {
        int resp = 0;
        if (i != null) {
            if (i.elemento % 2 == 0)
                resp++;
            resp = resp + getPares(i.esq) + getPares(i.dir);

        }
        return resp;
    }

    public boolean igual(ArvoreBinaria arvore01, ArvoreBinaria arvore02) {
        return igual(arvore01.raiz, arvore02.raiz);
    }

    public boolean igual(No i, No j) {
        boolean resp;
        if (i != null && j != null) {
            resp = (i.elemento == j.elemento) && igual(i.dir, j.dir) && igual(i.esq, j.esq);
        } else if (i == null && j == null) {
            resp = true;
        } else {
            resp = false;
        }
        return resp;
    }

    public boolean div11(ArvoreBinaria arvore01) {
        return div11(arvore01.raiz);
    }

    public boolean div11(No i) {
        boolean resp;
        if (i != null) {
            resp = (i.elemento % 11 == 0) || div11(i.esq) || div11(i.dir);
        } else
            resp = false;
        return resp;
    }
    public No toAB(Celula p1,CelulaDupla p2) throws Exception{
        No resp = null;
        while(p1 != null && p2!= null){
            resp = inserir(p1.elemento,resp);
            resp = inserir(p2.elemento,resp);
            p1 = p1.prox;
            p2 = p2.prox;
        }
        while(p1 != null){
            resp = inserir(p1.elemento,resp);
            p1 = p1.prox;

        }
        while(p2 != null){
            resp = inserir(p2.elemento,resp);
            p2 = p2.prox;
        }
        return resp;
    }

    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore01 = new ArvoreBinaria();
        ArvoreBinaria arvore02 = new ArvoreBinaria();
        arvore01.inserir(50);
        arvore01.inserir(60);
        arvore01.inserir(59);
        arvore01.inserir(65);

        arvore02.inserir(50);
        arvore02.inserir(60);
        arvore02.inserir(59);
        arvore02.inserir(65);

        arvore01.caminharCentral(arvore01.raiz);
        System.out.println("");
        arvore01.caminharPre(arvore01.raiz);
        System.out.println("");
        arvore01.caminharPos(arvore01.raiz);
        System.out.println("");
        System.out.println("Altura da arvore : " + arvore01.getAltura());
        System.out.println("Soma da arvore : " + arvore01.getSoma());
        System.out.println("Pares da arvore : " + arvore01.getPares());
        if (arvore01.igual(arvore01, arvore02))
            System.out.println("IGUAIS");
        if (arvore01.div11(arvore01))
            System.out.println("EXISTE DIVISEL POR 11 ");
        // COMPLEXIDADE INSERCAO MC(1) CM(lg(n)) PC(n)
        // COMPLEXIDADE PESQUISA MC(1) CM(lg(n)) PC(n)
        // COMPLEXIDADE CAMINHAMENTO (n)

        ListaSimples listaSimples = new ListaSimples();
        listaSimples.inserirInicio(10);
        listaSimples.inserirInicio(20);
        listaSimples.inserirInicio(30);
        listaSimples.inserirInicio(40);
        listaSimples.inserirInicio(50);
        listaSimples.mostrar();

        ListaDupla listadupla = new ListaDupla();
        listadupla.inserirInicio(5);
        listadupla.inserirInicio(15);
        listadupla.inserirInicio(25);
        listadupla.inserirInicio(35);
        listadupla.inserirInicio(45);
        listadupla.mostrar();
        ArvoreBinaria arvore03 = new ArvoreBinaria();
       arvore03.raiz =  arvore03.toAB(listaSimples.primeiro.prox, listadupla.primeiro.prox);
       arvore03.caminharPos(arvore03.raiz);
        

    }
}