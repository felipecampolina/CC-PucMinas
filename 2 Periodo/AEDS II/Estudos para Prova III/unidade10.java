class NoTRIE {
    public char elemento;
    public int tamanho = 255;
    public NoTRIE[] prox;
    public boolean folha;

    NoTRIE() {
        this(' ');
    }

    NoTRIE(char elemento) {
        this.elemento = elemento;
        prox = new NoTRIE[tamanho];
        for (int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }
        folha = false;
    }

    public int hash(char x) {
        return (int) x;
    }

}

class ArvoreTrie {
    NoTRIE raiz;

    ArvoreTrie() {
        raiz = new NoTRIE();
    }

    public void inserir(String s) {
        inserir(s, raiz, 0);
    }

    public void inserir(String s, NoTRIE no, int i) {
        if (no.prox[s.charAt(i)] == null) {
            no.prox[s.charAt(i)] = new NoTRIE(s.charAt(i));

            if (i == s.length() - 1) {
                no.prox[s.charAt(i)].folha = true;
            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }
        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) { // arvore hash -- tirar a primeira
                                                                                // condicao
            inserir(s, no.prox[s.charAt(i)], i + 1);
        } else {
            System.out.println("Erro");
        }

    }

    public void mostrar() {
        mostrar(" ", raiz);
    }

    public void mostrar(String s, NoTRIE no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public int contarAs() {
        int resp = 0;
        if (raiz != null) {
            resp = contarAs(raiz);
        }
        return resp;
    }

    public int contarAs(NoTRIE no) {
        int resp = (no.elemento == 'A') ? 1 : 0;

        if (no.folha == false) {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    resp += contarAs(no.prox[i]);
                }

            }
        }
        return resp;
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, NoTRIE no, int i) throws Exception {
        boolean resp;
        if (no.prox[s.charAt(i)] == null) {
            resp = false;
        } else if (i == s.length() - 1) {
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if (i < s.length() - 1) {
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }
}

class NoAV {
    public char elemento;
    private Celula primeiro;
    private Celula ultimo;
    public boolean folha;

    NoAV() {
        this(' ');
    }

    NoAV(char elemento) {
        this.elemento = elemento;
        ultimo = primeiro = new Celula();
        folha = false;
    }

    public NoAV inserir(char elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo = ultimo.prox;
        return ultimo.no;
    }

    public NoAV pesquisar(char x) {
        NoAV resp = null;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = i.no;
                i = ultimo;
            }
        }
        return resp;
    }

    public void setFilhoFolha(char x) {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                i.no.folha = true;
                i = ultimo;
            }
        }
    }

    public NoAV[] getFilho() {
        int n = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, n++)
            ;
        NoAV[] vet = new NoAV[n];

        n = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            vet[n++] = i.no;
        }

        return vet;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("] ");
    }
}

class Celula {
    public char elemento;
    public Celula prox;
    public NoAV no;

    Celula() {
        this(' ');
    }

    Celula(char elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.no = null;
    }
}

class ArvoreTrieAB {
    private NoAV raiz;

    ArvoreTrieAB() {
        raiz = new NoAV();
    }

    public void inserir(String s) {
        inserir(s, raiz, 0);
    }

    public void inserir(String s, NoAV no, int i) {
        NoAV filho = no.pesquisar(s.charAt(i));

        if (filho == null) {
            filho = no.inserir(s.charAt(i));

            if (i == s.length() - 1) {
                no.setFilhoFolha(s.charAt(i));
            } else {
                inserir(s, filho, i + 1);
            }
        } else if (filho.folha == false && i < s.length() - 1) {
            inserir(s, filho, i + 1);
        } else {
            System.out.println("ERRO");
        }
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, NoAV no, int i) throws Exception {
        boolean resp;
        NoAV filho = no.pesquisar(s.charAt(i));
        if (filho == null) {
            resp = false;
        } else if (i == s.length() - 1) {
            resp = (filho.folha == true);
        } else if (i < s.length() - 1) {
            resp = pesquisar(s, filho, i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, NoAV no) {
        if (no.folha == true) {
            System.out.println("Palavra:" + (s + no.elemento));
        } else {
            NoAV[] filho = no.getFilho();
            for (int i = 0; i < filho.length; i++) {
                mostrar(s + no.elemento, filho[i]);

            }
        }
    }

}

class NoAB {
    public char elemento;
    public NoAB esq, dir;
    public No2 no;

    /**
     * Construtor da classe.
     */
    public NoAB() {
        this.elemento = 0;
        this.esq = null;
        this.dir = null;
        this.no = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento char inserido na celula.
     */
    public NoAB(char elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.no = new No2(elemento);
    }
}

class No2 {
    public char elemento;
    private NoAB raiz;
    public boolean folha;

    public No2() {
        this(' ');
    }

    public No2(char elemento) {
        this.elemento = elemento;
        raiz = null;
        folha = false;
    }

    public No2 inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
        return pesquisar(x);
    }

    private NoAB inserir(char x, NoAB i) throws Exception {
        if (i == null) {
            i = new NoAB(x);

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public No2 pesquisar(char x) {
        return pesquisar(x, raiz);
    }

    private No2 pesquisar(int x, NoAB i) {
        No2 resp;
        if (i == null) {
            resp = null;

        } else if (x == i.elemento) {
            resp = i.no;

        } else if (x < i.elemento) {
            resp = pesquisar(x, i.esq);

        } else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void setFilhoFolha(char x) {
        setFilhoFolha(x, raiz);
    }

    public void setFilhoFolha(char x, NoAB i) {
        if (i == null) {
            // nada...

        } else if (x == i.elemento) {
            i.no.folha = true;

        } else if (x < i.elemento) {
            setFilhoFolha(x, i.esq);

        } else {
            setFilhoFolha(x, i.dir);
        }
    }

    public int getN() {
        return getN(raiz);
    }

    private int getN(NoAB i) {
        int resp = 0;
        if (i != null) {
            resp = 1 + getN(i.esq) + getN(i.dir);
        }
        return resp;
    }

    public No2[] getFilho() {
        int n = getN();
        No2[] vet = new No2[n];
        getFilho(vet, 0, raiz);
        return vet;
    }

    public int getFilho(No2[] vet, int pos, NoAB i) {
        if (i != null) {
            vet[pos++] = i.no;
            pos = getFilho(vet, pos, i.esq);
            pos = getFilho(vet, pos, i.dir);
        }
        return pos;
    }
}

class unidade10 {
    public static void main(String[] args) {
        ArvoreTrie arv = new ArvoreTrie();

        String array[] = new String[8];
        array[0] = "ABACAXI";
        array[1] = "BALA";
        array[2] = "BOLO";
        array[3] = "ABACATE";
        array[4] = "galo";
        array[5] = "pata";
        array[6] = "pato";
        array[7] = "gato";

        for (int i = 0; i < array.length; i++) {
            arv.inserir(array[i]);
        }
        arv.mostrar();
        System.out.println(arv.contarAs());
    }

}
