class listaLinear{
    int array[];
    int n  ;

    listaLinear(){
        this(6); // contrutor com tamnho fixo 
    }
    listaLinear(int tamanho ){
        array = new int [tamanho]; // contrutor com tamanho passado por paramentro 
        int n = 0;
    }

    public void inserirInicio(int x ) throws Exception{
        if(n>= array.length) throw new Exception("ERRO!"); // confere se o tamanho foi atingido ou não 

        for(int i = n ; i>0 ; i--){
            array[i] = array[i-1]; // leva elementos para o final 
        }
        array[0] = x; // adiciona elemento no inicio 
        n++;// n++ -- controle do  tamanho 

    }
    public void inserirFim(int x) throws Exception{
        if(n>= array.length)throw new Exception("ERRO!"); // confere se o tamanho foi atingido ou não
        array[n] = x;
        n++;
    }
    public void inserir(int x , int pos) throws Exception{
        if(n>=array.length || pos < 0 || pos>n)throw new Exception("ERRO!"); // confere se o tamanho foi atingido ou não + pos < 0 + pos > n
        for(int i = n ; i> pos ; i--){
            array[i] = array[i-1];
        }
        array[pos] = x;
        n++;
    }
    public int removerInicio() throws Exception{
        if(n==0)throw new Exception("ERRO!"); // confere se lista esta vazia
        int resp = array[0]; // elemnto removido 
        n--; // diminue tamanho
        for (int i = 0; i <n; i++) {
            array[i] = array[i+1]; // movimenta array -- array[0] vira array[1] e assim continua
        }
        return resp;
    }
    public int removerFim() throws Exception{
        if(n==0)throw new Exception("ERRO!"); // confere se lista esta vazia
        return array[--n]; // decrementa n e depois retorna array 
    }
    public int remover(int pos) throws Exception{
        if(n==0 || pos<0 || pos>=n)throw new Exception("ERRO!"); // confere se lista esta vazia + pos < 0 + pos >=n
        int resp = array[pos]; // atribue resp a array na posicao x
        n--; // decrementa tamanho 
        for (int i = pos; i < n; i++) {
            array[i] = array[i+1]; // movimenta array 
            
        }
        return resp; // retorna resp
    }



    public void mostrar(){
        System.out.print("[");
        for(int i = 0; i<n; i++){
            System.out.print(array[i]+" ");
        }
        System.out.print("]");
    }

    
    
    

}
class PilhaLinear{
    int array[];
    int n;

    PilhaLinear(){
        this(5);
    }
    PilhaLinear(int tamanho){
        array = new int[tamanho];
        int n = 0;
    }
    public void inserir(int x ) throws Exception{ // MESMA COISA QUE O INSERIR FINAL DA LISTA
        if(n>=array.length)throw new Exception("ERRO!"); // confere se pilha ja esta cheia
        array[n] = x;
        n++;
    }
    public int remover () throws Exception{ // MESMA COISA QUE O REMOVER INICIO DA LISTA
        if(n==0)throw new Exception("ERRO!"); // confere se pilha ja esta vazia
        int resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i+1];
        }
        return resp;
    }
    public void mostrar(){
        System.out.print("[");
        for(int i = 0; i<n; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("]");
    }
}
class FilaLinear{
    int array[];
    int primeiro,ultimo;

    FilaLinear(){
        this(5);
    }
    FilaLinear(int tamanho){
        array = new int[tamanho+1]; // tomar cuidado com o array na fila
        primeiro = ultimo = 0 ;
    }
    public void inserir(int x ) throws Exception{
        if((ultimo + 1) % array.length == primeiro)throw new Exception("ERRO!"); // confere se fila atingiu o tamanho
        array[ultimo]=x;
        ultimo = (ultimo + 1) % array.length;
    }
    public int remover()throws Exception{
        if(ultimo == primeiro)throw new Exception("ERRO!"); // confere se fila  esta vazia
        int resp = array[primeiro];
        primeiro = (primeiro+1) % array.length;
        return resp;
    }
    public void mostrar(){ // diferente de todos os outros mostra
        int i = primeiro; // i = primeiro 
        System.out.print("[");
        while(i!=ultimo){
            System.out.print(array[i]+" ");
            i = (i+1)%array.length; // adiciona 1 a a cada interacao do while i vai de 0 até tamanho
        }
        System.out.println("]");
    }
    public boolean isVazio(){
    return(primeiro==ultimo);
    }
    public void mostrarRec(){
        System.out.print("[");
        mostrarRec(primeiro);
        System.out.print("]");
    }
    public void mostrarRec(int i){
        if(i!=ultimo){
            System.out.print(array[i]+" ");
            mostrarRec((i+1) % array.length);
        }
    }
    public int retornaPos(int pos) throws Exception{
        if(pos>primeiro)throw new Exception("ERRO!"); // confere se posicao > primeiro
        int resp = array[pos+primeiro];
        return resp;
    }
}

class EstruturasLineares{
public static void main(String[] args) throws Exception {
    System.out.println("---LISTA---");
    listaLinear listaLinear01 = new listaLinear(10);
    listaLinear01.inserirInicio(10);
    listaLinear01.inserirInicio(20);
    listaLinear01.inserirInicio(50);
    listaLinear01.inserirInicio(60);
    listaLinear01.inserirFim(100);
    listaLinear01.inserirFim(200);
    listaLinear01.inserirFim(500);
    System.out.println(listaLinear01.removerFim());
    System.out.println(listaLinear01.remover(3));
    listaLinear01.mostrar();
    System.out.println();
    System.out.println("---PILHA---");
    PilhaLinear pilhaLinear01 = new PilhaLinear(10);
    pilhaLinear01.inserir(10);
    pilhaLinear01.inserir(20);
    pilhaLinear01.inserir(30);
    pilhaLinear01.inserir(40);
    pilhaLinear01.inserir(50);
    pilhaLinear01.mostrar();
    pilhaLinear01.remover();
    pilhaLinear01.remover();
    pilhaLinear01.mostrar();
    System.out.println();
    System.out.println("---FILA CIRCULAR---");
    FilaLinear filaLinear01 = new FilaLinear(5);
    filaLinear01.inserir(10);
     filaLinear01.inserir(20);
     filaLinear01.inserir(30);
     filaLinear01.inserir(40);
     filaLinear01.inserir(50);
     filaLinear01.mostrar();
     filaLinear01.remover();
     filaLinear01.inserir(100);
     filaLinear01.remover();
     filaLinear01.remover();
     filaLinear01.mostrar();
     filaLinear01.mostrarRec();
     System.out.println("\nPos: 2 " + filaLinear01.retornaPos(2));



    
}
}