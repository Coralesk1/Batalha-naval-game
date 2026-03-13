public class Tabuleiro {

    private final int TAMANHO = 10;
    private final char AGUA = '~';
    private final char BARCO = 'B';


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
        char matrizBi[][] = new char[TAMANHO][TAMANHO];


        for(int i = 1; i < TAMANHO; i++){
            for(int j = 1; j < TAMANHO; j++){

                matrizBi[i][j] = AGUA;
                matrizBi[linha][coluna] = BARCO;
                System.out.print(matrizBi[i][j] + " ");

            }
            System.out.println();
        }





        return false;
    }



}
