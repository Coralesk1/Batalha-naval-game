
import Regras.regras;

import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);

    while (true) {

        System.out.println("\n--- Welcome to Battle Chip! ---");
        System.out.println("1. Jogar");
        System.out.println("2. Regras do Jogo");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                Jogo.iniciarJogo(scanner);
                break;
            case 2:
                regras.mostrarRegras(scanner);
                break;
            case 3:
                System.out.println("Obrigado por jogar!");
                scanner.close();
                return;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    }
}
