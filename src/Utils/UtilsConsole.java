package Utils;

import java.util.List;

public class UtilsConsole {

    public void limpaTela(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }

    public String validaEntrada(int linha, List<String> erros){

        if(linha <= 0 || linha > 10){
            erros.add("inválida.");
        }
        return null;
    }


}
