import Navios.Navio;
import Navios.Submarino;

public class Jogador {

    private Tabuleiro tabuleiro;
    private Navio navio;


    public Jogador(Tabuleiro tabuleiro, Navio navio, ) {
        this.tabuleiro = tabuleiro;
        this.navio = navio;

    }
    public Jogador(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }


    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

}
