package Regras;

import Utils.UtilsConsole;

import java.util.Scanner;

public class regras {

    public static void mostrarRegras(Scanner scanner) {

        UtilsConsole.limpaTela();
        System.out.println("\n--- Regras do Jogo Battle Chip ---");
        System.out.println("1. O jogo é jogado em um tabuleiro 10x10.");
        System.out.println("2. Cada jogador posiciona seus chips no tabuleiro.");
        System.out.println("3. O objetivo é acertar as coordenadas onde os chips do oponente estão escondidos.");
        System.out.println("4. Ganha quem destruir todos os chips do adversário primeiro.");
        System.out.println("----------------------------------");

        System.out.println("Precisone enter para voltar para o menu ...");
        scanner.nextLine(); // Limpa o buffer caso venha de um nextInt()
        scanner.nextLine();

        UtilsConsole.limpaTela();

    }


}
