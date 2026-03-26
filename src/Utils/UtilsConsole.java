package Utils;

public class UtilsConsole {

    public static void limpaTela(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }

    public static boolean validaLinha(int linha){

        if(linha < 0 || linha > 10){
            UtilsConsole.limpaTela();
            System.out.println("Linha inválida.");
            System.out.println("Aguarda para tentar novamente...");
            return false;
        }
        return true;
    }

    public static boolean validaColuna(int linha){

        if(linha < 0 || linha > 10){
            UtilsConsole.limpaTela();
            System.out.println("Coluna inválida.");
            System.out.println("Aguarda para tentar novamente...");
            return false;
        }
        return true;
    }




}
