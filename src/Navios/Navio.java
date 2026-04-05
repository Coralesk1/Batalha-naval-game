package Navios;

import java.util.ArrayList;
import java.util.List;

public class Navio {

    private int vida;
    private int tamanho;
    private String tipo;

    private Encoracado encaracado;
    private Destroyer destroyer;
    private PortaAviao portaAviao;
    private Submarino submarino;
    private Cruzador cruzador;


    public Navio(int vida, int tamanho, String tipo) {
        this.vida = vida;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }

    public Navio() {
        encaracado = new Encoracado();
        destroyer = new Destroyer();
        portaAviao = new PortaAviao();
        submarino = new Submarino();
        cruzador = new Cruzador();

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

    public PortaAviao getPortaAvioes() {
        return portaAviao;
    }

    public void setPortaAvioes(PortaAviao portaAvioes) {
        this.portaAviao = portaAvioes;
    }

    public Submarino getSubmarino() {
        return submarino;
    }

    public void setSubmarino(Submarino submarino) {
        this.submarino = submarino;
    }

    public Cruzador getCruzador() {
        return cruzador;
    }

    public void setCruzador(Cruzador cruzador) {
        this.cruzador = cruzador;
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