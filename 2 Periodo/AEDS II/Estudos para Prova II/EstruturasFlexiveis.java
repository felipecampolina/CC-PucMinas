class Celula {
    int elemento; // elemento armazenado
    Celula prox; // ponteiro para proxima celula

    Celula() {
        this(0);
    }

    Celula(int x) {
        this.elemento = x; // adiciona o elemento
        this.prox = null; // adiciona a proxima celula = null
    }
}

class Pilha {
    Celula topo; // cria uma celula topo

    Pilha() {
        topo = null; // topo = null
    }

    public void inserir(int x) { // insere no INICIO
        Celula tmp = new Celula(x); // cria uma nova celula temp
        tmp.prox = topo; // ponteiro de temp = topo , ou seja, null
        topo = tmp; // iguala topo com tmp
        tmp = null; // joga fora temp
    }

    public int remover() throws Exception { // remove do INICIO
        if (topo == null)
            throw new Exception("ERRO!"); // confere de topo é null , pois se verdadeiro significa que a pilha ta vazia
        int elemento = topo.elemento; // pega o elemento do topo e guarda na variavel elemento
        Celula tmp = topo; // duplica topo , criando uma temp
        topo = topo.prox; // topo vai virar o proximo
        tmp.prox = null; // libera tmp
        tmp = null; // libera tmp
        return elemento; // retorna elemento
    }

    public void mostrar() {
        System.out.print("[");
        for (Celula i = topo; i != null; i = i.prox) { // rodar na pilha até que i == null , ou seja até o ultimo
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public int somaElementos() {
        int soma = topo.elemento;
        for (Celula i = topo.prox; i != null; i = i.prox) {
            soma += i.elemento;
        }
        return soma;

    }

    public int getSoma() {
        return getSoma(topo);
    }

    private int getSoma(Celula i) {
        int resp = 0;
        if (i != null) {
            resp += i.elemento + getSoma(i.prox);
        }
        return resp;
    }

    public int getMaior() {
        int maior = topo.elemento;
        for (Celula i = topo.prox; i != null; i = i.prox) {
            if (i.elemento > maior)
                maior = i.elemento;

        }
        return maior;
    }

}

class Fila {
    Celula primeiro, ultimo;

    Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x) {// insere no FINAL
        ultimo.prox = new Celula(x);// ponteiro de ultima aponta para uma nova celula com elemento x
        ultimo = ultimo.prox;// ultimo virá o ultimo.prox , que acabou de ser criado
    }

    public int removerPreserva() throws Exception { // preserva nó cabeça
        if (primeiro == ultimo)
            throw new Exception("ERRO!"); // confere se a fila ta vazia
        Celula tmp = primeiro.prox;// cria celula tmp que será a primeira celula sem contar com o nó cabeça
        primeiro = primeiro.prox.prox; // primeiro vira o elemento de 3 posicao
        int elemento = tmp.elemento; // elemnto vira elemento de tmp, ou seja, da celula que sera removida
        tmp.prox = null; // quebra ponteiro da celula removida
        tmp = null; // tira celula removida
        return elemento; // devolve elemento
    }

    public int removerSemPreserva() throws Exception { // nao presrva nó cabeca
        if (primeiro == ultimo)
            throw new Exception("ERRO!"); // confere se a fila ta vazia
        Celula tmp = primeiro; // cria celula tmp que sera o nó cabeça
        primeiro = primeiro.prox; // primeiro vira o segundo
        int elemento = primeiro.elemento; // elemento sera o elemento do segundo
        tmp.prox = null; // quebra ligacao de tmp
        tmp = null; // quebra tmp
        return elemento; // retorna elemento
    }
    /*
     * Existe uma diferenca entre as remocoes se irá ou nao preservar o no cabeca,
     * entretanto se escolhermos usar a segunda opcao devemos atualizar o mostrar,
     * visto que ele inicia no primeiro.prox
     */

    public void mostrar() {
        System.out.print("[");
        for (Celula i = primeiro.prox; i != null; i = i.prox) { // rodar na pilha até que i == null , ou seja até o
                                                                // ultimo
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public int getMaior() {
        int maior = primeiro.prox.elemento;
        for (Celula i = primeiro.prox.prox; i != null; i = i.prox) {
            if (maior < i.elemento)
                maior = i.elemento;
        }
        return maior;
    }

    public int getTerceiroElemento() {
        return (primeiro.prox.prox.prox.elemento);
    }

    public int getSoma() {
        int soma = primeiro.prox.elemento;
        for (Celula i = primeiro.prox.prox; i != null; i = i.prox) {
            soma += i.elemento;
        }
        return soma;
    }

    public Fila inverteOrdem() throws Exception {
        int array[] = new int[20];
        int x = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            array[x] = removerSemPreserva();
            x++;
        }
        Fila filaInvetida = new Fila();
        for (int i = x; i >= 0; i--) {
            filaInvetida.inserir(array[i]);
        }
        filaInvetida.removerSemPreserva();
        return filaInvetida;
    }

    int contar() {
        return contar(primeiro.prox);
    }

    int contar(Celula i) {
        int resp;
        if (i == null) {
            resp = 0;
        } else if (i.elemento % 2 == 0 && i.elemento % 5 == 0) {
            resp = 1 + contar(i.prox);
        } else {
            resp = contar(i.prox);
        }
        return resp;
    }

    void metodoDoidao() {
        Celula fim = ultimo;
        while (primeiro != fim) {
            ultimo.prox = new Celula(primeiro.prox.elemento);
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            tmp = tmp.prox = null;
            ultimo = ultimo.prox;
        }
        fim = null;
    }
    Celula toFila(Celula topo){
        Celula primeiro = new Celula();
        Celula ultimo = primeiro;
        ultimo = toFila(topo,ultimo);
        return primeiro;

    }
    Celula toFila(Celula i,Celula ultimo){
        if(i!=null){
            ultimo = toFila(i.prox,ultimo);
            ultimo.prox = new Celula(i.elemento);
            ultimo = ultimo.prox;
        }
        return ultimo;
    }

}
class listaSimples{
    Celula primeiro ,ultimo;

    listaSimples(){
        primeiro = new Celula();
        ultimo = primeiro;
    }
    public void inserirFim(int x ){
        ultimo.prox = new Celula(x); // ponteiro do ultimo sera  uma nova celula com o elemento x dentro 
        ultimo = ultimo.prox; // ultimo vira ultimo.prox
    }
    public int removerInicio() throws Exception{
        if(primeiro==ultimo)throw new Exception("ERRO!"); // confere se lista esta vazia
        Celula tmp = primeiro; // tmp vira no cabeca
        primeiro = primeiro.prox; // primeiro vira segundo, ou seja, primeiro com elemento
        int elemento = primeiro.elemento; // elemento vira o primeiro 
        tmp.prox = null; // tmp == null
        tmp = null; // tmp = null  tira o no cabeca 
        return elemento;

    }
    public void inserirInicio(int x){
        Celula tmp = new Celula(x); // cria uma celula tmp com o elemento
        tmp.prox = primeiro.prox; // ponteiro de temp sera = ponteiro de no cabeaca 
        primeiro.prox = tmp; // no cabeca aponta pra temp
        if(primeiro==ultimo)ultimo = tmp; // se lista ta vazia ultimo sera temp
        tmp = null; // tmp vira null
    }
    public int removerFinal() throws Exception{
    if(primeiro==ultimo)throw new Exception("ERRO!"); // confere se lista esta vazia
    Celula i;// cria celula i = tmp
    for(i = primeiro; i.prox!=null; i = i.prox); // acha anti penultimo 
    int elemento = ultimo.elemento; // atribue elemento como o ultimo elemento
    ultimo = i; // faz o ultimo virar antipenultimo
    i = ultimo.prox = null; // i e ultimo.prox vira null
    return elemento; // retorna elemento removido
    }
    public int getTamanho(){
        int resp = 0;
        for(Celula i = primeiro.prox ; i!=null ; i = i.prox){
            resp++;
        }
        return resp;

    }
    public void inserir(int x, int pos) throws Exception{
        int tamanho = getTamanho(); // Pega tamanho da lista
        if(pos<0 || pos>tamanho)throw new Exception("ERRO"); // Posicao invalida da erro
        else if(pos==0)inserirInicio(x); // caso pos = 0 inserir no inicio
        else if(pos==tamanho)inserirFim(x); // caso pos ==tamanho inserir no fim
        else{
            Celula i = primeiro; // cria celula i , como no cabeca 
            for(int j = 0 ; j<pos; j++,i = i.prox); // acha celula da posicao anterior
            Celula tmp = new Celula(x); // cria noca celula com x 
            tmp.prox = i.prox; // atribue o ponteiro da nova celula para o ponteiro da proxima de i
            i.prox = tmp;// atribue o ponteiro da proxima para tmp 
            tmp=i=null; // free 

        }
    }
    public int remover (int pos ) throws Exception{
        int tamanho = getTamanho();//Pega tamanho
        int elemento; // declara elemento 
        if(ultimo == primeiro || pos<0 || pos >=tamanho )throw new Exception("ERRO"); // Posicao invalida da erro e lista vazia
        else if(pos==0)elemento = removerFinal(); // caso pos = 0 removerFinal
        else if (pos==tamanho-1)elemento = removerInicio(); //caso pos == tamanho-1 removerInicio
        else{
            Celula i = primeiro; // celula i = no cabeca
            for(int j = 0 ; j<pos ; j++,i = i.prox); // acha celula anterior da posicao
            Celula tmp = i.prox; // celula tmp = celula da pos
            elemento = tmp.elemento; // elemento = elemento da pos
            i.prox = tmp.prox; // tranforma o ponteiro da uma antes da pos para uma depois da pos
            tmp.prox = null; // free
            i = tmp = null; // free
        }
        return elemento; // retorna elemnto
    }
    public void mostrar(){
        System.out.print("[");
        for(Celula i = primeiro.prox ; i!=null ; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

class CelulaDupla{
    int elemento;
    CelulaDupla prox,ant;

    CelulaDupla(){
        this(0);
    }
    CelulaDupla(int x){
        this.elemento = x;
        this.ant = this.prox = null;
    }
}
class ListaDupla{
    CelulaDupla primeiro,ultimo;

    ListaDupla(){
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }
    public void inserirInicio(int x){
        CelulaDupla tmp = new CelulaDupla(x);// cria uma celula dupla com o elemento nela
        tmp.ant = primeiro; // ponteiro anterior de tmp aponta para primeiro 
        tmp.prox = primeiro.prox; // ponteiro posterior de tmp aponta para primeiro.prox
        primeiro.prox = tmp; // ponteiro prox do primeiro aponta para tmp
        if(ultimo == primeiro)ultimo=tmp; // se lista vazia ultimo = tmp 
        else{
             tmp.prox.ant = tmp; // se nao ponteiro anterior da celula posterior de tmp aponta para tmp
        }
        tmp =null; // free tmp 
    }
    public void inserirFim(int x ){
        ultimo.prox = new CelulaDupla(x); // ponteiro do ultimo aponta para nova celula com elemento
        ultimo.prox.ant = ultimo; // ponteiro anterior da nova celula aponta para antigo ultimo
        ultimo = ultimo.prox; // ultimo vira nova celula

    }
    public int removerInicio() throws Exception{
        if(primeiro==ultimo)throw new Exception("ERRO!"); // confere se lista esta vazia
        CelulaDupla tmp = primeiro; // tmp vira no cabeca
        primeiro = primeiro.prox; // primeiro vira segundo, ou seja, primeiro com elemento
        int elemento = primeiro.elemento; // elemento vira o primeiro 
        tmp.prox = primeiro.ant= null; // tmp == null
        tmp = null; // tmp = null  tira o no cabeca 
        return elemento;
    }
    public int removerFim() throws Exception{
        if(primeiro==ultimo)throw new Exception("ERRO!"); // confere se lista esta vazia
        int elemento = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return elemento;
    }
    public void inserir(int x, int pos) throws Exception{
        int tamanho = getTamanho(); // Pega tamanho da lista
        if(pos<0 || pos>tamanho)throw new Exception("ERRO"); // Posicao invalida da erro
        else if(pos==0)inserirInicio(x); // caso pos = 0 inserir no inicio
        else if(pos==tamanho)inserirFim(x); // caso pos ==tamanho inserir no fim
        else{
            CelulaDupla i = primeiro; // cria celula i , como no cabeca 
            for(int j = 0 ; j<pos; j++,i = i.prox); // acha celula da posicao anterior
            CelulaDupla tmp = new CelulaDupla(x); // cria noca celula com x 
            tmp.ant = i; // ponterior anteriro de tmp aponta para celula i - q eh uma antes da posicao
            tmp.prox = i.prox; // atribue o ponteiro da nova celula para o ponteiro da proxima de i
            tmp.ant.prox = tmp.prox.ant = tmp; // ponterio anterior do proximo e ponteiro posterior do anteriro aponta pra tmp
            tmp=i=null; // free 

        }
    }
    public int remover (int pos ) throws Exception{
        int tamanho = getTamanho();//Pega tamanho
        int elemento; // declara elemento 
        if(ultimo == primeiro || pos<0 || pos >=tamanho )throw new Exception("ERRO"); // Posicao invalida da erro e lista vazia
        else if(pos==0)elemento = removerFim(); // caso pos = 0 removerFinal
        else if (pos==tamanho-1)elemento = removerInicio(); //caso pos == tamanho-1 removerInicio
        else{
            CelulaDupla i = primeiro; // celula i = no cabeca
            for(int j = 0 ; j<pos ; j++,i = i.prox); // acha celula anterior da posicao
            i.ant.prox = i.prox; // ponteiro prxoimo da celula anterior aponta para 1 celula depois da removida
            i.prox.ant = i.ant; // ponteiro anterior da celula posterior aponta para 1 celula antes da removida
            elemento = i.elemento; // elemento = i.elemento
            i.prox = i.ant = null; // quebra os ponteiros da removida 
            i = null; // free i

        }
        return elemento; // retorna elemnto
    }
    public void mostrar(){
        System.out.print("[");
        for(CelulaDupla i = primeiro.prox ; i!=null ; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
    public int getTamanho(){
        int resp = 0;
        for(CelulaDupla i = primeiro.prox ; i!=null ; i = i.prox){
            resp++;
        }
        return resp;

    }
    public ListaDupla inverteOrdem() throws Exception{
        ListaDupla listaDuplaInvertida = new ListaDupla();
        for(CelulaDupla i = primeiro.prox; i!=null;i = i.prox){
            listaDuplaInvertida.inserirInicio(i.elemento);
        }
        return listaDuplaInvertida;
    }
    
}

class CelulaMatriz{
    int elemento;
    CelulaMatriz inf,sup,esq,dir;

    CelulaMatriz(){
        this(0);
    }
    CelulaMatriz(int elemento){
        this(elemento,null,null,null,null);
    }
    CelulaMatriz(int elemento,CelulaMatriz inf,CelulaMatriz sup,CelulaMatriz esq,CelulaMatriz dir){
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}
class Matriz{
   
    private CelulaMatriz inicio;
    private int linhas, colunas;
     
    /*Construtor vazio*/
    public Matriz()throws Exception{
       this(2,2);
    }
    
    public Matriz(int linha, int coluna)throws Exception{
       if(linha <= 0 || coluna <= 0 )throw new Exception ("ERRO!"); 
          
       this.linhas = linha;
       this.colunas = coluna;
       
       this.inicio = new CelulaMatriz();
       CelulaMatriz aux = this.inicio;
       //Set das colunas 
       for(int j = 0; j < colunas; j++){
          CelulaMatriz nova = new CelulaMatriz();
          aux.dir = nova;
          nova.esq = aux;
          aux = aux.dir;
       }
       CelulaMatriz superior = this.inicio;
       
       //Caminhar pelas linhas da matriz 
       for(int i = 0; i < linhas-1; i++){
          CelulaMatriz lateral = new CelulaMatriz();//Criacao primeiro elemento da linha
          lateral.sup = superior;
          superior.inf = lateral;
          aux = lateral;
          
          //Caminhar pelas colunas da matriz 
          for(int j = 0; j < colunas-1; j++){
             superior = superior.dir;//mover para proximo elemento da coluna
             CelulaMatriz nova = new CelulaMatriz();   
             nova.esq = lateral;
             lateral.dir = nova;
             superior.inf = nova;
             nova.sup = superior;
             lateral = nova;//mover para proximo elemento da linha
          }
          superior = aux; 
       }
       superior = null;
       aux = null; 
    }
    
    /*Funcao para setar elementos conforme leitura de entrada*/
    public void setElementosLeitura(){
       CelulaMatriz l = this.inicio;
       for(int i = 0; i < linhas; i++){
          CelulaMatriz c = l;
          for(int j = 0; j < colunas; j++){
             c.elemento = MyIO.readInt();
             c = c.dir;
          }  
          l = l.inf;   
       }
    }
    
    /*Funcao para setar elemento conforme posicao e dado recebido*/
    public void setElemento(int linha, int coluna, int valor)throws Exception{
        if(this.colunas < coluna || this.linhas < linha || 0 > coluna || 0 > linha )
          throw new Exception ("ERRO!");
       CelulaMatriz l = this.inicio;
       for(int i = 0; i < coluna; i++, l = l.dir );
       for(int i = 0; i < linha; i++, l = l.inf );
       l.elemento = valor;
    }
 
    /*Funcao para obter elemento conforme posicao */
    public int getElemento(int linha, int coluna)throws Exception{
        if(this.colunas < coluna || this.linhas < linha || 0 > coluna || 0 > linha )
          throw new Exception ("ERRO!");
       CelulaMatriz l = this.inicio;
       for(int i = 0; i < coluna; i++, l = l.dir );
       for(int i = 0; i < linha; i++, l = l.inf );
       return l.elemento;
    }
 
    public void mostrar(){
       CelulaMatriz l = this.inicio;
       for(int i = 0; i < linhas; i++){
          CelulaMatriz c = l;
          for(int j = 0; j < colunas; j++){
             System.out.print(c.elemento+" ");
             c = c.dir;
          }  
          System.out.println("");
          l = l.inf;   
       }
    }
    
    /*Metodo para mostrar diagonal principal de uma matriz */   
    public void showDiagonalPrincipal()throws Exception{
       if(this.colunas != this.linhas)
          throw new Exception ("ERRO!");
          
       CelulaMatriz i = inicio;
       do{
          MyIO.print(i.elemento+" ");
          i = i.dir;
          if(i != null)
             i = i.inf;
       }while(i != null);
       MyIO.println("");
    }
    
    /*Metodo para mostrar diagonal secundaria de uma matriz */
    public void showDiagonalSecundaria()throws Exception{
       if(this.colunas != this.linhas)
          throw new Exception ("ERRO!");
          
       CelulaMatriz i = inicio;
       for(int x = 1; x < colunas; x++){//encontrar ultimo elemento da primeira linha
          i = i.dir;
       }
       do{
          MyIO.print(i.elemento+" ");
          i = i.esq;
          if(i != null)
             i = i.inf;
       }while(i != null);
       MyIO.println("");
    }
    
    public Matriz soma(Matriz b)throws Exception{
       return soma(this, b);
    }
 
    /*Metodo para somar duas matrizes */
    public Matriz soma(Matriz a, Matriz b)throws Exception{
       Matriz s = null;
       
       if(a.linhas == b.linhas && a.colunas == b.colunas){
          s = new Matriz(a.linhas, a.colunas);
          CelulaMatriz i1 = a.inicio;
          CelulaMatriz i2 = b.inicio;
          CelulaMatriz is = s.inicio;
          for(int x = 0; x < linhas; x++){
             CelulaMatriz j1 = i1;
             CelulaMatriz j2 = i2;
             CelulaMatriz js = is;
             
             for(int y = 0; y < colunas; y++){
                js.elemento = j1.elemento + j2.elemento;
             
                j1 = j1.dir;
                j2 = j2.dir;
                js = js.dir;
             }
             i1 = i1.inf;
             i2 = i2.inf;
             is = is.inf;
          }
       
       }   
       return s;
    }
    
    public Matriz multiplicacao(Matriz b)throws Exception{
       return multiplicacao(this, b);
    }
    
    /*Metodo para multiplicar matriz*/
    public Matriz multiplicacao(Matriz a, Matriz b)throws Exception{
       Matriz m = null;
       int num = 0;
       
       if(b.linhas == a.colunas){
          m = new Matriz(a.linhas, b.colunas);//Criacao matriz com numero de linhas de a e colunas de b
          CelulaMatriz i1 = a.inicio;
          CelulaMatriz i2 = b.inicio;
          CelulaMatriz im = m.inicio;
          
          for(int i = 0; i < a.linhas; i++){
             CelulaMatriz j1 = i1;
             CelulaMatriz j2 = i2;
             CelulaMatriz jm = im;
             
             for(int j = 0; j < b.colunas; j++){
                CelulaMatriz k1 = j1;
                CelulaMatriz k2 = j2;
                for(int k = 0; k < a.colunas; k++){
                   num += k2.elemento * k1.elemento;//Somar elemento com multiplicacao de elementos
                   k1 = k1.dir;//mover coluna de a
                   k2 = k2.inf;//mover linha de b
                }
                jm.elemento = num;
                num = 0;
                j2 = j2.dir;//Movimentacao colunas de b
                jm = jm.dir;//Movimentacao colunas de resposta
             }
             i1 = i1.inf;//Movimentacao linhas de a
             im = im.inf;//movimentacao linhas de resposta
          }
       
       }   
       return m;
    }
}
class EstruturasFlexiveis {
    public static void main(String[] args) throws Exception {
        System.out.println("---PILHA FLEXIVEL---");
        Pilha pilha = new Pilha();
        pilha.inserir(120);
        pilha.inserir(20);
        pilha.inserir(-30);
        pilha.inserir(40);
        pilha.inserir(150);
        pilha.inserir(60);
        pilha.mostrar();
        System.out.println("Elemento removido: " + pilha.remover());
        System.out.println("Elemento removido: " + pilha.remover());
        System.out.println("Elemento removido: " + pilha.remover());
        System.out.println("Apos 3 remocoes");
        pilha.mostrar();
        System.out.println("A soma da pilha = " + pilha.somaElementos());
        System.out.println("O maior elemento da pilha e: " + pilha.getMaior());
        System.out.println("---FILA FLEXIVEL---");
        Fila fila = new Fila();
        fila.inserir(20);
        fila.inserir(40);
        fila.inserir(160);
        fila.inserir(80);
        fila.inserir(100);
        fila.inserir(120);
        fila.mostrar();
        System.out.println(fila.removerSemPreserva());
        fila.mostrar();
        System.out.println("O maior elemento e =  " + fila.getMaior());
        System.out.println("O terceiro elemento e =  " + fila.getTerceiroElemento());
        System.out.println("A soma dos elementos e =  " + fila.getSoma());
        fila = fila.inverteOrdem();
        fila.mostrar();
        fila.metodoDoidao();
        fila.mostrar();
        System.out.println("---LISTA SIMPLES---");
        listaSimples listaSimples = new listaSimples();
        listaSimples.inserirInicio(5);
        listaSimples.inserirInicio(6);
        listaSimples.inserir(20,1);
        listaSimples.mostrar();
        System.out.println("---LISTA DUPLA---");
        ListaDupla listaDupla = new ListaDupla();
        listaDupla.inserirInicio(10);
        listaDupla.inserirInicio(20);
        listaDupla.inserirInicio(30);
        listaDupla.inserirInicio(40);
        listaDupla.mostrar();
        listaDupla = listaDupla.inverteOrdem();
        listaDupla.mostrar();
        System.out.println("---MATRIZ---");
        Matriz matriz = new Matriz(2,3);
        matriz.setElementosLeitura();
        matriz.mostrar();


       

    }

}
