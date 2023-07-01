import java.io.IOException;
import java.util.*;


/**
 * IMPORTANT:
 * O nome da classe deve ser "Main" para que a sua solução execute
 * Class name must be "Main" for your solution to execute
 * El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class Main {

    public static void main(String[] args) throws IOException {
       
        Scanner scanner = new Scanner(System.in);
        String frase = "";
        while (scanner.hasNext()) {
            frase = scanner.nextLine();
            int teste = 0;
            for (int i = 0; i < frase.length(); i++) {
                char x = 'a';
                x = Character.toLowerCase(frase.charAt(i));
                if (x == ' ')
                    teste--;
                if (teste % 2 == 0) {

                    System.out.print(Character.toUpperCase(x));
                } else {
                    System.out.print(x);
                }
                teste++;

            }

        }

    }

}