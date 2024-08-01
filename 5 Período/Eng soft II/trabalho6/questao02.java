//Questão 2 A
import java.util.Random;

// Interface do sistema que espera medidas em Celsius.
interface MedidorCelsiusIF {
    double medirTemperatura();
}

// Classe fornecida que mede temperatura em Fahrenheit.
 class MedidorFahrenheit {
    public double getTemperaturaFahrenheit() {
        return new Random().nextDouble() * 100; // Simulando o termômetro.
    }
}

// Adapter Object para converter Fahrenheit para Celsius.
class AdaptadorFahrenheitParaCelsiusObjectAdapter implements MedidorCelsiusIF {
    private MedidorFahrenheit medidorFahrenheit;

    public AdaptadorFahrenheitParaCelsiusObjectAdapter(MedidorFahrenheit medidor) {
        this.medidorFahrenheit = medidor;
    }

    @Override
    public double medirTemperatura() {
        double fahrenheit = medidorFahrenheit.getTemperaturaFahrenheit();
        return (fahrenheit - 32) * 5.0 / 9.0; // Conversão de Fahrenheit para Celsius.
    }
}

//Questão 2 B 

// Adapter Class para converter Fahrenheit para Celsius.
class AdaptadorFahrenheitParaCelsiusClassAdapter extends MedidorFahrenheit implements MedidorCelsiusIF {
    @Override
    public double medirTemperatura() {
        double fahrenheit = getTemperaturaFahrenheit();
        return (fahrenheit - 32) * 5.0 / 9.0; // Conversão de Fahrenheit para Celsius.
    }
}
public class questao02 {
    public static void main(String[] args) {
        // Utilizando o Object Adapter
        MedidorFahrenheit medidorFahrenheit = new MedidorFahrenheit();
        MedidorCelsiusIF adapterObject = new AdaptadorFahrenheitParaCelsiusObjectAdapter(medidorFahrenheit);
        System.out.println("Temperatura em Celsius (Object Adapter): " + adapterObject.medirTemperatura());

        // Utilizando o Class Adapter
        MedidorCelsiusIF adapterClass = new AdaptadorFahrenheitParaCelsiusClassAdapter();
        System.out.println("Temperatura em Celsius (Class Adapter): " + adapterClass.medirTemperatura());
    }
}