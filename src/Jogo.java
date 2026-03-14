import Utils.UtilsConsole;

import java.util.Scanner;

public class Jogo {

    private static Jogador jogador1;
    private static Jogador jogador2;

    public static void iniciarJogo(Scanner scanner, UtilsConsole utilsConsole) {

        utilsConsole.limpaTela();
        System.out.println("Iniciando o Jogo de Batalha Naval!");

        // Cria os jogadores e seus respectivos tabuleiros
        jogador1 = new Jogador(new Tabuleiro());
        jogador2 = new Jogador(new Tabuleiro());

        System.out.println("----PREPARAÇÃO DO JOGADOR 1----");
        preparaJogador(jogador1, scanner);

        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        preparaJogador(jogador2, scanner);


        /*for(int i = 0; i < matrizBi.length; i++){
            for(int j = 0; j < matrizBi[0].length; j++){
                System.out.print(matrizBi[i][j] + " ");
                matrizBi[cordenadaX][cordenadaY] = 2;
            }
            System.out.println();
        }

        System.out.println("===================");

        for(int i = 0; i < matrizBi.length; i++){
            for(int j = 0; j < matrizBi[0].length; j++){
                System.out.print(matrizBi[i][j] + " ");
                matrizBi[cordenadaX][cordenadaY] = 2;
            }
            System.out.println();
        }*/


        scanner.close();

    }

    public static void preparaJogador( Jogador jogador, Scanner scanner) {
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println("Posicione seus barcos no tabuleiro.");

        tabuleiro.mostraMatrizPrincipal();

        System.out.println("Posição da linha [0-9] ");
        int linha = scanner.nextInt();

        System.out.println("Posição da coluna [0-9] ");
        int coluna = scanner.nextInt();

        System.out.println("Orientação (H para horizontal) ou (V para vertical)");
        String orientacao = scanner.nextLine().toUpperCase();


        boolean posicionado = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao);


        /*for(int i = 0; i <= 5; i++){

        }*/

    }
}
