class Contato {
    String nome, telefone, email; // Atributos
    int cpf; // Atributos

    Contato() {
        this(null, null, null, 0);
    }

    // contrutor que sera utilizado
    Contato(String nome, String telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

}

class Celula {
    Contato contato; // Substitue o elemento por contato , ou seja é uma celula que tem um contato
                     // dentro
    Celula prox; // Ponteiro para fazer o encadeamento da lista

    Celula() {
        this(null, null);
    }

    Celula(Contato contato) {
        this(contato, null);
    }

    Celula(Contato contato, Celula prox) {
        this.contato = contato;
        this.prox = prox;
    }
}

class No {
    char elemento; // letra da arvore
    No dir, esq; // ponteiro para direita e esquerda
    Celula primeiro, ultimo; // Celula primeiro e ultima para criar a lista dentro da arvore , ou seja , a
                             // cada nó existe uma lista

    No(char letra) { // construtor do no
        this.elemento = letra;
        this.esq = this.dir = null;
        this.primeiro = this.ultimo = new Celula();
    }
}

class ArvoreDeLista {
    No raiz; // atributos da arvore

    ArvoreDeLista() throws Exception { // Construtor da arvore do jeito que o enunciado manda
        raiz = null;
        inserirArvore('M');
        inserirArvore('F');
        inserirArvore('T');
        inserirArvore('C');
        inserirArvore('I');
        inserirArvore('P');
        inserirArvore('W');
        inserirArvore('A');
        inserirArvore('B');
        inserirArvore('D');
        inserirArvore('E');
        inserirArvore('G');
        inserirArvore('H');
        inserirArvore('J');
        inserirArvore('K');
        inserirArvore('L');
        inserirArvore('N');
        inserirArvore('O');
        inserirArvore('Q');
        inserirArvore('R');
        inserirArvore('S');
        inserirArvore('U');
        inserirArvore('V');
        inserirArvore('X');
        inserirArvore('Y');
        inserirArvore('Z');

    }

    public void inserirArvore(char letra) throws Exception {
        raiz = inserirArvore(letra, raiz);// chama recursao enviando a letra e a raiz
    }

    public No inserirArvore(char letra, No i) throws Exception {
        if (i == null)
            i = new No(letra); // Sempre que i == null significa que o nó tem que ser adicionado lá
        else if (i.elemento > letra)
            i.esq = inserirArvore(letra, i.esq); // Caso letra menor que elemento chama recursao mandando i.esq -- NAO
                                                 // ESQUECER QUE TEMOS QUE COLOCAR I.ESQ = RECURSAO
        else if (i.elemento < letra)
            i.dir = inserirArvore(letra, i.dir);// Caso letra maior que elemento chama recursao mandando i.dir -- NAO
                                                // ESQUECER QUE TEMOS QUE COLOCAR I.dir = RECURSAO
        else
            throw new Exception("ERRO ELEMENTO REPETIDO"); // Caso i.elemnto == letra dá erro
        return i; // retorna i , pois é uma insercao por retorno de referencia
    }

    public void inserirLista(Contato contato) throws Exception {
        raiz = inserirLista(raiz, contato); // chama recursao mandando contato e raiz
    }

    public No inserirLista(No i, Contato contato) throws Exception {
        if (i == null) // Nesse caso , se i == null quer dizer que a letra inicial nao foi achada , ou seja , ERRO
            throw new Exception("Não achei a letra");
        else if (i.elemento == Character.toUpperCase(contato.nome.charAt(0))) { // Se letra for achada irá adiconar na lista
            //INSERIR LISTA NO FINAL
            i.ultimo.prox = new Celula(contato); // ponteiro do ultimo aponta pra nova celula 
            i.ultimo = i.ultimo.prox; // ultimo vira a nova celula 
            //INSERIR LISTA NO FINAL
        } else if (i.elemento > Character.toUpperCase(contato.nome.charAt(0))) // caso i.elemnto maior chama recursao enviando i.esq
            i.esq = inserirLista(i.esq, contato);
        else// caso i.elemnto menor chama recursao enviando i.dir
            i.dir = inserirLista(i.dir, contato);
        return i;

    }

    public boolean pesquisarNome(String nome) { // Muito parecido com a insercao , porem ao inves de inserir , testará alguma condicao
        return pesquisarNome(nome, raiz); // chama recursao mandando nome e raiz
    }

    public boolean pesquisarNome(String nome, No i) {
        boolean resp;
        if (i == null) // se i == null da ruim , pois a letra não foi achada , ou seja , o nome tbm n
            resp = false;
        else if (nome.charAt(0) > i.elemento)
            resp = pesquisarNome(nome, i.dir); // se letra é maior , chama recursao mandando i.dir
        else if (nome.charAt(0) < i.elemento)
            resp = pesquisarNome(nome, i.esq);// se letra é menor , chama recursao mandando i.esq
        else
            resp = pesquisarNome(nome, i.primeiro); // Se letra for igual , chama FUNCAO COM ASSINATURA DIFERENTE 
        return resp;
    }

    public boolean pesquisarNome(String nome, Celula primeiro) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (nome.contentEquals(i.contato.nome))
                resp = true; // Roda a TODOS ELEMENTOS DA LISTA , se achar o nome resp = true
        }
        return resp;
    }

    public boolean pesquisarCPF(int cpf) {
        return pesquisarCPF(cpf, raiz);// chama recursao mandando cpf e raiz
    }

    public boolean pesquisarCPF(int cpf, No i) { // Nesse caso precisamos rodar em todos os nomes para conferir
        boolean resp = false;
        if (i != null) {
            resp = pesquisarCPF(cpf, i.primeiro) || pesquisarCPF(cpf, i.dir) || pesquisarCPF(cpf, i.esq); // Roda nó atual , ou seja raiz, e nos a direita e a esquerda
        }
        return resp;
    }

    public boolean pesquisarCPF(int cpf, Celula primeiro) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.contato.cpf == cpf) // Roda a TODOS ELEMENTOS DA LISTA , se achar o cpf resp = true
                resp = true;
        }
        return resp;

    }

    public void mostrarArvore() {
        mostrarArvore(raiz);
    }

    public void mostrarArvore(No i) {
        if (i != null) {
            mostrarArvore(i.esq);
            System.out.println(i.elemento + " ");
            mostrarLista(i.primeiro);
            mostrarArvore(i.dir);
        }
    }

    public void mostrarLista(Celula primeiro) {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.contato.nome + " ");
        }
    }

}

class TrabalhoTeorico7Estudar {
    public static void main(String[] args) throws Exception {
        ArvoreDeLista arvoreDeLista = new ArvoreDeLista();
        Contato contato1 = new Contato("Felipe", "31980107736", "oi@gmail.com", 1234);
        Contato contato2 = new Contato("Gabriel", "31980107736", "oi@gmail.com", 0000);
        Contato contato3 = new Contato("Jorge", "31980107736", "oi@gmail.com", 1000);
        Contato contato4 = new Contato("Mateus", "31980107736", "oi@gmail.com", 1313);
        arvoreDeLista.inserirLista(contato1);
        arvoreDeLista.inserirLista(contato2);
        arvoreDeLista.inserirLista(contato3);
        arvoreDeLista.inserirLista(contato4);
        arvoreDeLista.mostrarArvore();
        if (arvoreDeLista.pesquisarNome("Jorge"))
            System.out.println("Achei");
        else
            System.out.println("Nao Achei");
        if (arvoreDeLista.pesquisarNome("Luiza"))
            System.out.println("Achei");
        else
            System.out.println("Nao Achei");
        if (arvoreDeLista.pesquisarCPF(1313))
            System.out.println("Achei");
        else
            System.out.println("Nao Achei");
        if (arvoreDeLista.pesquisarCPF(0000))
            System.out.println("Achei");
        else
            System.out.println("Nao Achei");

    }
}