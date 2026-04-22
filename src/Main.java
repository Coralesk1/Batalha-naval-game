
import Regras.regras;

import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);

    while (true) {

        System.out.println("\n--- Welcome to Battle Chip! ---");
        System.out.println("1. Jogar 1 vs 1");
        System.out.println("2. Jogar 1 vs PC");
        System.out.println("3. Regras do Jogo");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                Jogo.iniciarJogo(scanner, false);
                break;
            case 2:
                Jogo.iniciarJogo(scanner, true);
                break;
            case 3:
                regras.mostrarRegras(scanner);
                break;
            case 4:
                System.out.println("Obrigado por jogar <3 !");
                scanner.close();
                return;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    }

}
