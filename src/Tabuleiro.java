import Navios.Navio;

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

    public boolean posicionaBarco(int linha, int coluna, String orientacao, Navio navio){

        // TODO: Implementar a lógica real de posicionamento do barco.
        // Por enquanto, apenas um exemplo para marcar uma posição.
        // Você precisará verificar se o barco cabe, se não colide com outros barcos, etc.
        if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
            // Marca a posição inicial do barco.
            // A lógica completa precisará iterar sobre o tamanho do navio e sua orientação.
            matriz[linha][coluna] = BARCO;
            System.out.println("Barco posicionado em (" + linha + ", " + coluna + ")");
            return true;
        }
        System.out.println("Não foi possível posicionar o barco. Posição inválida.");
        return false;
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
