package Navios;

import java.util.ArrayList;
import java.util.List;

public class Navio {

    private int vida;
    private int tamanho;
    private String tipo;

    private Encoracado encaracado;
    private Destroyer destroyer;


    public Navio(int vida, int tamanho, String tipo) {
        this.vida = vida;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }

    public Navio() {
        encaracado = new Encoracado();
        destroyer = new Destroyer();

    }

    public Encoracado getEncaracado() {
        return encaracado;
    }

    public void setEncaracado(Encoracado encaracado) {
        this.encaracado = encaracado;
    }

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static Navio buscarNavioByNome(String nome) {

        List<Navio> listaNavioClass = new ArrayList<>();
        listaNavioClass.add(new Encoracado());
        listaNavioClass.add(new Destroyer());
        listaNavioClass.add(new PortaAviao());
        listaNavioClass.add(new Submarino());
        listaNavioClass.add(new Cruzador());

        for (Navio navio : listaNavioClass) {
            if (navio.getTipo().equals(nome)) {
                return navio;
            }

        }

        return null;
    }

}