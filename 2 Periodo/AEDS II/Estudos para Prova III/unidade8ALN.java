import java.util.*;

class NoAN{
    int elemento;
    NoAN dir, esq;
    boolean cor;

    public NoAN(){
        this(-1);
    }
      public NoAN(int elemento) {
        this(elemento, false, null, null);
      }
    
      public NoAN(int elemento, boolean cor) {
        this(elemento, cor, null, null);
      }
    
      public NoAN(int elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
      }
}
/**
 * Arvore binaria de pesquisa
 * 
 * @author Max do Val Machado
 */
 class Alvinegra {
    private NoAN raiz; // Raiz da arvore.
 
    /**
     * Construtor da classe.
     */
    public Alvinegra() {
       raiz = null;
    }
 
    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(int elemento) {
       return pesquisar(elemento, raiz);
    }
 
    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @param i        NoAN em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private boolean pesquisar(int elemento, NoAN i) {
       boolean resp;
       if (i == null) {
          resp = false;
       } else if (elemento == i.elemento) {
          resp = true;
       } else if (elemento < i.elemento) {
          resp = pesquisar(elemento, i.esq);
       } else {
          resp = pesquisar(elemento, i.dir);
       }
       return resp;
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
       System.out.print("[ ");
       caminharCentral(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharCentral(NoAN i) {
       if (i != null) {
          caminharCentral(i.esq); // Elementos da esquerda.
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          caminharCentral(i.dir); // Elementos da direita.
       }
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
       System.out.print("[ ");
       caminharPre(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharPre(NoAN i) {
       if (i != null) {
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          caminharPre(i.esq); // Elementos da esquerda.
          caminharPre(i.dir); // Elementos da direita.
       }
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
       System.out.print("[ ");
       caminharPos(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharPos(NoAN i) {
       if (i != null) {
          caminharPos(i.esq); // Elementos da esquerda.
          caminharPos(i.dir); // Elementos da direita.
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
       }
    }
 
    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(int elemento) throws Exception {   // insercao de tres elementos manulamente 
       // Se a arvore estiver vazia
       if (raiz == null) {
          raiz = new NoAN(elemento); // se raiz for nula adicionar normalmente o elemento na raiz

          // arvore com um elemento apenas na raiz 
       } 
       
       else if (raiz.esq == null && raiz.dir == null) {
          if (elemento < raiz.elemento) { // confere se elemento eh maior que raiz , se for adiciona ele na direita , caso nao na esquerda
             raiz.esq = new NoAN(elemento); 
          } else {
             raiz.dir = new NoAN(elemento);
          }
 
       // Senao, se a arvore tiver dois elementos (raiz e dir)
       } 
       
       
       else if (raiz.esq == null) {    // confere se arvore tem dois elementos -- raiz e dir 
          if (elemento < raiz.elemento) {
             raiz.esq = new NoAN(elemento); // confere se elemento eh menor que raiz, se for adiciona ele na raiz.esq
 
          } else if (elemento < raiz.dir.elemento) { // confere se elemento é menor que o elemento direito da raiz 
             raiz.esq = new NoAN(raiz.elemento);// raiz.esq vira a raiz , pq elemento é maior q raiz e menor q direita
             raiz.elemento = elemento; // raiz vira o elemento novo 
 
          } else {// confere se elemento é menor que o elemento direito da raiz 
             raiz.esq = new NoAN(raiz.elemento); // raiz.esq vira raiz 
             raiz.elemento = raiz.dir.elemento; // raiz vira elemento da direita
             raiz.dir.elemento = elemento; // elemento da direita vira novo elemento 

          }
          raiz.esq.cor = raiz.dir.cor = false; // setar cores da dir e esq como falso 
 
       // Senao, se a arvore tiver dois elementos (raiz e esq)
       } 
       
       
       else if (raiz.dir == null) {// confere se arvore tem dois elementos -- raiz e esq 
          if (elemento > raiz.elemento) { // confere se elemento é maior q raiz.elemento , se sim , seta dir como novo elemento
             raiz.dir = new NoAN(elemento);

 
          } else if (elemento > raiz.esq.elemento) { // confere se elemento é maior que elemento da raiz.esq
             raiz.dir = new NoAN(raiz.elemento); //raiz vai par raiz.dir
             raiz.elemento = elemento; // raiz vira novo elemento 
          } else { // confere se elemento é menor que raiz.elemento

             raiz.dir = new NoAN(raiz.elemento); // raiz vai para raiz.dir
             raiz.elemento = raiz.esq.elemento; // elemento da esquerda vira raiz
             raiz.esq.elemento = elemento; ;// elemento novo adicionado na esq

          }
          raiz.esq.cor = raiz.dir.cor = false; // setar cores como falso
 
       // Senao, a arvore tem tres ou mais elementos
       } else { // arvore com mais de tres elementos 
          inserir(elemento, null, null, null, raiz);
       }
       raiz.cor = false; }// setar cor raiz como falso    }
 
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento > avo.elemento) { // rotacao a esquerda ou direita-esquerda
             if (i.elemento > pai.elemento) {
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento < pai.elemento) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento < bisavo.elemento) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
          System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir("
                + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
       } // if(pai.cor == true)
    }
 
    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @param avo      NoAN em analise.
     * @param pai      NoAN em analise.
     * @param i        NoAN em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(int elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
       if (i == null) { // raiz for nula 
          if (elemento < pai.elemento) { // teste se elemento é menor q pai
             i = pai.esq = new NoAN(elemento, true); // i = pai.esq = novo no de elemento com cor preta
          } else {
             i = pai.dir = new NoAN(elemento, true);// i = pai.dir = novo no de elemento com cor preta
          }
          if (pai.cor == true) {  // se pai tbm eh preto , é ncessario balancear 
             balancear(bisavo, avo, pai, i); 
          }
       } else {
          // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) { // confere se eh um no 4-no
             i.cor = true; // seta cor de i como true e seus filhos false
             i.esq.cor = i.dir.cor = false; // seta cor de i como true e seus filhos false
             if (i == raiz) {
                i.cor = false; // se i == raiz seta cor como false
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i); // se pai é preto precisa balancear 
             }
          }
          if (elemento < i.elemento) {
             inserir(elemento, avo, pai, i, i.esq); // recursao 
          } else if (elemento > i.elemento) {
             inserir(elemento, avo, pai, i, i.dir); // recursao 
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private NoAN rotacaoDir(NoAN no) {
       System.out.println("Rotacao DIR(" + no.elemento + ")");
       NoAN noEsq = no.esq;
       NoAN noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private NoAN rotacaoEsq(NoAN no) {
       System.out.println("Rotacao ESQ(" + no.elemento + ")");
       NoAN noDir = no.dir;
       NoAN noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private NoAN rotacaoDirEsq(NoAN no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private NoAN rotacaoEsqDir(NoAN no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }
 class No {
   public char elemento;
	private NoAB raiz;
   public boolean folha;
   
   public No (){
      this(' ');
   }

   public No (char elemento){
      this.elemento = elemento;
		raiz = null;
      folha = false;
   }

   public No inserir(char x) throws Exception {
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

	public No pesquisar(char x) {
		return pesquisar(x, raiz);
	}

	private No pesquisar(int x, NoAB i) {
      No resp;
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

   public void setFilhoFolha(char x){
      setFilhoFolha(x, raiz);
   }
   public void setFilhoFolha(char x, NoAB i){
		if (i == null) {
         //nada...

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

   public No[] getFilho(){
      int n = getN();
      No[] vet = new No[n];
      getFilho(vet, 0, raiz);
      return vet;
   }

   public int getFilho(No[] vet, int pos, NoAB i){
      if(i != null){
         vet[pos++] = i.no;
         pos = getFilho(vet, pos, i.esq);
         pos = getFilho(vet, pos, i.dir);
      }
      return pos;
   }
}
class unidade8ALN{
    public static void main(String[] args) throws Exception {
        Alvinegra alvinegra = new Alvinegra();
        alvinegra.inserir(5);
        alvinegra.inserir(15);
        alvinegra.inserir(2);
        alvinegra.inserir(7);
        alvinegra.inserir(8);
        alvinegra.inserir(9);
        alvinegra.inserir(10);
        alvinegra.inserir(13);
        alvinegra.caminharPre();
        alvinegra.caminharCentral();
        
    }
}
