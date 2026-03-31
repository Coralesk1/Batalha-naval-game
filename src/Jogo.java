import Navios.*;
import Utils.UtilsConsole;

import java.util.HashMap;
import java.util.Random;
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

        //cria uma matriz vazia e mostra para ele , ele vai atirar e eu comparo se a cordenada que ele colocou na matriz vazia se
        //tem algum barco posicionado na matrix real , se acertou ent mostra na matriz vazia a fumaça na posição


        System.out.println("----PREPARAÇÃO DO JOGADOR 1----");
        boolean preparaJogador1 = preparaJogador(jogador1, scanner, listaNavios);

        if (preparaJogador1){
            System.out.println("Jogador 1 posicionado !!");
            System.out.println("Seu tabuleiro:");
            jogador1.getTabuleiro().mostraMatrizPrincipal();

            System.out.println("Pressione para confirmar.");
            scanner.nextLine();
        }

        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        boolean preparaJogador2 = preparaJogador(jogador2, scanner, listaNavios);

        if (preparaJogador2){
            System.out.println("Jogador 2 posicionado !!");
            System.out.println("Seu tabuleiro:");
            jogador1.getTabuleiro().mostraMatrizPrincipal();

            System.out.println("Pressione para confirmar.");
            scanner.nextLine();
        }

        // começa a logica de batalha

        //tem que fazer um sorteio de quem vai começar
        Random random = new Random();
        int jogadorEscolhidoJogar = random.nextInt(2);

        if (jogadorEscolhidoJogar == 1){
            System.out.println("Jogador 1 começa a batalha !!!");
            System.out.println("Tabuleiro do jogador : ");
            jogador2.getTabuleiro().mostraMatrizPrincipal();



        }else {
            System.out.println("Jogador 2 começa a batalha !!!");
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

                while (true){

                    int linha;
                    while (true) {

                        System.out.println("Informe a posição da linha [0-9] : ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                            scanner.next();
                        }
                        linha = scanner.nextInt();

                        boolean erroLinha = UtilsConsole.validaLinha(linha);

                        if (!erroLinha) {
                            UtilsConsole.limpaTela();
                        } else {
                            break;
                        }
                    }

                    int coluna;
                    while (true) {

                        System.out.println("Informe a posição da coluna [0-9] : ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a coluna:");
                            scanner.next();
                        }
                        coluna = scanner.nextInt();

                        boolean erroColuna = UtilsConsole.validaColuna(linha);

                        if (!erroColuna) {
                            UtilsConsole.limpaTela();
                        } else {
                            break;
                        }
                    }
                    scanner.nextLine();
                    String orientacao;
                    while (true) {
                        System.out.println("Informe a orientação (H para horizontal) ou (V para vertical) :");
                        orientacao = scanner.nextLine().toUpperCase();

                        boolean erroPosicao = UtilsConsole.validaPosicao(orientacao);

                        if (!erroPosicao){
                            UtilsConsole.limpaTela();
                        }else{
                            break;
                        }
                    }

                    boolean posicionaBarco = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio, scanner);

                    if (!posicionaBarco) {
                        posicionado = false;
                        jogador.getTabuleiro().mostraMatrizPrincipal();
                        System.out.println("Tente posicionar o barco " + navioEscolhido + " novamente.");
                    }else{
                        break;
                    }
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
