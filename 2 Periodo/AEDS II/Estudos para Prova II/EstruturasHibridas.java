class No1 {
   char elemento; // char elemento -- letra da 1 arvore
   No1 dir, esq; // nos direita e esquerda
   No2 arvore; // No 2 arvore -- criacao de uma arvore Binaria a cada nó da primeira arvore

   // construtores//
   No1(char x) {
      this.elemento = x;
      this.dir = null;
      this.esq = null;
      this.arvore = null;

   }

   No1(char x, No1 dir, No1 esq) {
      this.elemento = x;
      this.dir = dir;
      this.esq = esq;
      this.arvore = null;

   }
   // construtores//
}

class No2 { // Mesma coisa que o nó da arvore binaria
   String elemento;
   No2 dir, esq;

   No2(String x) {
      this.elemento = x;
      this.esq = this.dir = null;
   }

   No2(String x, No2 dir, No2 esq) {
      this.elemento = x;
      this.dir = dir;
      this.esq = esq;
   }

}

class ArvoreBinariaDeArvore {
   No1 raiz; // cria raiz do tipo nó 1 , ou seja , tem elemento , dir , esq e ARVORE

   // contrutor //
   public ArvoreBinariaDeArvore() throws Exception {
      raiz = null;
      inserir('M');
      inserir('F');
      inserir('T');
      inserir('C');
      inserir('I');
      inserir('P');
      inserir('W');
      inserir('A');
      inserir('B');
      inserir('D');
      inserir('E');
      inserir('G');
      inserir('H');
      inserir('J');
      inserir('K');
      inserir('L');
      inserir('N');
      inserir('O');
      inserir('Q');
      inserir('R');
      inserir('S');
      inserir('U');
      inserir('V');
      inserir('X');
      inserir('Y');
      inserir('Z');
   }
   // contrutor //

   // PRIMEIRA ARVORE = arvore de letras que possui uma arvore a cada nó
   // SEGUNDA ARVORE = arvore de string dentro da primeira

   public void inserir(char x) throws Exception { // Inserir CARACTER na primeira arvore
      raiz = inserir(x, raiz); // chama recursao passando a raiz
   }

   private No1 inserir(char x, No1 i) throws Exception {
      if (i == null) { // caso i seja null fará a insrção de uma nova letra
         i = new No1(x); // insere letra criando no do tipo 1 -- TOMAR CUIDADO COM O TIPO DE INSERCAO,
                         // POIS EXISTEM DUAS - passagem de pai e retorno de referencia

      } else if (x < i.elemento) {
         i.esq = inserir(x, i.esq); // ve se a letra é menor que o elemento , caso sim , chama recursao passando
                                    // i.esq

      } else if (x > i.elemento) {
         i.dir = inserir(x, i.dir);// ve se a letra é maior que o elemento , caso sim , chama recursao passando
                                   // i.dir

      } else {
         throw new Exception("Erro ao inserir!"); // confere se o elemento é repetido, caso sim dá erro
      }

      return i; // retorna o endereço de i - retorno de refencia
   }

   public void inserirString(String s) throws Exception {
      inserirString(s, raiz); // chama recursao para insercao na SEGUNDA ARVORE
   }

   public void inserirString(String s, No1 i) throws Exception {
      if (i == null) {
         throw new Exception("Erro ao inserir: caractere invalido!"); // caso i seja null , significa que a letra não
                                                                      // foi encotrada

      } else if (s.charAt(0) < i.elemento) {
         inserirString(s, i.esq); // confere se a primeira letra da palavra é menor que i.elemento , caso sim ,
                                  // chama recursao mandando i.esq

      } else if (s.charAt(0) > i.elemento) {
         inserirString(s, i.dir);// confere se a primeira letra da palavra é maior que i.elemento , caso sim ,
                                 // chama recursao mandando i.dir
      } else {
         i.arvore = inserirString(s, i.arvore); // Executara esse codigo quando achar a letra na primeira arvore e irá
                                                // mandar a palavra mais o NO2 - ou seja, a segunda arovore
      }
   }

   public No2 inserirString(String s, No2 i) throws Exception {
      if (i == null) {
         i = new No2(s);// caso i seja null fará a insrção de uma nova palavra na SEGUNDA ARVORE

      } else if (s.compareTo(i.elemento) < 0) {
         i.esq = inserirString(s, i.esq);// ve se a palavra é menor que o elemento , caso sim , chama recursao passando
                                         // i.esq

      } else if (s.compareTo(i.elemento) > 0) {
         i.dir = inserirString(s, i.dir); // ve se a palavra é maior que o elemento , caso sim , chama recursao passando
                                          // i.dir

      } else {
         throw new Exception("Erro ao inserir: elemento existente!"); // ve se a palavra é maior igual o elemento , caso
                                                                      // sim , vai dar erro
      }

      return i; // retorna o endereco do no2 -- insercao por retorno de referencia
   }

   void mostrar() {
      mostrar(raiz); // chama recursao passando raiz
   }

   public void mostrar(No1 i) {
      if (i != null) {
         mostrar(i.esq); // caminhamento em ordem - ordenado
         System.out.println("Letra: " + i.elemento); // printa LETRA
         mostrar(i.arvore); // entra na segunda arove
         mostrar(i.dir);// caminhamento em ordem - ordenado
      }
   }

   public void mostrar(No2 i) {
      if (i != null) {
         mostrar(i.esq);// caminhamento em ordem - ordenado
         System.out.println(i.elemento); // PRINTA palavra
         mostrar(i.dir);// caminhamento em ordem - ordenado
      }
   }

   public boolean pesquisar(String palavra) {
      return pesquisar(palavra, raiz); // chama recursao passando a palavra e a raiz da primeira arvore
   }

   public boolean pesquisar(String palavra, No1 i) {
      boolean resp;
      if (i == null)
         resp = false; // se i == null siginifica que letra n existe
      else if (Character.toUpperCase(palavra.charAt(0)) > i.elemento)
         resp = pesquisar(palavra, i.dir); // confere se letra é maior que i.elemento , caso sim , manda i.dir
      else if (Character.toUpperCase(palavra.charAt(0)) < i.elemento)
         resp = pesquisar(palavra, i.esq);// confere se letra é menor que i.elemento , caso sim , manda i.esq
      else {
         resp = pesquisar(palavra, i.arvore); // Achou a letra - manda a palavra e a arovre de palavra daquela letra
      }
      return resp;
   }

   public boolean pesquisar(String palavra, No2 i) {
      boolean resp;
      if (i == null) {
         resp = false;// se i == null siginifica que palalra n existe
      } else if (palavra.compareTo(i.elemento) < 0) {
         resp = pesquisar(palavra, i.esq);// confere se palavra é menor que i.elemento , caso sim , manda i.esq

      } else if (palavra.compareTo(i.elemento) > 0) {
         resp = pesquisar(palavra, i.dir);// confere se palavra é maior que i.elemento , caso sim , manda i.dir

      } else {
         resp = true; // achou palavra
      }
      return resp; // retorna resposta
   }

   // outras funcoes tem um conceito parecido com a pesquisar, porém a condicao
   // será diferente , porém para caminhar entre as arovres eh a msma coisa

   public boolean hasStringTam10() {
      return hasStringTam10(raiz);
   }

   public boolean hasStringTam10(No1 i) {
      boolean resp;
      if (i == null)
         resp = false;
      else
         resp = hasStringTam10(i.arvore) || hasStringTam10(i.dir) || hasStringTam10(i.esq);
      return resp;
   }

   public boolean hasStringTam10(No2 i) {
      boolean resp;
      if (i == null)
         resp = false;
      else if (i.elemento.length() == 10)
         resp = true;
      else
         resp = false;
      return resp;
   }

   public boolean hasStringTam10(char c) {
      return hasStringTam10(raiz, c);
   }

   public boolean hasStringTam10(No1 i, char c) {
      boolean resp;
      if (i == null) {
         resp = false;

      } else if (c < i.elemento) {
         resp = hasStringTam10(i.esq, c);

      } else if (c > i.elemento) {
         resp = hasStringTam10(i.dir, c);

      } else {
         resp = hasStringTam10(i.arvore);
      }
      return resp;
   }
}

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

class CelulaMatriz {
   int elemento;
   CelulaMatriz sup, inf, esq, dir;
   Celula primeiro, ultimo; // Lista dentro da matriz

   CelulaMatriz() {
      this(0);
   }

   public CelulaMatriz(int elemento) {
      this(elemento, null, null, null, null);
   }

   public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir) {
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
      this.primeiro = this.ultimo = new Celula();
   }
}

class MatrizdeLista {
   CelulaMatriz inicio;
   int linha, coluna;

   MatrizdeLista() {
      this(3, 3);
   }

   MatrizdeLista(int linha, int coluna) {
      int z = 0;
      this.linha = linha; // definindo o numero de linhas
      this.coluna = coluna; // definindo o numero de colunas
      this.inicio = new CelulaMatriz(z); // Cria uma nova celula Matriz
      z++;
      CelulaMatriz aux = this.inicio; // cria um auxiliar que aponta para celula inicio
      for (int j = 0; j < coluna; j++) { // set das colunas
         CelulaMatriz nova = new CelulaMatriz(z); // Cria uma nova celula
         aux.dir = nova; // aux.dir apontara para nova celula
         nova.esq = aux;// nova.esq apontara para nova celula
         aux = aux.dir; // aux vira a nova celula
         z++;
         // Nesse ponto ambas celulas serao conectadas como se fosse uma lista encadeada
         // dupla
      }
      CelulaMatriz superior = this.inicio; // celula superior aponta para o inicio

      for (int i = 0; i < linha - 1; i++) { // roda pelo o numero de linahs
         CelulaMatriz lateral = new CelulaMatriz(z); // cria nova celula
         lateral.sup = superior; // criacao da linha de baixo - ponteiro sup aponta para inicio
         superior.inf = lateral;// criacao da linha de baixo - ponteiro inf aponta para lateral(nova celula)
         aux = lateral; // aux vira lateral para manipulacao da mesma
         z++;

         for (int g = 0; g < coluna - 1; g++) { // rola pelo o numero de colunas
            superior = superior.dir; // mover para proximo elemento da coluna
            CelulaMatriz nova = new CelulaMatriz(z); // cria celula nova
            nova.esq = lateral; // nova.esq aponta para lateral
            lateral.dir = nova; // lateral.dir aponta para nova
            superior.inf = nova; // superior.inf , que é a celula na mesma pposicao so que na linha acima aponta
                                 // para nova
            nova.sup = superior; // nova.sup aponta para superior
            lateral = nova; // anda com lateral para nova e repete processo
            z++;
         }
         superior = aux; // pula linha

      }
      superior = null; // free
      aux = null; // free

   }

   void inserir(int i, int j, int elemento) {
      CelulaMatriz pi = null;

      int ii = 0;
      int jj = 0;
      CelulaMatriz pj = pi;
      for(pi = inicio; ii < i; ii++, pi = pi.dir){
         for( pj = pi; jj < j; jj++, pj = pj.inf);
      }
      System.out.println("POSICAO QUE ESTA SENDO INSERIDO: "+ "("+i+","+j+")"+ pj.elemento);
      Celula k;
       for (k = pj.primeiro.prox; k != null; k = k.prox);
       pj.ultimo.prox = new Celula(elemento);
       pj.ultimo =  pj.ultimo.prox;
   }

   

   public void mostrarMatriz(){
      CelulaMatriz l = this.inicio;
      for(int i = 0; i < linha; i++){
         CelulaMatriz c = l;
         for(int j = 0; j < coluna; j++){
            System.out.print(c.elemento+" ");
            c = c.dir;
         }  
         System.out.println("");
         l = l.inf;   
      }
   }
   
   void mostrarLista(int i, int j){
      CelulaMatriz pi = inicio, pj = pi;

      for(int ii = 0; ii < i; ii++, pi = pi.dir){
         for(int jj = 0; jj < j; jj++, pj = pj.inf);
      }

      for(Celula k = pj.primeiro.prox; k != null; k = k.prox){
         System.out.println(k.elemento + " ");
      }

   }
   boolean pesquisar(int elemento){
      boolean resp = false;

      for(CelulaMatriz i = inicio; !resp && i != null; i = i.dir){
         for(CelulaMatriz j = i; !resp && j != null; j = j.inf){
            if(j.elemento == elemento){
               resp = true;
            } else {
               for(Celula k = j.primeiro.prox; k != null; k = k.prox){
                  if(k.elemento == elemento){
                     resp = true;
                     k = j.ultimo;
                  }
               }
            }
         }
      }

      return resp;
   }

   boolean pesquisar(int i, int j, int elemento){
      boolean resp = false;
      CelulaMatriz pi, pj = null;
      int ii = 0 ,  jj = 0;

      for(pi = inicio; ii < i; ii++, pi = pi.dir){
         for(pj = pi; jj < j; jj++, pj = pj.inf);
      }

      for(Celula k = pj.primeiro.prox; k != null; k = k.prox){
         if(k.elemento == elemento){
            resp = true;
            k = pj.ultimo;
         }
      }
      return resp;

   }

}

class EstruturasHibridas {
   public static void main(String[] args) throws Exception {
      ArvoreBinariaDeArvore teste01 = new ArvoreBinariaDeArvore();
      try {
         teste01.inserirString("Macaco");
         teste01.inserirString("Maa");
         teste01.inserirString("Acaaa");
         teste01.inserirString("Banana");
         teste01.inserirString("FelipeCamo");

      } catch (java.lang.Exception e) {
      }
      teste01.mostrar();
      if (teste01.pesquisar("Macaco"))
         System.out.println("ACHEI");
      if (teste01.pesquisar("Banana"))
         System.out.println("ACHEI");
      if (teste01.hasStringTam10())
         System.out.println("ACHEI PALAVRA DE 10");

      MatrizdeLista teste02 = new MatrizdeLista(3,3);
      
      teste02.inserir(1, 1, 100);
      teste02.inserir(1, 2, 200);
      teste02.inserir(1, 2, 300);
      teste02.inserir(1, 1, 400);
      teste02.inserir(2, 2, 300);


      teste02.mostrarMatriz();
      teste02.mostrarLista(1, 1);
      teste02.mostrarLista(1, 2);
      if(teste02.pesquisar(400))System.out.println("ACHEI");
      else System.out.println("NAO ACHEI");
      if(teste02.pesquisar(1400))System.out.println("ACHEI");
      else System.out.println("NAO ACHEI");

   }

}