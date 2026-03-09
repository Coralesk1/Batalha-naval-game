public class Tabuleiro {

    private static final int TAMANHO = 10;
    private static final char AGUA = '~';
    private static final char BARCO = 'B';


    public boolean posicionaBarco(int linha, int coluna, String orientacao){
        int matrizBi[][] = new int[10][10];


        for(int i = 1; i < matrizBi.length; i++){
            for(int j = 1; j < matrizBi[0].length; j++){
                System.out.print(matrizBi[i][j] + " ");
                matrizBi[linha][coluna] = 2;
            }
            System.out.println();
        }
        return false;
    }

}
