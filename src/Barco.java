public class Barco {


    public enum TipoBarco {
        PORTA_AVIOES("Porta-Aviões", 5),
        CRUZADOR("Cruzador", 3),
        SUBMARINO("Submarino", 3),
        DESTRUIDOR("Destruidor", 2),
        PATRULHA("Couraçado", 4);

        private final String nome;
        private final int tamanho;
        private int vida;

        TipoBarco(String nome, int tamanho) {
            this.nome = nome;
            this.tamanho = tamanho;
        }

        public String getNome() {
            return nome;
        }

        public int getTamanho() {
            return tamanho;
        }
    }
}