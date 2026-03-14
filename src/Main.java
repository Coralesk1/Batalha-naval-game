
import Regras.regras;
import Utils.UtilsConsole;

import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);
    UtilsConsole utilsConsole = new UtilsConsole();

    while (true) {

        System.out.println("\n--- Welcome to Battle Chip! ---");
        System.out.println("1. Jogar");
        System.out.println("2. Regras do Jogo");
        System.out.println("3. Sair");
        System.out.println("4. Teste matriz bidimensional");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                Jogo.iniciarJogo(scanner, utilsConsole); // Passa o scanner para o método
                break;
            case 2:
                regras.mostrarRegras();
                break;
            case 3:
                System.out.println("Obrigado por jogar!");
                scanner.close();
                return;
            case 4:
                Tabuleiro tabuleiro = new Tabuleiro();
                tabuleiro.mostraMatrizPrincipal();

            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    }
}
