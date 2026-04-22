import Navios.Navio;
import Utils.UtilsConsole;
import java.util.Scanner;

public class Tabuleiro {

    private final int TAMANHO = 10;
    private final char AGUA = '~';
    private final char BARCO = 'B';

    private final char[][] matriz = new char[10][10];
    private final Navio[][] gradeNavios = new Navio[10][10];

    public Tabuleiro() {
        // inicializa a matriz com água toda vez que o tabuleiro é criado
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                matriz[i][j] = AGUA;
            }
        }
    }
    public int getTAMANHO() {
        return TAMANHO;
    }

    public char getAGUA() {
        return AGUA;
    }

    public char getBARCO() {
        return BARCO;
    }

    public char getPosicao(int linha, int coluna) {
        return matriz[linha][coluna];
    }
    public Navio getPosicaoNavio(int linha, int coluna) {
        return gradeNavios[linha][coluna];
    }


    public void setPosicao(int linha, int coluna, char valor) {
        matriz[linha][coluna] = valor;
    }

    public boolean posicionaBarco(int linha, int coluna, String orientacao, Navio navio, Scanner scanner){
        int tamanhoBarco = navio.getTamanho();

        // Validação de limites e sobreposição
        if (orientacao.equalsIgnoreCase("H")) { // Horizontal

            if (coluna + tamanhoBarco > TAMANHO) {
                System.out.println("Erro: O barco excede os limites do tabuleiro na horizontal.");

                System.out.print("Aguarda para tentar novamente ");
                for (int i = 0; i < 3; i++){
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();
                return false;
            }

            // Verifica se já existe um barco na posição
            for (int j = 0; j < tamanhoBarco; j++) {
                if (matriz[linha][coluna + j] == BARCO) {
                    System.out.println("Erro: Já existe um barco nesta posição.");

                    System.out.print("Aguarda para tentar novamente ");
                    for (int i = 0; i < 3; i++){
                        System.out.print(".");
                        try {
                            Thread.sleep(1250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    UtilsConsole.limpaTela();
                    return false;
                }
            }

            // Posiciona o barco
            for (int j = 0; j < tamanhoBarco; j++) {
                matriz[linha][coluna + j] = BARCO;
                gradeNavios[linha][coluna + j] =  navio;
            }

        } else if (orientacao.equalsIgnoreCase("V")) { // Vertical

            if (linha + tamanhoBarco > TAMANHO) {
                System.out.println("Erro: O barco excede os limites do tabuleiro na vertical.");

                System.out.print("Aguarda para tentar novamente ");
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                UtilsConsole.limpaTela();
                return false;
            }

            // Verifica se já existe um barco na posição
            for (int i = 0; i < tamanhoBarco; i++) {
                if (matriz[linha + i][coluna] == BARCO) {
                    System.out.println("Erro: Já existe um barco nesta posição.");

                    System.out.print("Aguarda para tentar novamente ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(".");
                        try {
                            Thread.sleep(1250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    UtilsConsole.limpaTela();
                    return false;
                }
            }

            // Posiciona o barco
            for (int i = 0; i < tamanhoBarco; i++) {
                matriz[linha + i][coluna] = BARCO;
                gradeNavios[linha + i][coluna] = navio;
            }
        }

        UtilsConsole.limpaTela();
        System.out.println("Barco posicionado em (" + linha + ", " + coluna + ") com orientação " + orientacao + "\n");

        mostraMatrizPrincipal();

        System.out.println("Pressione enter para posicionar o próximo barco.");
        scanner.nextLine();

        UtilsConsole.limpaTela();
        return true;
    }

    public boolean posicionaBarco(int linha, int coluna, String orientacao, Navio navio){
        int tamanhoBarco = navio.getTamanho();


        if (orientacao.equalsIgnoreCase("H")) {

            if (coluna + tamanhoBarco > TAMANHO) {
                return false;
            }

            // Verifica se já existe um barco na posição
            for (int j = 0; j < tamanhoBarco; j++) {

                if (matriz[linha][coluna + j] == BARCO) {
                    return false;
                }
            }

            // Posiciona o barco
            for (int j = 0; j < tamanhoBarco; j++) {
                matriz[linha][coluna + j] = BARCO;
                gradeNavios[linha][coluna + j] =  navio;
            }

        } else if (orientacao.equalsIgnoreCase("V")) {

            if (linha + tamanhoBarco > TAMANHO) {
                return false;
            }

            // Verifica se já existe um barco na posição
            for (int i = 0; i < tamanhoBarco; i++) {

                if (matriz[linha + i][coluna] == BARCO) {
                    return false;
                }
            }

            // Posiciona o barco
            for (int i = 0; i < tamanhoBarco; i++) {
                matriz[linha + i][coluna] = BARCO;
                gradeNavios[linha + i][coluna] = navio;
            }

        }

        return true;
    }

    public void mostraMatrizPrincipal(){
        System.out.print("   ");
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < TAMANHO; i++){
            System.out.print(i + "  ");
            for(int j = 0; j < TAMANHO; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
