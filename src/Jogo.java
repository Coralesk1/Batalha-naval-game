import Navios.*;
import Utils.UtilsConsole;
import netscape.javascript.JSObject;

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
        boolean jogador1Ok = preparaJogador(jogador1, scanner);
        if (jogador1Ok){

        }
        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        preparaJogador(jogador2, scanner);

        scanner.close();

    }

    public static List<String> preparaJogador( Jogador jogador, Scanner scanner) {
        List<String> erros = new ArrayList<>();

        for (int i = 0; i < 5; i++){

            System.out.println("Posicione seus barcos no tabuleiro.");

            jogador.getTabuleiro().mostraMatrizPrincipal();
            String navioEscolhido = escolheNavio(scanner);

            Navio navio = Navio.buscarNavioByNome(navioEscolhido);

            System.out.println("Posição da linha [0-9] : ");
            int linha;
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                scanner.next();
            }
            linha = scanner.nextInt();

            String erroLinha = UtilsConsole.validaLinha(linha, erros);

            if (!erroLinha.equals("")){
                System.out.println(erroLinha);
            }

            System.out.println("Posição da coluna [0-9] : ");
            int coluna;
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número entre 0 e 9 para a coluna:");
                scanner.next();
            }
            coluna = scanner.nextInt();

            UtilsConsole.validaColuna(coluna, erros);
            if(!erros.isEmpty()){
                return erros;
            }

            System.out.println("Orientação (H para horizontal) ou (V para vertical)");
            String orientacao = scanner.nextLine().toUpperCase();

            boolean posicionado = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio);

            if (posicionado){
                break;
            }

        }


        return false;
    }

    public static String escolheNavio(Scanner scanner){

        HashMap<Integer, String> listaNavios = new HashMap<>();

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

            String navioEscolhido = listaNavios.get(escolhaNavio);

            if (navioEscolhido == null){
                System.out.println("Navio inválido!");
            }else {
                break;
                return navioEscolhido;
            }
        }


    }



}
