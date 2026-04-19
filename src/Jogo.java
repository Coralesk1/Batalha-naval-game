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

        /*listaNavios.put(1, "Submarino");
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
        }*/

        listaNavios.put(1, "Submarino");
        listaNavios.put(2, "Destroyer");
        listaNavios.put(3, "Porta aviões");
        listaNavios.put(4, "Encoracado");
        listaNavios.put(5, "Cruzado");

        System.out.println("----PREPARAÇÃO DO JOGADOR 2----");
        if (isIA){
            boolean preparaIa = preparaIa(jogador2, listaNavios);

            if (preparaIa){
                System.out.println("IA posicionada !!");
                System.out.println("Tabuleira da IA:");
                jogador2.getTabuleiro().mostraMatrizPrincipal();



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

        /*if (isIA){
            iniciaBatalhaIA(jogadorEscolhidoJogar, scanner);
        }else{
            iniciaBatalha(jogadorEscolhidoJogar, scanner);
        }*/



    }

    public static boolean preparaJogador(Jogador jogador, Scanner scanner, HashMap<Integer, String> listaNavios) {
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

    public static boolean preparaIa(Jogador jogador, HashMap<Integer, String> listaNavios) {

        boolean posicionado = true;

        while (true) {

            for (int i = 0; i < 5; i++) {

                String navioEscolhido = escolherNavioIA(listaNavios);


                //busca navio
                Navio navio = Navio.buscarNavioByNome(navioEscolhido);

                while (true){

                    int linha = random.nextInt(10);
                    int coluna = random.nextInt(10);
                    String orientacao = String.valueOf(random.nextBoolean()? 'V' : 'H');

                    boolean posicionaBarco = jogador.getTabuleiro().posicionaBarcoIA(linha, coluna, orientacao, navio);

                    if (!posicionaBarco) {
                        posicionado = false;
                    }else{
                        posicionado = true;
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

    public static String escolherNavioIA(HashMap<Integer, String> listaNavios){

        int primeiroKey = listaNavios.keySet().iterator().next();
        String navioEscolhido = listaNavios.get(primeiroKey);

        listaNavios.remove(primeiroKey);
        return navioEscolhido;

    }

    public static void iniciaBatalha(int jogadorEscolhidoJogar, Scanner scanner){

        System.out.println("Jogador " + jogadorEscolhidoJogar + " começa a batalha !!!");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

            if (jogador2.getNavio().getDestroyer().getVida() == 0
                    && jogador2.getNavio().getEncaracado().getVida() == 0
                    && jogador2.getNavio().getCruzador().getVida() == 0
                    && jogador2.getNavio().getPortaAvioes().getVida() == 0
                    && jogador2.getNavio().getSubmarino().getVida() == 0){
                System.out.println("Jogador 1 venceu !!!!");

                System.out.println("Retornando ao menu ");
                for (int i = 0; i < 3; i++){
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();

                break;
            }

            if (jogador1.getNavio().getDestroyer().getVida() == 0
                    && jogador1.getNavio().getEncaracado().getVida() == 0
                    && jogador1.getNavio().getCruzador().getVida() == 0
                    && jogador1.getNavio().getPortaAvioes().getVida() == 0
                    && jogador1.getNavio().getSubmarino().getVida() == 0){
                System.out.println("Jogador 2 venceu !!!!");

                System.out.println("Retornando ao menu ");
                for (int i = 0; i < 3; i++){
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();

                break;
            }

        }
    }

    public static void iniciaBatalhaIA(int jogadorEscolhidoJogar, Scanner scanner){

        System.out.println("Jogador " + jogadorEscolhidoJogar + " começa a batalha !!!");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UtilsConsole.limpaTela();

        while (true){

            if (jogadorEscolhidoJogar == 1) {
                System.out.println("Tabuleiro do jogador 2");
                jogador2MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();

            }else if (jogadorEscolhidoJogar == 2) {
                System.out.println("Tabuleiro do jogador 1");
                jogador1MatrizTemplate.getTabuleiro().mostraMatrizPrincipal();

            }

            int linha = 0;
            int coluna = 0;

            System.out.println("Escolha a cordenada para atacar !");
            if (jogadorEscolhidoJogar == 1){

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

            } else if (jogadorEscolhidoJogar == 2) {

                linha = random.nextInt(10);
                coluna = random.nextInt(10);




            }


            Boolean acertouNavio = atacarTabuleiro(linha, coluna, jogadorEscolhidoJogar);

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

            if (jogador1.getNavio().getDestroyer().getVida() == 0
                    && jogador1.getNavio().getEncaracado().getVida() == 0
                    && jogador1.getNavio().getCruzador().getVida() == 0
                    && jogador1.getNavio().getPortaAvioes().getVida() == 0
                    && jogador1.getNavio().getSubmarino().getVida() == 0){
                System.out.println("Jogador 2 venceu !!!!");

                System.out.println("Retornando ao menu ");
                for (int i = 0; i < 3; i++){
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();

                break;
            }

            if (jogador2.getNavio().getDestroyer().getVida() == 0
                    && jogador2.getNavio().getEncaracado().getVida() == 0
                    && jogador2.getNavio().getCruzador().getVida() == 0
                    && jogador2.getNavio().getPortaAvioes().getVida() == 0
                    && jogador2.getNavio().getSubmarino().getVida() == 0){
                System.out.println("Jogador 1 venceu !!!!");

                System.out.println("Retornando ao menu ");
                for (int i = 0; i < 3; i++){
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();

                break;
            }

        }
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
                return false;

            }else if (caracter == 'B') {

                jogador2MatrizTemplate.getTabuleiro().setPosicao(linha, coluna, '@');


                Navio navio = jogador2.getTabuleiro().getPosicaoNavio(linha, coluna);
                if (navio.getTipo().equals("Encoracado")){
                    jogador2.getNavio().getEncaracado().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Destroyer")){
                    jogador2.getNavio().getDestroyer().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Porta Aviões")){
                    jogador2.getNavio().getPortaAvioes().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Submarino")){
                    jogador2.getNavio().getSubmarino().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Cruzado")){
                    jogador2.getNavio().getCruzador().setVida(navio.getVida() - 1);
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
                if (navio.getTipo().equals("Porta Aviões")){
                    jogador1.getNavio().getPortaAvioes().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Submarino")){
                    jogador1.getNavio().getSubmarino().setVida(navio.getVida() - 1);
                }
                if (navio.getTipo().equals("Cruzado")){
                    jogador1.getNavio().getCruzador().setVida(navio.getVida() - 1);
                }

                return true;

            }
        }

        return null;
    }
}
