public class OrdenacaoParcial {

    public static void swap(int [] array,int min,int i){
        int temp = array[min];
            array[min] = array[i];
            array[i]=temp;
    }

    public static int[] selecaoParcial(int array[]){
        int k = 5;
        for(int i = 0; i<k ; i++){
            int min = i;
            for(int j = i+1 ; j<array.length-1;j++){
                if(array[j]<array[min])min=j;
            }
            int temp = array[min];
            array[min] = array[i];
            array[i]=temp;
        }
        return array;
        
    }
    public static int[] insercaoParcial(int array[]){
        int n = array.length;
        int k = 8;
        for (int i = 1; i < n; i++) {
            int temp = array[i];
            int j = (i<k)?i-1:k-1;
            while((j>=0) && (array[j]>temp)){
                array[j+1]=array[j];
                j--;

            }
            array[j+1]=temp;
            
        }
        return array;
    }
    public static int[] quickParcial(int array[],int esq,int dir){
        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(array,i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  quickParcial(array,esq, j);
        if (i < dir)  quickParcial(array,i, dir);
        return array;
    }
    public static void main(String[] args) {
        int arrayInteiros[] = {10,5,4,15,256,85,14,0,12,36,45,75,18,19};
        //arrayInteiros = selecaoParcial(arrayInteiros);
        arrayInteiros = insercaoParcial(arrayInteiros);
        //arrayInteiros = quickParcial(arrayInteiros, 0, arrayInteiros.length-1);

        for(int i = 0 ; i<arrayInteiros.length;i++){
            System.out.println("["+i+"]"+arrayInteiros[i]);
        }
    }
}
