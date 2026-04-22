import Navios.*;
import Utils.UtilsConsole;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

    private static Jogador jogador1;
    private static Jogador jogador2;
    private static Jogador jogador1MatrizTemplate;
    private static Jogador jogador2MatrizTemplate;
    private static final Random random = new Random();

    public static void iniciarJogo(Scanner scanner, boolean isIA) {

        UtilsConsole.limpaTela();
        System.out.println("Iniciando o Jogo de Batalha Naval!");

        // Cria os jogadores e seus respectivos tabuleiros
        jogador1 = new Jogador(new Tabuleiro(), new Navio());
        jogador2 = new Jogador(new Tabuleiro(), new Navio());
        jogador1MatrizTemplate = new Jogador(new Tabuleiro());
        jogador2MatrizTemplate = new Jogador(new Tabuleiro());

        HashMap<Integer, String> listaNavios = new HashMap<>();

        listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

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


        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");

        listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

        if (isIA){
            boolean preparaIa = preparaJogador(jogador2, listaNavios);

            if (preparaIa){

                System.out.print("A IA esta posicionando seus barcos, aguarde ");
                for (int j = 0; j < 3; j++){
                    System.out.print(".");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("\nIA posicionada.");

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UtilsConsole.limpaTela();
            }

        }else {

            boolean preparaJogador2 = preparaJogador(jogador2, scanner, listaNavios);

            if (preparaJogador2){
                System.out.println("Jogador 2 posicionado !!");
                System.out.println("Seu tabuleiro:");
                jogador1.getTabuleiro().mostraMatrizPrincipal();

                System.out.println("Pressione para confirmar.");
                scanner.nextLine();
            }
        }

        // começa a logica de batalha

        int jogadorEscolhidoJogar = random.nextInt(2) + 1;

        if (isIA){
            iniciaBatalhaIA(jogadorEscolhidoJogar, scanner);
        }else{
            iniciaBatalha(jogadorEscolhidoJogar, scanner);
        }

    }

    public static boolean preparaJogador(Jogador jogador, Scanner scanner, HashMap<Integer, String> listaNavios) {

        for (int i = 0; i < 5; i++) {

            System.out.println("Posicione seus barcos no tabuleiro.");
            jogador.getTabuleiro().mostraMatrizPrincipal();
            String navioEscolhido = escolherNavio(scanner, listaNavios);
            Navio navio = Navio.buscarNavioDaFrota(navioEscolhido, jogador.getNavio());

            while (true) {
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
                        jogador.getTabuleiro().mostraMatrizPrincipal();
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
                        jogador.getTabuleiro().mostraMatrizPrincipal();
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

                    if (!erroPosicao) {
                        UtilsConsole.limpaTela();
                        jogador.getTabuleiro().mostraMatrizPrincipal();
                    } else {
                        break;
                    }
                }

                boolean posicionaBarco = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio, scanner);

                if (posicionaBarco) {
                    break;
                } else {
                    System.out.println("Tente posicionar o barco " + navioEscolhido + " novamente.");
                    jogador.getTabuleiro().mostraMatrizPrincipal();
                }
            }
        }
        return true;
    }

    public static boolean preparaJogador(Jogador jogador, HashMap<Integer, String> listaNavios) {
        for (int i = 0; i < 5; i++) {

            String navioEscolhido = escolherNavio(listaNavios);
            Navio navio = Navio.buscarNavioDaFrota(navioEscolhido, jogador.getNavio());

            while (true) {
                int linha = random.nextInt(10);
                int coluna = random.nextInt(10);
                String orientacao = String.valueOf(random.nextBoolean() ? 'V' : 'H');

                boolean posicionaBarco = jogador.getTabuleiro().posicionaBarco(linha, coluna, orientacao, navio);
                if (posicionaBarco) {
                    break;
                }
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

    public static String escolherNavio(HashMap<Integer, String> listaNavios){

        int primeiroKey = listaNavios.keySet().iterator().next();
        String navioEscolhido = listaNavios.get(primeiroKey);

        listaNavios.remove(primeiroKey);
        return navioEscolhido;

    }

    public static void iniciaBatalha(int jogadorEscolhidoJogar, Scanner scanner){

        System.out.println("Jogador " + jogadorEscolhidoJogar + " começa a batalha !!!");

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UtilsConsole.limpaTela();

        while (true){

            UtilsConsole.limpaTela();
            System.out.print("Vez do jogador: ");
            if (jogadorEscolhidoJogar == 1) {
                System.out.print("1\n");

                System.out.println("\nTabuleiro do jogador 1:");
                jogador1.getTabuleiro().mostraMatrizPrincipal();
                mostraStatusNavios(jogador1, "Jogador 1");

                System.out.println("Tabuleiro do oponete:");
                jogador2MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();

            } else {
                System.out.print("2\n");

                System.out.println("\nTabuleiro do jogador 2:");
                jogador2.getTabuleiro().mostraMatrizPrincipal();
                mostraStatusNavios(jogador2, "Jogador 2");

                System.out.println("Tabuleiro do oponete:");
                jogador1MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();
            }

            System.out.println("Escolha a cordenada para atacar !");

            int linha;
            while (true) {
                System.out.println("Informe a posição da linha [0-9]: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                    scanner.next();
                }
                linha = scanner.nextInt();
                if (UtilsConsole.validaLinha(linha)) {
                    break;
                }
                System.out.println("Linha inválida, tente novamente.");
            }

            int coluna;
            while (true) {
                System.out.println("Informe a posição da coluna [0-9]: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a coluna:");
                    scanner.next();
                }
                coluna = scanner.nextInt();
                if (UtilsConsole.validaColuna(coluna)) {
                    break;
                }
                System.out.println("Coluna inválida, tente novamente.");
            }

            Boolean acertouNavio = atacarTabuleiro(linha, coluna, jogadorEscolhidoJogar);

            if (acertouNavio == null) {
                System.out.println("Você já atirou nesta posição! Trocando de jogador...");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;
            } else if (acertouNavio) {
                System.out.println("Belo tiro! Você joga novamente.");
            } else {
                System.out.println("Água! Trocando de jogador...");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (jogador2.getNavio().getDestroyer().getVida() == 0
                    && jogador2.getNavio().getEncaracado().getVida() == 0
                    && jogador2.getNavio().getCruzador().getVida() == 0
                    && jogador2.getNavio().getPortaAvioes().getVida() == 0
                    && jogador2.getNavio().getSubmarino().getVida() == 0) {
                System.out.println("Jogador 1 venceu !!!!");
                break;
            }

            if (jogador1.getNavio().getDestroyer().getVida() == 0
                    && jogador1.getNavio().getEncaracado().getVida() == 0
                    && jogador1.getNavio().getCruzador().getVida() == 0
                    && jogador1.getNavio().getPortaAvioes().getVida() == 0
                    && jogador1.getNavio().getSubmarino().getVida() == 0) {
                System.out.println("Jogador 2 venceu !!!!");
                break;
            }
        }

        System.out.print("Retornando ao menu ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        UtilsConsole.limpaTela();
    }

    public static void iniciaBatalhaIA(int jogadorEscolhidoJogar, Scanner scanner) {

        if (jogadorEscolhidoJogar == 2){
            System.out.println("IA começa a batalha !!!");
        } else {
            System.out.println("Jogador 1 começa a batalha !!!");
        }

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jogador2.getTabuleiro().mostraMatrizPrincipal();

        while (true) {

            UtilsConsole.limpaTela();
            System.out.print("Vez do jogador: ");
            if (jogadorEscolhidoJogar == 1) {
                System.out.print("Jogador 1\n");
            } else {
                System.out.print("IA\n");
            }

            System.out.println("\nSeu tabuleiro Jogador 1:");
            jogador1.getTabuleiro().mostraMatrizPrincipal();
            mostraStatusNavios(jogador1, "Jogador 1");

            System.out.println("\nTabuleiro da IA:");
            jogador2MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();
            mostraStatusNavios(jogador2, "IA");

            int linha = 0;
            int coluna = 0;

            if (jogadorEscolhidoJogar == 1) {
                System.out.println("\nEscolha a coordenada para atacar!");
                while (true) {
                    System.out.println("Informe a posição da linha [0-9]: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a linha:");
                        scanner.next();
                    }
                    linha = scanner.nextInt();
                    if (UtilsConsole.validaLinha(linha)) {
                        break;
                    }
                    System.out.println("Linha inválida, tente novamente.");
                }

                while (true) {
                    System.out.println("Informe a posição da coluna [0-9]: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Erro: Entrada inválida! Digite um número entre 0 e 9 para a coluna:");
                        scanner.next();
                    }
                    coluna = scanner.nextInt();
                    if (UtilsConsole.validaColuna(coluna)) {
                        break;
                    }
                    System.out.println("Coluna inválida, tente novamente.");
                }

            } else {

                while(true){

                    linha = random.nextInt(10);
                    coluna = random.nextInt(10);

                    char caracterMatrizTemplate = jogador1MatrizTemplate.getTabuleiro().getPosicao(linha, coluna);

                    if(caracterMatrizTemplate != '@' && caracterMatrizTemplate != 'X') {
                        break;
                    }
                }

                System.out.print("\nIA atacando ");
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }

            Boolean acertouNavio = atacarTabuleiro(linha, coluna, jogadorEscolhidoJogar);

            if (acertouNavio == null) {
                System.out.println("Você já atirou nesta posição! Trocando de jogador...");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;
            } else if (acertouNavio) {
                System.out.println("Belo tiro! Você joga novamente.");
            } else {
                System.out.println("Água! Trocando de jogador...");
                jogadorEscolhidoJogar = (jogadorEscolhidoJogar == 1) ? 2 : 1;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (jogador2.getNavio().getDestroyer().getVida() == 0
                    && jogador2.getNavio().getEncaracado().getVida() == 0
                    && jogador2.getNavio().getCruzador().getVida() == 0
                    && jogador2.getNavio().getPortaAvioes().getVida() == 0
                    && jogador2.getNavio().getSubmarino().getVida() == 0) {
                System.out.println("Jogador 1 venceu !!!!");
                break;
            }

            if (jogador1.getNavio().getDestroyer().getVida() == 0
                    && jogador1.getNavio().getEncaracado().getVida() == 0
                    && jogador1.getNavio().getCruzador().getVida() == 0
                    && jogador1.getNavio().getPortaAvioes().getVida() == 0
                    && jogador1.getNavio().getSubmarino().getVida() == 0) {
                System.out.println("IA venceu !!!!");
                break;
            }
        }

        System.out.print("Retornando ao menu ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        UtilsConsole.limpaTela();
    }

    public static Boolean atacarTabuleiro(int linha, int coluna, int jogadorEscolhidoJogar){

        if (jogadorEscolhidoJogar == 1){

            char caracter = jogador2.getTabuleiro().getPosicao(linha, coluna);

            char caracterMatrizTemplate = jogador2MatrizTemplate.getTabuleiro().getPosicao(linha, coluna);

            if (caracterMatrizTemplate == '@' || caracterMatrizTemplate == 'X'){
                return null;
            }
            if (caracter == '~'){
                jogador2MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, 'X');
                jogador2.getTabuleiro().setPosicao(linha, coluna, 'X');
                return false;

            }else if (caracter == 'B') {

                jogador2MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, '@');
                jogador2.getTabuleiro().setPosicao(linha, coluna, '@');


                Navio navio = jogador2.getTabuleiro().getPosicaoNavio(linha, coluna);

                if (navio.getTipo().equals("Encoracado")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Destroyer")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Porta aviões")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Submarino")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Cruzado")){
                    navio.setVida(navio.getVida() - 1);
                }

                return true;
            }

        }else if (jogadorEscolhidoJogar == 2){

            char caracter = jogador1.getTabuleiro().getPosicao(linha, coluna);


            char caracterMatrizTemplate = jogador1MatrizTemplate.getTabuleiro().getPosicao(linha, coluna);

            if (caracterMatrizTemplate == '@' || caracterMatrizTemplate == 'X'){
                return null;
            }
            if (caracter == '~'){
                jogador1MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, 'X');
                jogador1.getTabuleiro().setPosicao(linha, coluna, 'X');
                return false;

            }else if (caracter == 'B') {

                jogador1MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, '@');
                jogador1.getTabuleiro().setPosicao(linha, coluna, '@');

                Navio navio = jogador1.getTabuleiro().getPosicaoNavio(linha, coluna);

                if (navio.getTipo().equals("Encoracado")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Destroyer")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Porta aviões")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Submarino")){
                    navio.setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Cruzado")){
                    navio.setVida(navio.getVida() - 1);
                }

                return true;
            }
        }

        return null;
    }

    public static void mostraStatusNavios(Jogador jogador, String nomeJogador) {
        System.out.println("---- Status dos navios do " + nomeJogador + " ----");
        System.out.println("Submarino: " + jogador.getNavio().getSubmarino().getVida() + " vidas(s)");
        System.out.println("Destroyer: " + jogador.getNavio().getDestroyer().getVida() + " vidas(s)");
        System.out.println("Porta-aviões: " + jogador.getNavio().getPortaAvioes().getVida() + " vidas(s)");
        System.out.println("Encouraçado: " + jogador.getNavio().getEncaracado().getVida() + " vidas(s)");
        System.out.println("Cruzador: " + jogador.getNavio().getCruzador().getVida() + " vidas(s)");
        System.out.println("------------------------------------");
    }
}
