import Navios.*;
import Utils.UtilsConsole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<String> prepjogador1 = preparaJogador(jogador1, scanner, utilsConsole);

        if (!prepjogador1.isEmpty()){
            System.out.println(prepjogador1);
        }


        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        preparaJogador(jogador2, scanner, utilsConsole);

        scanner.close();

    }

    public static List<String> preparaJogador( Jogador jogador, Scanner scanner, UtilsConsole utilsConsole) {
        List<String> erros = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            System.out.println("Posicione seus barcos no tabuleiro.");

            jogador.getTabuleiro().mostraMatrizPrincipal();
            String navioEscolhido = escolheNavio(scanner);

            //busca navio
            Navio navio = Navio.buscarNavioByNome(navioEscolhido);

            int linha;
            while(true){

                System.out.println("Posição da linha [0-9] : ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                    scanner.next();
                }
                linha = scanner.nextInt();

                String erroLinha = utilsConsole.validaEntrada(linha, erros);

                if (erroLinha != null) {
                    utilsConsole.limpaTela();
                    System.out.println("Linha " + erroLinha);
                }else{
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

                String erroColuna = utilsConsole.validaEntrada(linha, erros);

                if (erroColuna != null) {
                    utilsConsole.limpaTela();
                    System.out.println("Coluna " + erros);
                } else {
                    break;
                }
            }

            String orientacao;
            while (true){

                System.out.println("Orientação (H para horizontal) ou (V para vertical)");
                orientacao = scanner.nextLine().toUpperCase();

                break;
            }


            boolean posicionado = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio);

            if (posicionado) {
                break;
            }

        }
        return erros;
    }

    public static String escolheNavio(Scanner scanner){

        HashMap<Integer, String> listaNavios = new HashMap<>();
        String navioEscolhido;

        listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

        System.out.println("Lista de Navios disponiveis:");
        for(HashMap.Entry<Integer, String> navio : listaNavios.entrySet()){
            System.out.println(navio.getKey() +  " - " + navio.getValue());
        }

        while (true){
            System.out.println("Escolha seu navio para posiciona-lo: ");
            int escolhaNavio = scanner.nextInt();

            navioEscolhido = listaNavios.get(escolhaNavio);

            if (navioEscolhido == null){
                System.out.println("Navio inválido!");
            }else {
                break;
            }
        }
        return navioEscolhido;

    }



}
