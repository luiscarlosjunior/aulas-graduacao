import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AGREGAÇÃO (◇): a Playlist "tem" músicas, mas apenas APONTA para músicas que já
 * existem no catálogo — ela RECEBE a Musica pronta (adicionar), não a cria. Se a
 * playlist some, as músicas continuam existindo. Contraste direto com o Album.
 */
public class Playlist {

    private String nome;
    private final String dono;
    private final List<Musica> musicas = new ArrayList<>();   // referências a músicas existentes

    public Playlist(String nome, String dono) {
        this.nome = nome;
        this.dono = dono;
    }

    /** Recebe uma Musica JÁ EXISTENTE (não cria). Isso caracteriza a agregação. */
    public void adicionar(Musica m) {
        if (m == null) throw new IllegalArgumentException("música não pode ser nula");
        if (!musicas.contains(m)) musicas.add(m);   // evita duplicata
    }

    public void remover(Musica m) {
        musicas.remove(m);
    }

    public String getNome()         { return nome; }
    public void setNome(String n)   { this.nome = n; }
    public String getDono()         { return dono; }

    public List<Musica> getMusicas() {
        return Collections.unmodifiableList(musicas);
    }

    public int duracaoTotalSegundos() {
        int total = 0;
        for (Musica m : musicas) total += m.getDuracaoSegundos();
        return total;
    }
}
