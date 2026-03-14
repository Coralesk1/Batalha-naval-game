public class Tabuleiro {

    private final int TAMANHO = 10;
    private final char AGUA = '~';
    private final char BARCO = 'B';
    private char[][] matriz; // Matriz do tabuleiro


    public int getTAMANHO() {
        return TAMANHO;
    }

    public char getAGUA() {
        return AGUA;
    }

    public char getBARCO() {
        return BARCO;
    }

    public boolean posicionaBarco(int linha, int coluna, String orientacao){
        // Verifica se a posição é válida
        if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
            this.matriz[linha][coluna] = BARCO;
            return true;
        }
        return false;
    }

    public void mostraMatrizPrincipal(){
        // Imprime os números das colunas
        System.out.print("   "); // Espaço para o número da linha
        for (int j = 0; j < TAMANHO; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < TAMANHO; i++){ // linhas
            System.out.print(i + "  "); // Imprime o número da linha
            for(int j = 0; j < TAMANHO; j++){ // colunas
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
