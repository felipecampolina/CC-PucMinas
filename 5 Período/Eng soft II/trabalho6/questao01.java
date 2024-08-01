import java.util.HashSet;
import java.util.Set;

// Produto final que será construído.
class Pedido {
    private Set<String> dentroDaCaixa;
    private Set<String> foraDaCaixa;

    public Pedido() {
        this.dentroDaCaixa = new HashSet<>();
        this.foraDaCaixa = new HashSet<>();
    }

    public void addItemDentroDaCaixa(String item) {
        dentroDaCaixa.add(item);
    }

    public void addItemForaDaCaixa(String item) {
        foraDaCaixa.add(item);
    }

    @Override
    public String toString() {
        return "Pedido{" +
               "dentroDaCaixa=" + dentroDaCaixa +
               ", foraDaCaixa=" + foraDaCaixa +
               '}';
    }
}

// Interface Builder.
interface PedidoBuilder {
    void buildHamburguer(String tipo);
    void buildBebida(String tipo);
    void buildBrinquedo(String tipo);
    Pedido getPedido();
}

// Implementação concreta do Builder.
class PedidoConcretoBuilder implements PedidoBuilder {
    private Pedido pedido;

    public PedidoConcretoBuilder() {
        this.pedido = new Pedido();
    }

    @Override
    public void buildHamburguer(String tipo) {
        pedido.addItemDentroDaCaixa(tipo + " hamburguer");
    }

    @Override
    public void buildBebida(String tipo) {
        pedido.addItemForaDaCaixa(tipo + " bebida");
    }

    @Override
    public void buildBrinquedo(String tipo) {
        pedido.addItemForaDaCaixa(tipo + " brinquedo");
    }

    @Override
    public Pedido getPedido() {
        return pedido;
    }
}

// Classe que monta o pedido, podendo ser considerado como o 'Director'.
class MontadorPedido {
    public Pedido montarPedido(PedidoBuilder builder) {
        builder.buildHamburguer("Cheese");
        builder.buildBebida("Coca");
        builder.buildBrinquedo("Carro");
        return builder.getPedido();
    }
}

// Classe principal para executar o pedido.
public class questao01 {
    public static void main(String[] args) {
        PedidoBuilder builder = new PedidoConcretoBuilder();
        MontadorPedido montador = new MontadorPedido();
        Pedido pedido = montador.montarPedido(builder);
        System.out.println(pedido);
    }
}
