class Contato {
    public String nome;
    public int telefone;
    public String email;
    public int cpf;

    Contato(String nome, int telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
}

class Celula {
    public Contato contato;
    public Celula prox;

    // construtores
    public Celula(Contato contato, Celula prox) {
        this.contato = contato;
        this.prox = prox;
    }

    public Celula() {
        this(null, null);
    }

    public Celula(Contato contato) {
        this(contato, null);
    }

}

class No {
    public char letra;
    public No esq, dir;
    public Celula primeiro, ultimo;

    // construtores
    public No(char letra) {
        this.letra = letra;
        this.esq = this.dir = null;
        primeiro = ultimo = new Celula();
    }
}

// CLASSE ARVORE
class Agenda2 {
    private No raiz;

    public Agenda2() {
        raiz = new No('M');
        raiz.esq = new No('F');
        raiz.dir = new No('T');
        raiz.esq.esq = new No('C');
        raiz.esq.dir = new No('I');
        raiz.esq.esq.esq = new No('A');
        raiz.esq.esq.dir = new No('D');
        raiz.esq.esq.esq.dir = new No('B');
        raiz.esq.esq.dir = new No('D');
        raiz.esq.esq.dir.dir = new No('E');
        raiz.esq.dir = new No('I');
        raiz.esq.dir.esq = new No('G');
        raiz.esq.dir.esq.dir = new No('H');
        raiz.esq.dir.dir = new No('J');
        raiz.esq.dir.dir.dir = new No('K');
        raiz.esq.dir.dir.dir.dir = new No('L');
        raiz.dir.esq = new No('P');
        raiz.dir.esq.esq = new No('O');
        raiz.dir.esq.esq.dir = new No('N');
        raiz.dir.esq.dir = new No('Q');
        raiz.dir.esq.dir.dir = new No('R');
        raiz.dir.esq.dir.dir.dir = new No('S');
        raiz.dir.dir = new No('W');
        raiz.dir.dir.esq = new No('U');
        raiz.dir.dir.esq.dir = new No('V');
        raiz.dir.dir.dir = new No('X');
        raiz.dir.dir.dir.dir = new No('Y');
        raiz.dir.dir.dir.dir.dir = new No('Z');

    }

    public boolean pesquisarNome(String nome) {
        return pesquisarNome(raiz, nome);
    }

    private boolean pesquisarNome(No no, String nome) {
        boolean resp;
        if (no == null) {
            resp = false;
        } else if (Character.toUpperCase(nome.charAt(0)) == no.letra) {
            resp = false;
            for (Celula i = no.primeiro.prox; (!resp && i != null); i = i.prox) {
                if (i.contato.nome.equals(nome) == true) {
                    resp = true;
                }
            }
        } else if (Character.toUpperCase(nome.charAt(0)) < no.letra) {
            resp = pesquisarNome(no.esq, nome);

        } else {
            resp = pesquisarNome(no.dir, nome);
        }
        return resp;
    }

    public void inserir(Contato contato) throws Exception {
        if (Character.isLetter(contato.nome.charAt(0))) {
            raiz = inserir(raiz, contato);
        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    private No inserir(No no, Contato contato) throws Exception {
        // insere o nó com a letra
        if (no == null) {
            no = new No(Character.toUpperCase(contato.nome.charAt(0)));
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;

            // insere o contatinho
        } else if (Character.toUpperCase(contato.nome.charAt(0)) == no.letra) {
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;

            // letra menor, caminha para a esquerda
        } else if (Character.toUpperCase(contato.nome.charAt(0)) < no.letra) {
            no.esq = inserir(no.esq, contato);

            // letra maior, caminha para a direita
        } else {
            no.dir = inserir(no.dir, contato);
        }
        return no;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.letra + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    public boolean pesquisarCPF(int cpf) {
        return pesquisarCPF(raiz, cpf);
    }

    public boolean pesquisarCPF(No i, int cpf) {
        boolean teste = false;
        if (i != null) {
            if (!teste)
                teste = pesquisarCPF(i.esq, cpf); // Elementos da esquerda.
            for (Celula j = i.primeiro.prox; j != null; j = j.prox) {
                if (j.contato.cpf == cpf) {
                    teste = true;
                }
            }
            if (!teste)
                teste = pesquisarCPF(i.dir, cpf); // Elementos da direita.
        } else {
            teste = false;
        }
        return teste;
    }

    public static void main(String[] args) throws Exception {
        Agenda2 agenda2 = new Agenda2();
        Contato contatinho2 = new Contato("Gabriel", 21999320, "bibizinha@gmail", 12353);
        Contato contatinho = new Contato("Gabriela", 21999320, "bibizinha@gmail", 1211221);
        agenda2.inserir(contatinho);
        agenda2.inserir(contatinho2);
        if (agenda2.pesquisarNome("Gabriel"))
            System.out.println("SIM");
        if (agenda2.pesquisarCPF(1211221))
            System.out.println("SIM");
        else
            System.out.println("NAO");
        if (agenda2.pesquisarCPF(12353))
            System.out.println("SIM");
        else
            System.out.println("NAO");
        if (agenda2.pesquisarCPF(5532))
            System.out.println("SIM");
        else
            System.out.println("NAO");
    }
}
