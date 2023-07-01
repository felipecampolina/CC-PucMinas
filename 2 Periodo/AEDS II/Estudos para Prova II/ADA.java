class ADA{


    public static int func(int x ){
        if(x>0)return func(x-1);
        else return 1;
    }



    public static void main(String[] args) {
        System.out.println(func(4));
}
}