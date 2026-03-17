import Navios.Navio;

import java.util.HashMap;
import java.util.Scanner;

public class Tabuleiro {

    private final int TAMANHO = 10;
    private final char AGUA = '~';
    private final char BARCO = 'B';

    private final char[][] matriz = new char[10][10];

    public int getTAMANHO() {
        return TAMANHO;
    }

    public char getAGUA() {
        return AGUA;
    }

    public char getBARCO() {
        return BARCO;
    }



    public boolean posicionaBarco(int linha, int coluna, String orientacao, Navio navioEscolhido){


        System.out.print("   "); // Espaço para o número da linha
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < TAMANHO; i++){ // linhas
            System.out.print(i + "  "); // Imprime o número da linha
            for(int j = 0; j < TAMANHO; j++){ // colunas
                matriz[i][j] = AGUA;
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        return false;
    }

    public void mostraMatrizPrincipal(){

        System.out.print("   "); // Espaço para o número da linha
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < TAMANHO; i++){ // linhas
            System.out.print(i + "  "); // Imprime o número da linha
            for(int j = 0; j < TAMANHO; j++){ // colunas
                matriz[i][j] = AGUA;
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }



}
