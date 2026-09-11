/*
 *  Artista É-UM Usuario. Acrescenta o nome artístico.
 *
 *  Observe: `nome`/`email` NÃO se repetem aqui — vêm da superclasse. Se a
 *  regra de validação de e-mail mudar, muda em UM lugar (Usuario), e os dois
 *  subtipos herdam a correção. É esse "consertar num lugar só" que a herança
 *  bem usada entrega.
 */
public class Artista extends Usuario {

    private final String nomeArtistico;

    public Artista(String nome, String email, String nomeArtistico) {
        super(nome, email);
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    @Override
    public String tipoDePerfil() {
        return "Artista: " + nomeArtistico;
    }
}
