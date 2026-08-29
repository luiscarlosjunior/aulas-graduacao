/**
 * Um artista do catálogo. Classe simples (só o essencial), usada aqui para
 * demonstrar ASSOCIAÇÃO: um Ouvinte "segue" Artistas.
 *
 * Escopo desta aula: classes, objetos e relacionamentos — SEM herança ainda.
 */
public class Artista {

    private final String nomeArtistico;

    public Artista(String nomeArtistico) {
        if (nomeArtistico == null || nomeArtistico.isBlank()) {
            throw new IllegalArgumentException("nome artístico é obrigatório");
        }
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    @Override
    public String toString() {
        return nomeArtistico;
    }
}
