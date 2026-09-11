/*
 *  Musica — um CONCRETO da abstração ConteudoDeAudio.
 *
 *  Só precisa acrescentar o que é ESPECÍFICO de música (o artista) e
 *  concretizar a operação abstrata `descricaoCurta()`. Todo o resto
 *  (título, duração, fichaTecnica) vem "de graça" da superclasse — este é o
 *  reúso que a generalização (Booch) proporciona.
 */
public class Musica extends ConteudoDeAudio {

    private final String artista;

    public Musica(String titulo, int duracaoSegundos, String artista) {
        super(titulo, duracaoSegundos); // reaproveita a validação da base
        this.artista = (artista == null || artista.isBlank()) ? "Artista desconhecido" : artista;
    }

    public String getArtista() {
        return artista;
    }

    /** Concretiza o contrato herdado, à maneira de "música". */
    @Override
    public String descricaoCurta() {
        return "Música de " + artista;
    }
}
