// Max-Heap data structure in Java

import java.util.ArrayList;

class Heap {
  void organiza(ArrayList<Filme> arranjo, int i) {
    int size = arranjo.size();
    int menor = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < size && arranjo.get(l).getId() < arranjo.get(menor).getId())
      menor = l;
    if (r < size && arranjo.get(r).getId() < arranjo.get(menor).getId())
      menor = r;

    if (menor != i && arranjo.get(menor).getSegmento() == 0) {
      Filme temp = arranjo.get(menor);
      arranjo.set(menor, arranjo.get(i));
      arranjo.set(i, temp);

      organiza(arranjo, menor);
    }
  }

  void pegaEsquerda(ArrayList<Filme> arranjo, int i) {
   
    int l = 2 * i + 1;

    int idEsquerda = arranjo.get(l).getId();
    
    System.out.println(idEsquerda);
  }
  void pegaDireita(ArrayList<Filme> arranjo, int i) {
   
    int l = 2 * i + 2;

    int idDireita = arranjo.get(l).getId();
    
    System.out.println(idDireita);
  }

  void insert(ArrayList<Filme> arranjo, Filme newFilme) {
    int size = arranjo.size();
    if (size == 0) {
      arranjo.add(newFilme);
    } else {
      arranjo.add(newFilme);
      for (int i = size / 2 - 1; i >= 0; i--) {
          organiza(arranjo, i);
      }
    }
  }

  void delete(ArrayList<Filme> arranjo, int num)
  {
    int size = arranjo.size();
    int i;
    int b;
    for (i = 0; i < size; i++)
    {
      if (num == arranjo.get(i).getId())
        break;
    }

    Filme temp = arranjo.get(i);
    
    if(arranjo.get(size-1).getSegmento() == 0){
      arranjo.set(i, arranjo.get(size-1));
      arranjo.set(size-1, temp);
      arranjo.remove(size-1);
    }else{
      for(b = 0; b<size; b++){
        if(arranjo.get(b).getSegmento() == 0 &&
           arranjo.get(b).getId() != num){
            arranjo.set(i, arranjo.get(b));
          break;
        }
      }
      arranjo.set(b, temp);
      arranjo.remove(b);
    }
    

   /*  System.out.println("DELETADO = " );
    for(int b = 0; b<size-1; b++){
       System.out.println(arranjo.get(b).getId());
    }
 */

    for (int j = size / 2 - 1; j >= 0; j--)
    {
        organiza(arranjo, j);
    }
  }

  void printArray(ArrayList<Filme> array, int size) {
    for (Filme i : array) {
      System.out.print(i.getId() + " ");
    }
    System.out.println();
  }

  /* public static void main(String args[]) {

    ArrayList<Integer> array = new ArrayList<Integer>();
    int size = array.size();

    Heap h = new Heap();
    h.insert(array, 3);
    h.insert(array, 4);
    h.insert(array, 9);
    h.insert(array, 5);
    h.insert(array, 2);

    System.out.println("Max-Heap array: ");
    h.printArray(array, size);

    h.delete(array, 4);
    System.out.println("After deleting an element: ");
    h.printArray(array, size); 

    System.out.println(array.get(0));
    System.out.print(array.get(1)+ " ");
    System.out.print(array.get(2));
    System.out.println(array.get(3));


  } */
}
