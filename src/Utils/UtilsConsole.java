package Utils;

public class UtilsConsole {

    public void limpaTela(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }

    public void mostraMatriz(){

        int[][] matrizBi = new int[10][10];

        for(int i = 0; i < matrizBi.length; i++){

            for(int j = 0; j < matrizBi[0].length; j++){
                System.out.print(matrizBi[i][j] + " ");
            }
            System.out.println();
        }
    }
}
