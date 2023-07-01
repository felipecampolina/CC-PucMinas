

class Contato{

    //Atributos//
    public String nome;
    public String telefone;
    public String email;
    public int cpf;
     //Atributos//

    //Construtores//

    public Contato(String nome, String telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
    public Contato(){
        this("","","",-1);

    }

    //Construtores//


}
class Celula {
	public Contato contato; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	/**
	 * Construtor da classe.
	 */
    public Celula (){
        this(null, null);
     }
     public Celula(Contato contato){
        this(contato, null);
     }

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
    
	public Celula (Contato contato, Celula prox){
        this.contato = contato;
        this.prox = prox;
     }
}
class Lista {
	private Celula primeiro;
	private Celula ultimo;


	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}


	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Contato x) {
		Celula tmp = new Celula(x);
      tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
      tmp = null;
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Contato x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}


	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Contato removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Contato resp = primeiro.contato;
      tmp.prox = null;
      tmp = null;
		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Contato removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
      Celula i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);

      Contato resp = ultimo.contato; 
      ultimo = i; 
      i = ultimo.prox = null;
      
		return resp;
	}


	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(Contato x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = new Celula(x);
         tmp.prox = i.prox;
         i.prox = tmp;
         tmp = i = null;
      }
   }


	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Contato remover(int pos) throws Exception {
      Contato resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = i.prox;
         resp = tmp.contato;
         i.prox = tmp.prox;
         tmp.prox = null;
         i = tmp = null;
      }

		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.contato.nome + " ");
		}
		System.out.println("] ");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisarNaLista(Contato x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
         if(i.contato == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
   public Contato getElemento(Contato contato1 ) {
	Contato resp = primeiro.contato;
	for (Celula i = primeiro.prox; resp != contato1; i = i.prox) {
		resp = i.contato;
	}
	return resp;
	
}
   
}
class No extends Contato {
	public char letra;
	public No esq, dir;
	public Celula primeiro, ultimo;
	
	public No(char letra) {
		this.letra = letra;
		this.esq = this.dir = null;
		primeiro = ultimo = new Celula();
	}
}
class Agenda {
	private No raiz; 

	public Agenda() {
		raiz = new No ('M');
		raiz.esq = new No ('F');
		raiz.dir = new No ('T');
		raiz.esq.esq = new No ('C');
        raiz.esq.dir = new No ('I');
        raiz.esq.esq.esq = new No ('A');
        raiz.esq.esq.dir = new No ('D');
        raiz.esq.esq.esq.dir = new No ('B');
        raiz.esq.esq.dir = new No ('D');
        raiz.esq.esq.dir.dir = new No ('E');
        raiz.esq.dir = new No ('I');
        raiz.esq.dir.esq = new No ('G');
        raiz.esq.dir.dir = new No ('H');
        raiz.esq.dir.dir = new No ('J');
        raiz.esq.dir.dir.dir = new No ('K');
        raiz.esq.dir.dir.dir.dir = new No ('L');
        raiz.dir.esq = new No ('P');
        raiz.dir.esq.esq = new No ('O');
        raiz.dir.esq.esq.dir = new No ('N');
        raiz.dir.esq.dir = new No ('Q');
        raiz.dir.esq.dir.dir = new No ('R');
        raiz.dir.esq.dir.dir.dir = new No ('S');
        raiz.dir.dir = new No ('W');
        raiz.dir.dir.esq = new No ('U');
        raiz.dir.dir.esq.dir = new No ('V');
        raiz.dir.dir.dir = new No ('X');
        raiz.dir.dir.dir.dir = new No ('Y');
        raiz.dir.dir.dir.dir.dir = new No ('Z');

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
         for(Celula i = no.primeiro.prox; (!resp && i != null); i = i.prox){
            if(i.contato.nome.equals(nome) == true){
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
		if(Character.isLetter(contato.nome.charAt(0))){
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
	
	public boolean pesquisarCpf(int cpf,String nome) {
		return pesquisar(raiz, cpf,nome);
	}

	private boolean pesquisar(No no, int cpf,String nome) {
		boolean resp = false;
		if (no != null) {
			resp = pesquisarCpf(raiz,cpf,nome);
			if(resp == false){
				resp = pesquisar(no.esq, cpf,nome);
				if(resp == false){
					resp = pesquisar(no.dir, cpf,nome);
				}
			}
		}
		return resp;
	}

	public boolean pesquisarCpf(No no, int cpf,String nome) {
        boolean resp;
          if (no == null) { 
           resp = false;
        } else if (Character.toUpperCase(nome.charAt(0)) == no.letra) { 
           resp = false;
           for(Celula i = no.primeiro.prox; (!resp && i != null); i = i.prox){
              if(i.contato.cpf == cpf){
                 resp = true;
              }
           }
        } else if (Character.toUpperCase(nome.charAt(0)) < no.letra) { 
           resp = pesquisarCpf(no.esq, cpf,nome); 
  
        } else { 
           resp = pesquisarCpf(no.dir, cpf,nome); 
        }
        return resp;
      }
      public static void main(String[] args) throws Exception {

		Lista listaTeste = new Lista();

        Contato contato1 = new Contato("Felipe", "31980107736", "123@gmail.com", 123);
        Contato contato2 = new Contato("Gabriel", "31985847736", "123@gmail.com", 1234);
        Contato contato3 = new Contato("Eduardo", "3100007736", "123@gmail.com", 12345);
		Contato contato4 = new Contato("Fernando", "3100507736", "123@gmail.com", 123456);


		listaTeste.inserirInicio(contato1);
		listaTeste.inserirFim(contato2);
		listaTeste.inserirFim(contato3);
		listaTeste.inserirFim(contato4);
		listaTeste.mostrar();
		
        Agenda agenda = new Agenda();
		
        agenda.inserir(listaTeste.getElemento(contato1));
		agenda.inserir(listaTeste.getElemento(contato4));

       if(agenda.pesquisarNome("Felipe"))MyIO.println("Achei!");
	   if(agenda.pesquisarCpf(123,"Felipe"))MyIO.println("Achei!");
	   if(agenda.pesquisarNome("Gabriela"))MyIO.println("Achei!");
	   if(agenda.pesquisarNome("Fernando"))MyIO.println("Achei!");
        
      }

}


//Analise de Complexidade da Arvore
//inserir mc(1) pc(n) cm(lg n)
//remover mc(1) pc(n) cm(lg n)
//pesquisar mc(1) pc(n) cm(lg n)

