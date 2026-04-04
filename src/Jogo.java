import Navios.*;
import Utils.UtilsConsole;

import java.util.HashMap;
import java.util.Scanner;

public class Jogo {

    private static Jogador jogador1;
    private static Jogador jogador2;
    private static Jogador jogador1MatrizTemplate;
    private static Jogador jogador2MatrizTemplate;


    public static void iniciarJogo(Scanner scanner, /*indicador jogador IA*/) {

        UtilsConsole.limpaTela();
        System.out.println("Iniciando o Jogo de Batalha Naval!");

        // Cria os jogadores e seus respectivos tabuleiros
        jogador1 = new Jogador(new Tabuleiro(), new Navio());
        jogador2 = new Jogador(new Tabuleiro(), new Navio());
        jogador1MatrizTemplate = new Jogador(new Tabuleiro());
        jogador2MatrizTemplate = new Jogador(new Tabuleiro());


        HashMap<Integer, String> listaNavios = new HashMap<>();

        listaNavios.put(2, "Destroyer");
        listaNavios.put(4, "Encoracado");


        System.out.println("----PREPARAÇÃO DO JOGADOR 1----");
        boolean preparaJogador1 = preparaJogador(jogador1, scanner, listaNavios);

        if (preparaJogador1){
            System.out.println("Jogador 1 posicionado !!");
            System.out.println("Seu tabuleiro:");
            jogador1.getTabuleiro().mostraMatrizPrincipal();

            System.out.println("Pressione para confirmar.");
            scanner.nextLine();
            UtilsConsole.limpaTela();
        }

        /*listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        boolean preparaJogador2 = preparaJogador(jogador2, scanner, listaNavios);

        if (preparaJogador2){
            System.out.println("Jogador 2 posicionado !!");
            System.out.println("Seu tabuleiro:");
            jogador1.getTabuleiro().mostraMatrizPrincipal();

            System.out.println("Pressione para confirmar.");
            scanner.nextLine();
        }*/

        // começa a logica de batalha

        //tem que fazer um sorteio de quem vai começar
        /*Random random = new Random();
        int jogadorEscolhidoJogar = random.nextInt(2) + 1;*/

        int jogadorEscolhidoJogar = 2;
        //cria uma matriz vazia e mostra para ele , ele vai atirar e eu comparo se a cordenada que ele colocou na matriz vazia se
        //tem algum barco posicionado na matrix real , se acertou ent mostra na matriz vazia a fumaça na posição


        boolean seila = iniciaBatalha(jogadorEscolhidoJogar, scanner);

        System.out.println("fsadasasdasd");

        scanner.close();

    }

    public static boolean preparaJogador(Jogador jogador, Scanner scanner, HashMap<Integer, String> listaNavios) {
        boolean posicionado = true;

        while (true) {

            for (int i = 0; i <= listaNavios.size(); i++) {

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
                        System.out.println("Tente posicionar o barco " + navioEscolhido + " novamente.");
                        jogador.getTabuleiro().mostraMatrizPrincipal();

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
            while (!scanner.hasNextInt()) {
                System.out.println("Erro: Entrada inválida! Digite um número da lista:");
                scanner.next();
            }
            escolhaNavio = scanner.nextInt();

            navioEscolhido = listaNavios.get(escolhaNavio);

            if (navioEscolhido == null){
                System.out.println("Navio inválido!, tente novamente.");
            }else {
                break;
            }
        }
        listaNavios.remove(escolhaNavio);
        return navioEscolhido;

    }

    public static boolean iniciaBatalha(int jogadorEscolhidoJogar, Scanner scanner){

        System.out.println("Jogador " + jogadorEscolhidoJogar + " começa a batalha !!!");

        /*try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        UtilsConsole.limpaTela();

        while (true){

            if (jogadorEscolhidoJogar == 1) {
                System.out.println("Tabuleiro do jogador 2");
                jogador2MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();

            }else if (jogadorEscolhidoJogar == 2) {
                System.out.println("Tabuleiro do jogador 1");
                jogador1MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();

            }

            System.out.println("Escolha a cordenada para atacar !");

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

                boolean erroColuna = UtilsConsole.validaColuna(coluna);

                if (!erroColuna) {
                    UtilsConsole.limpaTela();
                } else {
                    break;
                }
            }

            Boolean acertouNavio = atacarTabuleiro(linha, coluna, jogadorEscolhidoJogar);

            if (jogador1.getNavio().getDestroyer().getVida() == 0){
                System.out.println("Eu sou um genio;");
            }

            if (jogador1.getNavio().getEncaracado().getVida() == 0){
                System.out.println("Eu sou um genio 2 ;");
            }


            if (acertouNavio) {
                System.out.println("Belo tiro! Você joga novamente.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UtilsConsole.limpaTela();



            } else if (acertouNavio == false) {
                System.out.println("Água! Trocando de jogador...");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UtilsConsole.limpaTela();
            }else if (acertouNavio == null) {
                System.out.println("Voçê já atirou nesta posição !");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UtilsConsole.limpaTela();
            }









        }




    }

    public static Boolean atacarTabuleiro(int linha, int coluna, int jogadorEscolhidoJogar){

        if (jogadorEscolhidoJogar == 1){


            char caracter = jogador2.getTabuleiro().getPosicao(linha, coluna);


            if (caracter == '~'){
                jogador2MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, 'X');
                return false;

            }else if (caracter == 'B') {
                jogador2MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, '@');

                //pega o navio filha que estiver na posição e diminui sua vida
                jogador2.setNavio(jogador2.getTabuleiro().getPosicaoNavio(linha, coluna));
                Navio navio = jogador2.getNavio();
                navio.setVida(navio.getVida() - 1);

                return true;
            }else {
                return null;
            }

        }else if (jogadorEscolhidoJogar == 2){

            char caracter = jogador1.getTabuleiro().getPosicao(linha, coluna);


            char caracterMatrizTemplate = jogador1MatrizTemplate.getTabuleiro().getPosicao(linha, coluna);

            if (caracterMatrizTemplate == '@' || caracterMatrizTemplate == 'X'){
                return null;
            }
            if (caracter == '~'){
                jogador1MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, 'X');
                return false;

            }else if (caracter == 'B') {

                jogador1MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, '@');


                Navio navio = jogador1.getTabuleiro().getPosicaoNavio(linha, coluna);
                if (navio.getTipo().equals("Encoracado")){
                    jogador1.getNavio().getEncaracado().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Destroyer")){
                    jogador1.getNavio().getDestroyer().setVida(navio.getVida() - 1);
                }


                return true;

            }

        }

        return null;
    }




}
