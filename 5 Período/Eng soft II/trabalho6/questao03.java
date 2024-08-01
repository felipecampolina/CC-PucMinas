// Interface Command
interface Command {
    void execute();
}

// Command Concreto para Salvar
class SaveCommand implements Command {
    public void execute() {
        System.out.println("Saving a file...");
    }
}

// Command Concreto para Abrir
class OpenCommand implements Command {
    public void execute() {
        System.out.println("Opening a file...");
    }
}

// Command Concreto para Imprimir
class PrintCommand implements Command {
    public void execute() {
        System.out.println("Printing a document...");
    }
}

// Botão ou Atalho que usa Command
class Button {
    private Command command;

    public Button(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }
}

// Classe para testar os comandos
public class questao03 {
    public static void main(String[] args) {
        Command save = new SaveCommand();
        Command open = new OpenCommand();
        Command print = new PrintCommand();

        Button saveButton = new Button(save);
        Button openButton = new Button(open);
        Button printButton = new Button(print);

        // Simular pressionamento de botões
        saveButton.press();
        openButton.press();
        printButton.press();
    }
}
