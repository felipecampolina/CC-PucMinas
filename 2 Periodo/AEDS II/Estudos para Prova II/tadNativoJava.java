import java.util.*;

class tadNativoJava {
    public static void main(String[] args) {
        // Listas Nativas do Java // 
        Vector<String> vector = new Vector<String>(); // array redimensionavel 
        ArrayList<String> arrayList = new ArrayList<String>(); // array redimensionavel 
        LinkedList<String> linkedList = new LinkedList<String>(); // listas duplamente encadeada
        // Listas Nativas do Java // 

        //Pilhas Natias do Java//
        Stack<String> pilha = new Stack<String>();
        //Pilhas Natias do Java//

        //Filas Nativas do Java // 
        Queue<String> fila = new LinkedList<String>();
        //Filas Nativas do Java // 



        vector.add("Atlético-MG");
        arrayList.add("Atlético-MG");
        linkedList.add("Atlético-MG");
        vector.add("Cruzeiro");
        arrayList.add("Cruzeiro");
        linkedList.add("Cruzeiro");
        vector.add("América");
        arrayList.add("América");
        linkedList.add("América");
        pilha.push("Atlético-MG");
      pilha.push("Cruzeiro");
      pilha.push("América");
      fila.add("Atlético-MG");
      fila.add("Cruzeiro");
      fila.add("América");


        System.out.println("Tamanhos:" + vector.size() + " -- " + arrayList.size() + " -- " + linkedList.size());
        System.out.println("get(index): " + (String) vector.get(0) + " -- " + (String) arrayList.get(1) + " -- "
                + (String) linkedList.get(2));

        for (Iterator i = vector.iterator(); i.hasNext();) {
            System.out.println("Vector: " + (String) i.next());
        }
        for (Iterator i = arrayList.iterator(); i.hasNext();) {
            System.out.println("ArrayList: " + (String) i.next());
        }
        for (Iterator i = linkedList.iterator(); i.hasNext();) {
            System.out.println("LinkedList: " + (String) i.next());
        }
        while(pilha.empty() == false){
            System.out.println("Retirando da pilha: " + pilha.pop());
          }
          while (!fila.isEmpty()){
            System.out.println("Retirando da fila: " + fila.remove());
         }
    }
}