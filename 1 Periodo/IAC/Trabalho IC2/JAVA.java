import java.util.Scanner;

public class Exercicio3 {

    public static void main(String[] args) {
       System.out.println("Escreva o valor do raio");
       Scanner scan = new Scanner(System.in);

       double raio = scan.nextFloat();
       double area = Math.PI * raio * raio;

       System.out.println("O valor da area é " + area);
    }
}