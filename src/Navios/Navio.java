package Navios;

public abstract class Navio {

    private int vida;
    private int tamanho;
    private String tipo;

    public Navio(int vida, int tamanho, String tipo) {
        this.vida = vida;
        this.tamanho = tamanho;
        this.tipo = tipo;
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

}