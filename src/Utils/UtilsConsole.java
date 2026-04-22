package Utils;

public class UtilsConsole {

    public static void limpaTela(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }

    public static boolean validaLinha(int linha){

        if(linha < 0 || linha > 10){

            System.out.println("Erro: Linha inválida.");

            System.out.print("Aguarda para tentar novamente ");
            for (int i = 0; i < 3; i++){
                System.out.print(".");
                try {
                    Thread.sleep(1250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }

    public static boolean validaColuna(int coluna){

        if(coluna < 0 || coluna > 10){

            System.out.println("Erro: Coluna inválida.");

            System.out.print("Aguarda para tentar novamente ");
            for (int i = 0; i < 3; i++){
                System.out.print(".");
                try {
                    Thread.sleep(1250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }
        return true;
    }

    public static boolean validaPosicao(String posicao){

        if (!posicao.equals("H") && !posicao.equals("V")) {
            System.out.println("Erro: Orientação inválida. Use 'H' para horizontal ou 'V' para vertical.");

            System.out.print("Aguarda para tentar novamente ");
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(1250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        return true;
    }
}
