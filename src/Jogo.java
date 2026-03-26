import Navios.*;
import Utils.UtilsConsole;

import java.util.HashMap;
import java.util.Scanner;

public class Jogo {

    private static Jogador jogador1;
    private static Jogador jogador2;


    public static void iniciarJogo(Scanner scanner) {

        UtilsConsole.limpaTela();
        System.out.println("Iniciando o Jogo de Batalha Naval!");

        // Cria os jogadores e seus respectivos tabuleiros
        jogador1 = new Jogador(new Tabuleiro());
        jogador2 = new Jogador(new Tabuleiro());
        HashMap<Integer, String> listaNavios = new HashMap<>();

        listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

        System.out.println("----PREPARAÇÃO DO JOGADOR 1----");
        boolean preparaJogador1 = preparaJogador(jogador1, scanner, listaNavios);

        if (preparaJogador1){
            System.out.println("jogador 1 posicionado !!");
        }

        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        boolean preparaJogador2 = preparaJogador(jogador2, scanner, listaNavios);

        if (preparaJogador2){
            System.out.println("jogador 2 posicionado !!");
        }

        scanner.close();

    }

    public static boolean preparaJogador( Jogador jogador, Scanner scanner, HashMap<Integer, String> listaNavios) {
        boolean posicionado = true;

        while (true) {

            for (int i = 0; i < 5; i++) {

                System.out.println("Posicione seus barcos no tabuleiro.");

                jogador.getTabuleiro().mostraMatrizPrincipal();
                String navioEscolhido = escolherNavio(scanner, listaNavios);

                //busca navio
                Navio navio = Navio.buscarNavioByNome(navioEscolhido);

                int linha;
                while (true) {

                    System.out.println("Posição da linha [0-9] : ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                        scanner.next();
                    }
                    linha = scanner.nextInt();

                    boolean erroLinha = UtilsConsole.validaLinha(linha);

                    if (!erroLinha) {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        UtilsConsole.limpaTela();
                    } else {
                        break;
                    }
                }

                int coluna;
                while (true) {

                    System.out.println("Posição da coluna [0-9] : ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida! Digite um número entre 0 e 9 para a coluna:");
                        scanner.next();
                    }
                    coluna = scanner.nextInt();

                    boolean erroColuna = UtilsConsole.validaColuna(linha);

                    if (!erroColuna) {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        UtilsConsole.limpaTela();
                    } else {
                        break;
                    }
                }
                scanner.nextLine();
                String orientacao;
                while (true) {
                    System.out.println("Orientação (H para horizontal) ou (V para vertical)");
                    orientacao = scanner.nextLine().toUpperCase();

                    if (orientacao.equals("H") || orientacao.equals("V")){
                        break;
                    }else{
                        System.out.println("Orientação inválida.");
                    }
                }

                boolean posicionaBarco = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio, scanner);

                if (!posicionaBarco) {
                    posicionado = false;
                    break;
                }
            }
            if (posicionado){
                break;
            }
        }

        return true;
    }

    public static String escolherNavio(Scanner scanner, HashMap<Integer, String> listaNavios){

        String navioEscolhido;

        System.out.println("Lista de Navios disponiveis:");
        for(HashMap.Entry<Integer, String> navio : listaNavios.entrySet()){
            System.out.println(navio.getKey() +  " - " + navio.getValue());
        }

        int escolhaNavio;
        while (true){
            System.out.println("Escolha seu navio para posiciona-lo: ");
             escolhaNavio = scanner.nextInt();

            navioEscolhido = listaNavios.get(escolhaNavio);

            if (navioEscolhido == null){
                System.out.println("Navio inválido!");
            }else {
                break;
            }
        }
        listaNavios.remove(escolhaNavio);
        return navioEscolhido;

    }



}
