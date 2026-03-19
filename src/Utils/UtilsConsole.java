package Utils;

import java.util.List;

public class UtilsConsole {

    public void limpaTela(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }

    public static String validaLinha(int linha, List<String> erros){

        if(linha <= 0 || linha > 10){
            return "Linha inválida";
        }
        return null;
    }

    public static String validaColuna(int linha, List<String> erros){

        if(linha <= 0 || linha > 10){

        }
        return "";
    }


}
