import Navios.Navio;
import Utils.UtilsConsole;

import java.util.HashMap;
import java.util.Scanner;

public class Tabuleiro {

    private final int TAMANHO = 10;
    private final char AGUA = '~';
    private final char BARCO = 'B';

    private final char[][] matriz = new char[10][10];

    public Tabuleiro() {
        // Inicializa a matriz com água quando o tabuleiro é criado
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
        if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
            return matriz[linha][coluna];
        }
        return ' '; // Ou lançar uma exceção para posições inválidas
    }

    public void setPosicao(int linha, int coluna, char valor) {
        if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
            matriz[linha][coluna] = valor;
        }
    }

    public boolean posicionaBarco(int linha, int coluna, String orientacao, Navio navio, Scanner scanner){
        int tamanhoBarco = navio.getTamanho();

        // Validação de limites e sobreposição
        if (orientacao.equalsIgnoreCase("H")) { // Horizontal
            if (coluna + tamanhoBarco > TAMANHO) {
                System.out.println("Erro: O barco excede os limites do tabuleiro na horizontal.");
                return false;
            }
            for (int j = 0; j < tamanhoBarco; j++) {
                if (matriz[linha][coluna + j] == BARCO) {
                    System.out.println("Erro: Já existe um barco nesta posição.");
                    return false;
                }
            }
            // Posiciona o barco
            for (int j = 0; j < tamanhoBarco; j++) {
                matriz[linha][coluna + j] = BARCO;
            }
        } else if (orientacao.equalsIgnoreCase("V")) { // Vertical
            if (linha + tamanhoBarco > TAMANHO) {
                System.out.println("Erro: O barco excede os limites do tabuleiro na vertical.");
                return false;
            }
            for (int i = 0; i < tamanhoBarco; i++) {
                if (matriz[linha + i][coluna] == BARCO) {
                    System.out.println("Erro: Já existe um barco nesta posição.");
                    return false;
                }
            }
            // Posiciona o barco
            for (int i = 0; i < tamanhoBarco; i++) {
                matriz[linha + i][coluna] = BARCO;
            }
        } else {
            System.out.println("Erro: Orientação inválida. Use 'H' para horizontal ou 'V' para vertical.");
            return false;
        }

        System.out.println("Barco posicionado em (" + linha + ", " + coluna + ") com orientação " + orientacao + "\n");

        mostraMatrizPrincipal();

        System.out.println("Pressione enter para posicionar o próximo barco ...");
        scanner.nextLine();

        UtilsConsole.limpaTela();
        return true;
    }

    public void mostraMatrizPrincipal(){
        System.out.print("   "); // Espaço para o número da linha
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < TAMANHO; i++){ // Itera sobre as linhas
            System.out.print(i + "  "); // Imprime o número da linha
            for(int j = 0; j < TAMANHO; j++){ // Itera sobre as colunas
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
